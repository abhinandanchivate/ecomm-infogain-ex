package com.infogain

import com.infogain.config.initPostgresDatabase
import com.infogain.routes.rootRoutes
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*


fun main(args: Array<String>) {
    io.ktor.server.tomcat.jakarta.EngineMain.main(args)
}

fun Application.module() {


    initPostgresDatabase()
    install(ContentNegotiation){
       json()
    }

    routing {
        rootRoutes()
    }


}
