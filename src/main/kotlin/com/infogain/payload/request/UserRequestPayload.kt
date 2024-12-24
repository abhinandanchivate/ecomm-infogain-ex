package com.infogain.payload.request

import kotlinx.serialization.Serializable
import java.util.UUID
@Serializable
data class UserRequestPayload(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val roleId: String
)
