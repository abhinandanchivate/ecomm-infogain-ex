package com.infogain.routes

import com.infogain.payload.request.RoleRequestPayload
import com.infogain.service.RoleService
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.roleRoutes(roleService: RoleService) {
    route("/roles") {
        post {
            val payload = call.receive<RoleRequestPayload>()
            call.respond(roleService.create(payload))
        }
        get {
            call.respond(roleService.findAll())
        }
        get("/{id}") {
            val id = call.parameters["id"]!!
            call.respond(roleService.findById(id))
        }
        delete("/{id}") {
            val id = call.parameters["id"]!!
            call.respond(mapOf("deleted" to roleService.delete(id)))
        }
    }
}
