package com.example.xmis_project.network.dto

data class RecommendationsRequest(
    val user_prompt: String,
    val limit: Int = 10
)