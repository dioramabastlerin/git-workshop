package de.kapitel26.gitsamplebuilder

import io.ktor.application.call
import io.ktor.application.Application
import io.ktor.response.respondTextWriter
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.html.respondHtml
import kotlinx.html.*
import com.fasterxml.jackson.module.kotlin.*  
import java.io.File
import kotlin.text.toIntOrNull
import kotlin.math.abs

fun main() {
    val adminServer = embeddedServer(Netty, port = 8040) { adminModule() }
    val participantsServer = embeddedServer(Netty, port = 8080) { participantsModule() }
    adminServer.start(wait = false)
    participantsServer.start(wait = true)
}

data class Progress(
    val aufgaben: List<Pair<String, List<String>>>,
    val aktuelleAufgabe: Pair<String, List<String>>,
    val participants: Map<String,String>
)

val mapper = jacksonObjectMapper()

var state : Progress = 
    readAufgaben().let { aufgaben ->
        println(aufgaben)
        Progress(   
            aufgaben,
            aufgaben.first(),
            emptyMap()
        )    
    }

    
fun update(newState: Progress) {
    state = newState
}

fun readAufgaben() : List<Pair<String, List<String>>> {
    val s: String = File("build/git-uebungen/aufgaben.json").readText()
    return mapper.readValue(s)
}

fun Application.participantsModule() {
    routing {
        get("/") {
            call.respondHtml {
                val userId = call.parameters["id"]

                body {
                    h1 { text("Git Workshop - Progress Monitor") }
                    if(userId == null) {
                        form(action = "/register", method = FormMethod.get) {
                            label { text("Dein Name/Alias:") }
                            input(name = "newAlias") {value = "torfnase"}
                        }
                    } else {
                        h2 { text("Hallo ${state.participants[userId]}!") }
                        h2 { text("Aufgabe ${state.aktuelleAufgabe.first}" ) }
                        state.aktuelleAufgabe.second.forEach { p { text(it) } }        
                    }
                }
            }
        }

        get("/register") {
            call.respondHtml {
                val newAlias = call.parameters["newAlias"] ?: throw Exception("Missing parameter newAlias")
                val newUserId = abs("participant/$newAlias".hashCode()).toString()
                    if( state.participants[newUserId] == null) {
                        update(state.copy(participants = state.participants +(newUserId to newAlias)))
                        
                        head {
                            meta(content = "0; url=/?id=$newUserId") { httpEquiv="refresh"}
                        }
                    } else {
                        body {
                            p { +"Alias $newAlias ist bereits vergeben."}
                        }
                    }    
           }
        }
    }
}

fun Application.adminModule() {
    routing {
        get("/") {
            val selected = call.parameters["select"] ?: "?"
            update(state.copy(aktuelleAufgabe = state.aufgaben.find { it.first == selected } ?: state.aufgaben.first()))
            call.respondHtml {
                body {
                    h1 { text("Git Workshop - Progress Monitor") }
                    p { text("map: $state.participants")}
                    h2 { text("Aktuelle Aufgabe") }
                    form(action = "/", method = FormMethod.get) {
                        state.aufgaben.map { it.first }
                            .forEach {
                                input(name = "select") {  
                                    value= it
                                    type = InputType.radio
                                    checked = it == state.aktuelleAufgabe.first
                                    onChange = "this.form.submit()"
                                    text(it)
                                }
                                br {}
                                // <input type="radio" id="html" name="fav_language" value="HTML">
                                // <label for="html">HTML</label><br>
                                
                            }
                            //input() { type = InputType.submit }
                    }

                }
            }
        }
    }
}
