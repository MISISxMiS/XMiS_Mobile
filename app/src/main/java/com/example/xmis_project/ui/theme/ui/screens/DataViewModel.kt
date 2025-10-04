package com.example.xmis_project.ui.theme.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.util.Log
import com.example.misis_xmis.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataViewModel : ViewModel() {
    private val _messages = MutableStateFlow(
        listOf(
            Message("Привет! Это твой собеседник.", false),
            Message("Привет! Как дела?", true),
            Message("Отлично, спасибо. Я — тестовый бот.", false),
            Message("Начинаем тестировать функционал чата.", true)
        ))
    val messages: StateFlow<List<Message>> = _messages

    fun fetchData() {
        viewModelScope.launch {
            try {
                // --- Запрос 1: Получить список пользователей ---
                val usersResponse = RetrofitClient.apiService.getUsers()

//                if (usersResponse.isSuccessfu) {
//                    val users = usersResponse.body()
//                    Log.d("API_CALL", "Получено пользователей: ${users?.size}")
                    // Обновление LiveData/StateFlow с данными пользователей
//                } else {
//                    Log.e("API_CALL", "Ошибка при получении пользователей: ${usersResponse.code()}")
//                }

            } catch (e: Exception) {
                Log.e("API_CALL", "Произошла ошибка сети/парсинга: ${e.message}")
            }
        }
    }
}