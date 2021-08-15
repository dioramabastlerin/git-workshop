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


fun main() {
    println("Starting the progress monitor!")
    val server = embeddedServer(Netty, port = 8080) {
        htmlModule()
    }

    server.start(wait = true)
}

val participants = mutableMapOf<String, Int>()

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
            if(alias is String)
                participants[alias] = 42
            call.respondHtml() { 
                body {
                    p { text("Du bist registriert als $alias") }
                    h3 { text("Debug") }
                    p { text("map: $participants")}
                }
            }
        }
    }
}
