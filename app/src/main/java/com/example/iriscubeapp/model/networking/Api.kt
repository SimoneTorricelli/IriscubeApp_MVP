package com.example.iriscubeapp.model.networking

import SampleData
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import java.lang.Exception

interface WebService {
    @GET("4da288b1-5179-4202-8cde-9fb6895f7c69")
    suspend fun getMovement(): Response<Array<SampleData>>
}