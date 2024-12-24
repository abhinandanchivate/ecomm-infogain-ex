package com.infogain.repository.impl

import com.infogain.dto.Role
import com.infogain.payload.request.RoleRequestPayload
import com.infogain.repository.RoleRepository


import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class RoleRepositoryImpl : RoleRepository {
    override fun create(rolePayload: RoleRequestPayload): Role = transaction {
        Role.new {
            name = rolePayload.name
            description = rolePayload.description
        }
    }

    override fun findAll(): List<Role> = transaction {
        Role.all().toList()
    }

    // we will accept UUIDs interms of String
    // we need to convert them interms of UUID object

    override fun findById(id: String): Role? = transaction {
        Role.findById(UUID.fromString(id))
    }

    override fun delete(id: String): Boolean = transaction {
        val role = Role.findById(UUID.fromString(id))
        if (role != null) {
            role.delete()
            true
        } else {
            false
        }
    }
}
