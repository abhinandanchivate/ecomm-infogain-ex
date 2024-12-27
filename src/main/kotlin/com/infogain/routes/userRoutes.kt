package com.infogain.routes

import com.infogain.payload.request.UserRequestPayload
import com.infogain.service.UserService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.userRoutes(userService: UserService) {
    route("/users") {
        post {
            val payload = call.receive<UserRequestPayload>() // @RequestBody
            call.respond(userService.create(payload))
        }
        get {
            call.respond(userService.findAll())
        }
        get("/{id}") {
            val id = call.parameters["id"]?: return@get call.respond(HttpStatusCode.BadRequest,"missing userID")
            print(UUID.fromString(id))
            call.respond(userService.findById(id))
        }
        put("/{id}") {
            val id = call.parameters["id"]!!
            val payload = call.receive<UserRequestPayload>()
            call.respond(userService.update(id, payload))
        }
        delete("/{id}") {
            val id = call.parameters["id"]!!
            call.respond(mapOf("deleted" to userService.delete(id)))
        }
    }
}
