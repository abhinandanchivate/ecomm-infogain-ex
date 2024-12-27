package com.infogain.repository.impl

import com.infogain.dto.Role
import com.infogain.dto.User
import com.infogain.payload.request.UserRequestPayload
import com.infogain.repository.UserRepository
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import org.jetbrains.exposed.dao.load
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class UserRepositoryImpl : UserRepository {
    override fun create(userPayload: UserRequestPayload): User = transaction {
        val role = Role.findById(UUID.fromString(userPayload.roleId))
            ?: throw IllegalArgumentException("Role with ID ${userPayload.roleId} not found.")

        // exposed dao will support for lazy loading .

        User.new {
            firstName = userPayload.firstName
            lastName = userPayload.lastName
            email = userPayload.email
            password = userPayload.password
            createdAt= java.time.LocalDateTime.now().toKotlinLocalDateTime()
            updatedAt=java.time.LocalDateTime.now().toKotlinLocalDateTime()
            this.role = role

        }.apply {
            println("$firstName $lastName $createdAt")
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

        println(role.id.toString())
        println(role.name)
      user.apply {
            firstName = userPayload.firstName
            lastName = userPayload.lastName
            email = userPayload.email
            password = userPayload.password
            this.role = role
          updatedAt = java.time.LocalDateTime.now().toKotlinLocalDateTime()
      }

        println("User updated successfully: ${user.id}")
        user.load(User::role)
        user
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
