package com.example.misis_xmis.network

class ApiRepository(private val api: ApiService) {
    suspend fun sendMessage(message: String) {
        return api.sendMessage()
    }
}
