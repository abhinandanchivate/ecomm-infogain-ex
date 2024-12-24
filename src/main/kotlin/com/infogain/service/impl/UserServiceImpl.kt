package com.infogain.service.impl

import com.infogain.exceptions.NotFoundException
import com.infogain.extensions.toResponse
import com.infogain.payload.request.UserRequestPayload
import com.infogain.payload.response.UserResponsePayload
import com.infogain.repository.UserRepository
import com.infogain.service.UserService



class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun create(userPayload: UserRequestPayload): UserResponsePayload {
        val result = userRepository.create(userPayload)
        return result.toResponse()
    }

    override fun findAll(): List<UserResponsePayload> =
        userRepository.findAll().map { it.toResponse() }

    override fun findById(id: String): UserResponsePayload =
        userRepository.findById(id)?.toResponse()
            ?: throw NotFoundException("User with id $id not found")

    override fun update(id: String, userPayload: UserRequestPayload): UserResponsePayload {
        val result = userRepository.update(id, userPayload)
        return result?.toResponse() ?: throw NotFoundException("User not found")
    }

    override fun delete(id: String): Boolean = userRepository.delete(id)
}
