package com.example.xmis_project.network.dto

import com.example.xmis_project.models.Place

data class RecommendationsResponse(
    val success: Boolean,
    val recommendations: List<Place>,
    val count: Int,
    val user_preferences: Map<String, Any>?,
    val error_message: String?
)