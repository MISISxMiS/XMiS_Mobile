package com.example.xmis_project.models

data class Message(val text: String, val isUser: Boolean, val places: List<Place>?)
