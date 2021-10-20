package de.kapitel26.gitsamplebuilder

import com.fasterxml.jackson.module.kotlin.*
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.html.respondHtml
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.*
import io.ktor.response.respondRedirect
import java.io.File
import kotlin.math.abs
import kotlinx.html.*
import kotlin.coroutines.*

fun main() {
    val adminServer = embeddedServer(Netty, port = 8040) { adminModule() }
    val participantsServer = embeddedServer(Netty, port = 8080) { participantsModule() }
    adminServer.start(wait = false)
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

    routing {
        get("/") {
            call.respondHtml {
                val sessions: UserSession? = call.sessions.get()
                val userId = call.parameters["id"] ?: sessions?.userId
                val toggleSid = call.parameters["toggle"]
                if (toggleSid != null && userId != null) {
                    val olda: Set<String> = state.achievements[toggleSid] ?: emptySet()
                    update(
                            if (olda.contains(userId)) {
                                state.copy(
                                        achievements =
                                                state.achievements + (toggleSid to (olda - userId))
                                )
                            } else {
                                state.copy(
                                        achievements =
                                                state.achievements + (toggleSid to (olda + userId))
                                )
                            }
                    )
                }

                body {
                    h1 { text("Git Workshop - Progress Monitor") }
                    if (userId == null) {
                        form(action = "/register", method = FormMethod.get) {
                            label { text("Dein Name/Alias:") }
                            input(name = "newAlias") { value = "torfnase" }
                        }
                    } else {
                        h2 { text("Hallo ${state.participants[userId]}!") }
                        h2 { text("Aufgabe ${state.aktuelleAufgabe.first}") }
                        state.aktuelleAufgabe.second.forEach { schritt ->
                            p {
                                val sid =
                                        abs((state.aktuelleAufgabe.first to schritt).hashCode())
                                                .toString()
                                text(schritt)
                                a(href = "/?id=$userId&toggle=$sid") { +(if(state.achievements[sid]?.contains(userId) ?: false) " erledigt" else " offen"  ) }
                            }
                        }
                    }
                }
            }
        }

        get("/register") {
            val newAlias = call.parameters["newAlias"] ?: throw Exception("Missing parameter newAlias")
            val newUserId = abs("participant/$newAlias".hashCode()).toString()
            if (state.participants[newUserId] == null) {
                update(state.copy(participants = state.participants + (newUserId to newAlias)))
                call.sessions.set(UserSession(newUserId))
                call.respondRedirect("/")
            } else {
                call.respondHtml {
                    body { p { +"Alias $newAlias ist bereits vergeben." } }
                }
            }

        }
    }
}

fun Application.adminModule() {
    routing {
        get("/") {
            val selected = call.parameters["select"] ?: "?"
            update(
                    state.copy(
                            aktuelleAufgabe = state.aufgaben.find { it.first == selected }
                                            ?: state.aufgaben.first()
                    )
            )
            call.respondHtml {
                body {
                    h1 { text("Git Workshop - Progress Monitor") }
                    h2 { text("Aktuelle Aufgabe") }
                    val totalNum = state.participants.size
                    state.aktuelleAufgabe.also { (aufgabe, schritte) ->
                        schritte.forEach { schritt ->
                            val sid =
                                    abs((state.aktuelleAufgabe.first to schritt).hashCode())
                                            .toString()
                            p { +"$aufgabe/$schritt ${state.achievements[sid]?.size}/$totalNum ${state.achievements[sid]?.map { state.participants[it] }}" }
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
}
