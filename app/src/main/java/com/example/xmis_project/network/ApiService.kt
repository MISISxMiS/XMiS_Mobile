package com.example.xmis_project.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("get_list")
    suspend fun getUsers()

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") postId: Int)

    @POST("chat/send")
    suspend fun sendMessage()
}