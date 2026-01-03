package com.example.coroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import com.example.coroutines.data.UserApiService
import com.example.coroutines.data.UserRepositoryImpl
import com.example.coroutines.ui.theme.CoroutinesTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val userApiService = retrofit.create(UserApiService::class.java)
            val userRepository = UserRepositoryImpl(userApiService)

            CoroutinesTheme {
                LaunchedEffect(true) {
                    userRepository.getUser(2)
                        .onSuccess { user ->
                            Log.d("Retrofit Test", user.firstName + ' ' + user.email)
                        }
                        .onFailure { failure ->
                            Log.d("Retrofit Test", "${failure.message}")
                        }
                }
            }
        }
    }
}