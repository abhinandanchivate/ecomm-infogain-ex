package com.infogain.service.impl

import com.infogain.exceptions.NotFoundException
import com.infogain.exceptions.UserNotFoundException
import com.infogain.extensions.toResponse
import com.infogain.payload.request.UserRequestPayload
import com.infogain.payload.response.RoleResponsePayload
import com.infogain.payload.response.UserResponsePayload
import com.infogain.repository.UserRepository
import com.infogain.service.UserService
import org.jetbrains.exposed.sql.transactions.transaction

// exposed dao
//
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun create(payload: UserRequestPayload): UserResponsePayload {
        return transaction {
            // Ensure the DAO operations are wrapped in this transaction block
            userRepository.create(payload).toResponse()
        }
    }

    override fun findAll(): List<UserResponsePayload> =
        transaction {
            userRepository.findAll().map { it.toResponse() }
        }
//     fun findById(id: String): UserResponsePayload =
//        userRepository.findById(id)?.toResponse()
//            ?: throw NotFoundException("User with id $id not found")
    override fun findById(userId: String): UserResponsePayload {
    return transaction {
        val user = userRepository.findById(userId) ?: throw UserNotFoundException("User with ID $userId not found")

        user.toResponse()
    }
}

    override fun update(id: String, userPayload: UserRequestPayload): UserResponsePayload {
        val result = userRepository.update(id, userPayload)
        return result?.toResponse() ?: throw NotFoundException("User not found")
    }

    override fun delete(id: String): Boolean = userRepository.delete(id)
}
