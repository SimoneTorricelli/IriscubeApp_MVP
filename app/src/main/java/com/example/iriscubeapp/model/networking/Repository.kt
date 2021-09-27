package com.example.iriscubeapp.model.networking

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Repository {
    private var client = RetrofitClient.retrofit

    suspend fun getMovement() = withContext(Dispatchers.IO) {
        try {
            client.getMovement()
        } catch (cause: Throwable) {
            throw TodoException("Unable to retrieve todo", cause)
        }
    }
}