package com.infogain.service.impl

import com.infogain.exceptions.NotFoundException
import com.infogain.extensions.toResponse
import com.infogain.payload.request.RoleRequestPayload
import com.infogain.payload.response.RoleResponsePayload
import com.infogain.repository.RoleRepository
import com.infogain.service.RoleService

import java.time.format.DateTimeFormatter

class RoleServiceImpl(private val roleRepository: RoleRepository) : RoleService {
    override fun create(rolePayload: RoleRequestPayload): RoleResponsePayload {
        val role = roleRepository.create(rolePayload)
        return role.toResponse()
    }

    override fun findAll(): List<RoleResponsePayload> {
        return roleRepository.findAll().map { it.toResponse() }
    }

    override fun findById(id: String): RoleResponsePayload {
        val role = roleRepository.findById(id) ?: throw NotFoundException("Role with ID $id not found.")
        return role.toResponse()
    }

    override fun delete(id: String): Boolean {
        return roleRepository.delete(id)
    }
}
