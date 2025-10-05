package com.example.xmis_project.network

import com.example.xmis_project.network.dto.RecommendationsRequest
import com.example.xmis_project.network.dto.RecommendationsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("/recommendations")
    suspend fun getRecommendations(@Body request: RecommendationsRequest): RecommendationsResponse

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") postId: Int)

    @POST("chat/send")
    suspend fun sendMessage()
}