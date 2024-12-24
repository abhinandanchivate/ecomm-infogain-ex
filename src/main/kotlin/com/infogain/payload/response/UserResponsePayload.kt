package com.infogain.payload.response

import kotlinx.serialization.Serializable
import java.util.UUID
@Serializable
data class UserResponsePayload(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val roleId: String,
    val roleName: String,
    val createdAt: String,
    val updatedAt: String
)
