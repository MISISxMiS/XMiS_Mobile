package com.example.xmis_project.network

class ApiRepository(private val api: ApiService) {
    suspend fun sendMessage(message: String) {
        return api.sendMessage()
    }
}
