package com.example.iriscubeapp.model.networking

import com.google.gson.annotations.SerializedName

data class Todo(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("completed")
    val completed: Boolean = false
)

data class MovementException(override val message: String, override val cause: Throwable) : Exception()