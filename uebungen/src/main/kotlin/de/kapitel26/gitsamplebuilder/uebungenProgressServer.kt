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

fun main() {
    println("Starting the progress monitor!")
    val adminServer = embeddedServer(Netty, port = 8040) { adminModule() }
    adminServer.start(wait = false)
    val participantsServer = embeddedServer(Netty, port = 8080) { htmlModule() }
    participantsServer.start(wait = true)
}

val participants = mutableMapOf<Int, String>()
val aufgaben = readAufgaben()
var aktuelleAufgabe = aufgaben.first()

fun readAufgaben() : List<Pair<String, List<String>>> {
    val s: String = File("build/git-uebungen/aufgaben.json").readText()
    val mapper = jacksonObjectMapper()
    return mapper.readValue(s)
}

fun Application.htmlModule() {


    routing {
        get("/") {
            call.respondHtml {
                body {
                    h1 { text("Git Workshop - Progress Monitor") }
                    form(action = "/register", method = FormMethod.get) {
                        label { text("Dein Name/Alias:") }
                        input(name = "alias") {value = "torfnase"}
                    
                    }
                }
            }
        }

        get("/register") {
            val alias = call.parameters["alias"]
            val id = "participant/$alias".hashCode()
            if(alias is String)
                participants[id] = alias
            call.respondHtml() { 
                body {
                    p { text("Du bist registriert als $alias") }
                    h3 { text("Debug") } 
                    a( href="/progress?id=$id" ) { text("Hierhin") }                   
                }
            }
        }

        get("/progress") {
            call.respondHtml() { 
                body {
                    val id = call.parameters["id"]?.toIntOrNull() ?: throw Exception()
                    h2 { text("Hallo ${participants[id]}!") }
                    h2 { text("Aufgabe ${aktuelleAufgabe.first}" ) }
                    aktuelleAufgabe.second.forEach { p { text(it) } }
                }
                
            }
        }
    }
}

fun Application.adminModule() {
    routing {
        get("/") {
            val selected = call.parameters["select"] ?: "?"
            aktuelleAufgabe = aufgaben.find { it.first == selected } ?: aufgaben.first()
            call.respondHtml {
                body {
                    h1 { text("Git Workshop - Progress Monitor") }
                    p { text("map: $participants")}
                    h2 { text("Aktuelle Aufgabe") }
                    form(action = "/", method = FormMethod.get) {
                        aufgaben.map { it.first }
                            .forEach {
                                input(name = "select") {  
                                    value= it
                                    type = InputType.radio
                                    checked = it == aktuelleAufgabe.first
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
