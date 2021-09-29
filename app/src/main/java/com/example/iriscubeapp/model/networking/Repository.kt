package com.example.iriscubeapp.model.networking

import MovementException
import com.example.iriscubeapp.contract.NetworkTestContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class Repository(var client:WebService) : NetworkTestContract.Repository{

    init {
        client = RetrofitClient.retrofit
    }

    override suspend fun getMovement() = withContext(Dispatchers.IO) {
        try {
            client.getMovement()
        } catch (cause: Throwable) {
            throw MovementException("Unable to retrieve movement", cause)
        }
    }
}