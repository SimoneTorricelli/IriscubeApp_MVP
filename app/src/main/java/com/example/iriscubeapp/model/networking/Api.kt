package com.example.iriscubeapp.model.networking

import SampleData
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.Exception

interface WebService {
    @GET("")
    suspend fun getMovement() : Result<SampleData> {
        if(true){
            return Result.success(SampleData(0,"1",2.0,"3"))
        }
        else{
            return Result.failure(TodoException("ERRORE",Exception()))
        }
    }
}