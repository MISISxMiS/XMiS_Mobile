<h1>XMiS Mobile Application</h1>

**📱 Мобильное приложение для рекомендаций мест**

<h2>🚀 Основные функции</h2> <ul> <li>💬 Чат с системой рекомендаций</li> <li>🗺️ Интеграция с картами (DGIS SDK)</li> <li>📍 Просмотр рекомендованных мест</li> <li>💾 Локальное кэширование данных</li> <li>🔄 Сетевая синхронизация</li> <li>🎨 Современный Material Design 3 интерфейс</li> </ul><h2>🛠 Технологии и зависимости</h2><h3>Основной стек</h3> <ul> <li><b>Kotlin</b> - основной язык разработки</li> <li><b>Jetpack Compose</b> - современный декларативный UI фреймворк</li> <li><b>Android SDK</b> 34-36</li> <li><b>Gradle</b> - система сборки</li> </ul><h3>Jetpack Components</h3> <ul> <li><b>Compose BOM</b> - Bill of Materials для управления версиями Compose</li> <li><b>Compose UI</b> - основные компоненты интерфейса</li> <li><b>Compose Material3</b> - Material Design 3 компоненты</li> <li><b>Compose Navigation</b> - навигация между экранами</li> <li><b>Compose Activity</b> - интеграция с Activity</li> <li><b>Lifecycle Runtime KTX</b> - расширения для работы с жизненным циклом</li> </ul><h3>Сетевые запросы</h3> <ul> <li><b>Retrofit 2</b> - типобезопасный HTTP клиент</li> <li><b>Gson Converter</b> - конвертер для JSON сериализации/десериализации</li> <li><b>OkHttp</b> - HTTP клиент с поддержкой интерсепторов</li> <li><b>Logging Interceptor</b> - логирование сетевых запросов</li> </ul><h3>Локальное хранилище</h3> <ul> <li><b>Room</b> - абстракция над SQLite для локальной базы данных</li> <li><b>Room KTX</b> - Kotlin расширения для Room</li> </ul><h3>Дополнительные библиотеки</h3> <ul> <li><b>Picasso</b> - загрузка и кэширование изображений</li> <li><b>Core KTX</b> - Kotlin расширения для Android Framework</li> </ul><h3>Тестирование</h3> <ul> <li><b>JUnit</b> - модульное тестирование</li> <li><b>AndroidX Test</b> - инструменты для UI тестирования</li> <li><b>Compose UI Test</b> - тестирование Compose интерфейсов</li> </ul><h2>🔌 Обращения к API</h2><h3>Интерфейс API сервиса</h3>

<pre>
package com.example.misis_xmis.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    
    /**
     * Получение списка пользователей
     * Endpoint: GET /get_list
     */
    @GET("get_list")
    suspend fun getUsers(): Response<List<User>>
    
    /**
     * Получение поста по ID
     * Endpoint: GET /posts/{id}
     * @param postId ID поста
     */
    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") postId: Int): Response<Post>
    
    /**
     * Отправка сообщения в чат
     * Endpoint: POST /chat/send
     */
    @POST("chat/send")
    suspend fun sendMessage(@Body message: MessageRequest): Response<MessageResponse>
}
</pre>

<h3>Пример использования в ViewModel</h3>
class ChatViewModel(
    private val apiService: ApiService
) : ViewModel() {
    
    // Получение списка пользователей
    suspend fun loadUsers() {
        try {
            val response = apiService.getUsers()
            if (response.isSuccessful) {
                val users = response.body()
                // Обработка данных
            } else {
                // Обработка ошибки
            }
        } catch (e: Exception) {
            // Обработка исключения
        }
    }
    
    // Получение поста по ID
    suspend fun loadPost(postId: Int) {
        try {
            val response = apiService.getPostById(postId)
            if (response.isSuccessful) {
                val post = response.body()
                // Обработка данных
            }
        } catch (e: Exception) {
            // Обработка исключения
        }
    }
    
    // Отправка сообщения
    suspend fun sendMessage(text: String) {
        try {
            val messageRequest = MessageRequest(text = text)
            val response = apiService.sendMessage(messageRequest)
            if (response.isSuccessful) {
                // Сообщение отправлено успешно
            }
        } catch (e: Exception) {
            // Обработка исключения
        }
    }
}
  
</pre>

<h3>Конфигурация Retrofit</h3>

<pre>
  object RetrofitClient {
    private const val BASE_URL = "https://your-api-base-url.com/"
    
    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .build()
            .create(ApiService::class.java)
    }
}
</pre>


<h2>🏗 Архитектура приложения</h2>
<pre> 
┌─────────────────┐    ┌──────────────────┐    ┌──────────────┐
│   UI Layer      │    │   Domain Layer   │    │  Data Layer  │
│   (Compose)     │◄──►│   (ViewModels)   │◄──►│ (Repository) │
└─────────────────┘    └──────────────────┘    └──────────────┘
                              │                       │
                              │                       │
                              ▼                       ▼
                     ┌──────────────────┐    ┌──────────────────┐
                     │   State          │    │   Remote         │
                     │   Management     │    │   Data Source    │
                     └──────────────────┘    └──────────────────┘
</pre><h2>🔧 Конфигурация сборки</h2><h3>Версии SDK</h3> <ul> <li><b>compileSdk:</b> 36</li> <li><b>minSdk:</b> 34</li> <li><b>targetSdk:</b> 36</li> </ul><h3>Особенности сборки</h3> <ul> <li><b>BuildConfig</b> - автоматическая генерация конфигурационных полей</li> <li><b>Java 11</b> - версия Java для компиляции</li> <li><b>ProGuard</b> - обфускация для release сборок</li> </ul><h2>🚀 Быстрый старт</h2><ol> <li> <b>Клонирование репозитория</b> <pre><code>git clone &lt;repository-url&gt;</code></pre> </li> <li> <b>Настройка API ключей</b> <pre><code># В local.properties DGIS_API_KEY=your_dgis_api_key_here</code></pre> </li> <li> <b>Запуск приложения</b> <pre><code>./gradlew assembleDebug</code></pre> </li> </ol><h2>📝 Примечания</h2> <ul> <li>Приложение использует современные подходы Android разработки</li> <li>Полная поддержка темной темы через Material3</li> <li>Асинхронные операции реализованы через coroutines</li> <li>Типобезопасные сетевые запросы через Retrofit</li> <li>Локальная база данных через Room для офлайн-работы</li> </ul>
