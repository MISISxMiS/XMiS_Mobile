package com.example.xmis_project.network

import com.example.xmis_project.network.dto.RecommendationsRequest
import com.example.xmis_project.network.dto.RecommendationsResponse
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("/recommendations")
    suspend fun getRecommendations(@Body request: RecommendationsRequest): RecommendationsResponse

    @GET("/photos/{full_path}")
    suspend fun getPhoto(@Path("full_path") fullPath: String): retrofit2.Response<String>



}