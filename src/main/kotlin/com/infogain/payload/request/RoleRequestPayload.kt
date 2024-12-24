package com.infogain.payload.request

import kotlinx.serialization.Serializable


@Serializable
data class RoleRequestPayload(
    val name: String,
    val description: String?
)
