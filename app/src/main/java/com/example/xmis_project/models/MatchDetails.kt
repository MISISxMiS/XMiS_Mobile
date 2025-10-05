package com.example.xmis_project.models

data class MatchDetails(
    val entity_types_match: List<String>,
    val atmosphere_match: List<String>,
    val purpose_match: List<String>,
    val budget_match: Boolean,
    val features_match: List<String>
)