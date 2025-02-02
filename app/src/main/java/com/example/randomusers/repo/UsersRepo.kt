package com.example.randomusers.repo

import com.example.randomusers.model.UserVO
import com.example.randomusers.model.Resource
import com.example.randomusers.service.UsersService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRepo {

    private val service = UsersService()

    suspend fun getUsers(reqCount: Int): Resource<UserVO> =
        withContext(Dispatchers.Default) {
            try {
                val response = service.getRandomUsers(reqCount)
                Resource.Success(response)
            } catch (e: Exception) {
                Resource.Error(error = e.message ?: "")
            }
        }

}