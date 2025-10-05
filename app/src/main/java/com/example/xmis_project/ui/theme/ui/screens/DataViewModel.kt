package com.example.xmis_project.ui.theme.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.misis_xmis.network.RetrofitClient
import com.example.xmis_project.models.Message
import com.example.xmis_project.network.dto.RecommendationsRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {
    private val _messages = MutableStateFlow(
        listOf(
            Message("Приветствую вас!", false, null),
        ))
    val messages: StateFlow<List<Message>> = _messages

    private val _userLastRequest = MutableStateFlow("Введите запрос")
    val userLastRequest: StateFlow<String> = _userLastRequest

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun sendUserPrompt(userPrompt: String) {
        viewModelScope.launch {
            _isLoading.value = true

            val userMessage = Message(userPrompt, true, null)
            _messages.value = _messages.value + userMessage

            try {
                val request = RecommendationsRequest(
                    user_prompt = userPrompt,
                    limit = 10
                )

                val response = RetrofitClient.apiService.getRecommendations(request)

                if (response.success && response.recommendations.isNotEmpty()) {
                    val botMessage = Message(
                        text = "Нашел ${response.recommendations.size} подходящих мест:",
                        isUser = false,
                        places = response.recommendations
                    )
                    _messages.value = _messages.value + botMessage
                } else {
                    val errorMessage = Message(
                        text = "К сожалению, не удалось найти подходящие места. Попробуйте изменить запрос.",
                        isUser = false,
                        places = null,
                    )
                    _messages.value = _messages.value + errorMessage
                }
            } catch (e: Exception) {
                Log.e("API_CALL", "Ошибка при запросе рекомендаций: ${e.message}")
                val errorMessage = Message(
                    text = "Произошла ошибка при поиске мест. Проверьте подключение к интернету.",
                    isUser = false,
                    places = null
                )
                _messages.value = _messages.value + errorMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateUserLastRequest(request: String) {
        if (request.length > 20) _userLastRequest.value = request.removeRange(20, request.length) + "..."
        else _userLastRequest.value = request
    }

    fun clearData() {
        _messages.value = listOf(
            Message("Приветствую вас!", false, null),
        )
        _userLastRequest.value = "Введите запрос"
    }

}