package com.infogain

import com.infogain.config.initPostgresDatabase
import com.infogain.routes.rootRoutes
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

import io.ktor.server.application.Application
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

import io.ktor.serialization.kotlinx.json.*


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {


    initPostgresDatabase()
    install(ContentNegotiation) {

      json()
    }

    routing {
        rootRoutes()
    }


}
