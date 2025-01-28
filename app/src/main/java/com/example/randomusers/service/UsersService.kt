package com.example.randomusers.service

import com.example.randomusers.model.UserVO
import com.example.randomusers.network.Ktor.client
import com.example.randomusers.utils.BASE_URL
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType

class UsersService {

    suspend fun getRandomUsers(): UserVO = client.get(BASE_URL) {
        contentType(ContentType.Application.Json)
        parameter("results", 5000)
    }.body()

}