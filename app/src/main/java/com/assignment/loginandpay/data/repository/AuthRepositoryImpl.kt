package com.assignment.loginandpay.data.repository

import android.content.Context
import com.assignment.loginandpay.data.dto.toServerResult
import com.assignment.loginandpay.data.network.ApiService
import com.assignment.loginandpay.domain.model.ServerResult
import com.assignment.loginandpay.domain.repository.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @ApplicationContext
    private val context: Context
) : AuthRepository {
    private val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    override suspend fun login(login: String, pw: String) {
        try {
            val response = apiService.login(login, pw)
            if (!response.isSuccessful) {
                throw Error(response.message())
            }
            val result = response.body() ?: throw Error(response.message())
            if(result.success) {
                writeToken(result.toServerResult())
            }
        } catch (e: IOException) {
            throw e.fillInStackTrace()
        } catch (e: Exception) {
            throw e.fillInStackTrace()
        }
    }

    private fun writeToken(result: ServerResult) {
        if (result.success) {
            val token = result.response?.token ?: ""
            putToken(token)
        }
    }

    private fun putToken(token: String) {
        with(prefs.edit()) {
            putString("token", token)
            apply()
        }
    }
}