package com.example.iriscubeapp.model.networking

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Repository {
    private var client = RetrofitClient.retrofit

    suspend fun getMovement(context : Context) = withContext(Dispatchers.IO) {
        try {
            client.getMovement()
        } catch (cause: Throwable) {
            throw MovementException("Unable to retrieve movement", cause)
        }
    }
}