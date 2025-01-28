package com.example.randomusers.model

import kotlinx.serialization.Serializable

@Serializable
data class UserVO(
    val results: List<User>,
)
