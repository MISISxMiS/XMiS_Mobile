package com.example.xmis_project.models

data class Place(
    val id: Int,
    val title: String,
    val url: String,
    val photo: String,
    val description: String,
    val entity_types: List<String>,
    val atmosphere_tags: List<String>,
    val purpose_tags: List<String>,
    val budget_level: String,
    val features: List<String>,
    val best_time: String,
    val opening_hours: String,
    val is_24_7: Boolean,
    val overall_rating: Double,
    val review_count: Int,
    val relevance_score: Double,
    val match_details: MatchDetails
)
