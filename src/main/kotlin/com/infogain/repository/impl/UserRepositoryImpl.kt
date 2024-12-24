package com.infogain.repository.impl

import com.infogain.dto.Role
import com.infogain.dto.User
import com.infogain.payload.request.UserRequestPayload
import com.infogain.repository.UserRepository
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class UserRepositoryImpl : UserRepository {
    override fun create(userPayload: UserRequestPayload): User = transaction {
        val role = Role.findById(UUID.fromString(userPayload.roleId))
            ?: throw IllegalArgumentException("Role with ID ${userPayload.roleId} not found.")

        User.new {
            firstName = userPayload.firstName
            lastName = userPayload.lastName
            email = userPayload.email
            password = userPayload.password
            this.role = role

        }
    }

    override fun findAll(): List<User> = transaction {
        User.all().toList()
    }

    override fun findById(id: String): User? = transaction {
        User.findById(UUID.fromString(id))
    }

    override fun update(id: String, userPayload: UserRequestPayload): User? = transaction {
        val user = User.findById(UUID.fromString(id)) ?: return@transaction null
        val role = Role.findById(UUID.fromString(userPayload.roleId))
            ?: throw IllegalArgumentException("Role with ID ${userPayload.roleId} not found.")

      user.apply {
            firstName = userPayload.firstName
            lastName = userPayload.lastName
            email = userPayload.email
            password = userPayload.password
            this.role = role
        }
    }

    override fun delete(id: String): Boolean = transaction {
        val user = User.findById(UUID.fromString(id))
        if (user != null) {
            user.delete()
            true
        } else {
            false
        }
    }
}
