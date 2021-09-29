package com.example.iriscubeapp.model.networking

import SampleData
import retrofit2.Response
import retrofit2.http.GET

interface WebService {
    @GET("4da288b1-5179-4202-8cde-9fb6895f7c69")
    suspend fun getMovement(): Response<Array<SampleData>>
}