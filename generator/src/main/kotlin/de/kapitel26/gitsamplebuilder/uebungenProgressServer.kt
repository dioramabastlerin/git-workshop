package de.kapitel26.gitsamplebuilder

import com.fasterxml.jackson.module.kotlin.*
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.statement.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.request
import io.ktor.application.install
import io.ktor.features.*
import io.ktor.html.respondHtml
import io.ktor.http.*
import io.ktor.util.pipeline.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.*
import io.ktor.response.respondRedirect
import java.io.File
import java.net.URL
import kotlin.math.*
import kotlinx.html.*
import kotlin.coroutines.*
import kotlin.random.*

fun main() {
    val adminServer = embeddedServer(Netty, port = 8040) { adminModule() }
    adminServer.start(wait = false)
    val participantsServer = embeddedServer(Netty, port = 8080) { participantsModule() }
    participantsServer.start(wait = true)
}

data class Progress(
        val aufgaben: List<Pair<String, List<String>>>,
        val aktuelleAufgabe: Pair<String, List<String>>,
        val participants: Map<String, String>,
        val achievements: Map<String, Set<String>>
)

data class UserSession(val userId: String)

val progressFile = File("build/git-uebungen/progress.json")

val mapper = jacksonObjectMapper()

var state: Progress =
        if (progressFile.exists()) {
            println("Restoring state from file")
            mapper.readValue(progressFile)
        } else {
            println("Fresh startup")
            readAufgaben().let { aufgaben ->
                Progress(aufgaben, aufgaben.first(), emptyMap(), emptyMap())
            }
        }

fun update(newState: Progress) {
    state = newState
    mapper.writerWithDefaultPrettyPrinter().writeValue(progressFile, newState)
}

fun readAufgaben(): List<Pair<String, List<String>>> {
    val s: String = File("build/git-uebungen/aufgaben.json").readText()
    return mapper.readValue(s)
}

fun Application.participantsModule() {
    install(Sessions) {
        cookie<UserSession>("user_session")
    }

    install(StatusPages) {
        exception<Throwable> { cause ->
            call.respondHtml(HttpStatusCode.InternalServerError) {
                body {
                    h1 { +"Dumm gelaufen: $cause.message"}
                    pre {
                        +cause.stackTraceToString()
                    }
                }
            }
        }
    }

    routing {
        aufgabenFilesLocalJekyll()
        workshopSiteFromLocalJekyll()
        get("/") { call.respondRedirect("/git-workshop") }
    }
}

fun Application.adminModule() {
    routing {
        adminDashboad()
    }
}


val ApplicationCall.userId : String get() = 
    sessions.get<UserSession>()?.userId 
    ?: java.util.Random().nextInt().toString().also { sessions.set(UserSession(it)) }

fun Route.aufgabenFilesLocalJekyll() {
    get("/git-workshop/git-uebungen/{path...}") {
        println("Aufgabe request ${call.parameters.getAll("path")}")
        val previous = call.sessions.get<UserSession>() 
        val session = 
            if( previous != null && state.participants.containsKey(previous.userId))
                previous
            else 
                Random.Default.nextInt()
                .let { "%08x".format(it) }
                .let { UserSession(it) }
                .also { 
                    call.sessions.set(it)
                    println("Starting new session with userId $it")
                    update(state.copy(participants = state.participants + (it.userId to it.userId)))
                }
        val userId = session.userId

        call.parameters["newAlias"]?.let { update(state.copy(participants = state.participants + (userId to it)))}
       
        if(call.parameters["signout"] != null)  { 
            call.sessions.clear<UserSession>()
            call.respondRedirect("/git-workshop")
        } else if(state.participants[userId] == userId) {
            call.respondHtml() {
                body {
                    h2 { +"Git Workshop - Progress Monitor - Teilnehmer" }
                    form(action = "#", method = FormMethod.get) {
                        label { text("Dein Name/Alias:") }
                        input(name = "newAlias") { value = "torfnase" }
                        submitInput()
                    }    
                }
            }
        } else {
            val sidParam = call.parameters["sid"]
            println("userId=$userId, sid=$sidParam")
            if (sidParam != null) {
                val completed = call.parameters["completed"].toBoolean()
                val previousAchievements: Set<String> = state.achievements[sidParam] ?: emptySet()
                val updatedAchievements = if(completed) previousAchievements + userId else previousAchievements -userId
                update(state.copy(achievements = state.achievements + (sidParam to updatedAchievements)))
            }

            val response = this.getStaticContent("git-uebungen/" + (call.parameters.getAll("path")?.joinToString("/") ?: ""))
            val processedContent = response.readText().replace(
                """\<\!\-\-UEB\-(.+?)\-\-\> \<h2\> (.+?)\ <\/h2\>""".toRegex(), 
                { step -> 
                    val aufgabname = step.groups[1]?.value
                    val schrittname = step.groups[2]?.value
                    val schrittSid = abs((aufgabname to schrittname).hashCode()).toString()    
                    val isCompleted = state.achievements[schrittSid]?.contains(call.sessions.get<UserSession>()?.userId) ?: false
                    
                    """<h2>$schrittname <a href="?sid=$schrittSid&completed=${!isCompleted}">${ if(isCompleted) "erledigt" else "offen"} </a></h2>"""
                }
            )
            call.respondText(processedContent, status = response.status, contentType = response.contentType())
        }
    }
}

fun Route.workshopSiteFromLocalJekyll() {
    get("/git-workshop/{path...}") { 
        
        val response = this.getStaticContent(call.parameters.getAll("path")?.joinToString("/") ?: "")
        call.respondBytes(response.readBytes(), status = response.status, contentType = response.contentType())
    }
}


suspend fun PipelineContext<Unit, ApplicationCall>.getStaticContent(path: String) : HttpResponse =
    "http://localhost:4000/git-workshop/${path}"
        .also { println("Requesting: $it") } 
        .let { url ->   HttpClient().use {  c -> c.request(url) {} } }


fun Route.adminDashboad() {
    get("/") {
        val selected = call.parameters["select"] ?: "?"
        update(
                state.copy(
                        aktuelleAufgabe = state.aufgaben.find { it.first == selected }
                                        ?: state.aufgaben.first()
                )
        )
        call.respondHtml {
            head { 
                // meta() { 
                //     httpEquiv="refresh"
                //     content="2 0" 
                // }
            }

            body {
                h1 { text("Git Workshop - Progress Monitor") }
                h2 { text("Aktuelle Aufgabe") }
                val totalNum = state.participants.size
                state.aktuelleAufgabe.also { (aufgabe, schritte) ->
                    schritte.forEach { schritt ->
                        val sid =
                                abs((state.aktuelleAufgabe.first to schritt).hashCode())
                                        .toString()
                        p { +"$aufgabe/$schritt ${state.achievements[sid]?.size}/$totalNum ${state.achievements[sid]?.map { state.participants[it] }} sid=$sid" }
                    }
                }
                h2 { text("Aufgaben") }
                form(action = "/", method = FormMethod.get) {
                    state.aufgaben.map { it.first }.forEach {
                        input(name = "select") {
                            value = it
                            type = InputType.radio
                            checked = it == state.aktuelleAufgabe.first
                            onChange = "this.form.submit()"
                            text(it)
                        }
                        br {}
                    }
                }
                h2 { text("Teilnehmer") }
                p {
                    state.participants.forEach { (userId, alias) ->
                        a(href="/?id=$userId") { +"$alias $userId" }
                        br {}
                    }
                }
            }
        }
    }    
}

