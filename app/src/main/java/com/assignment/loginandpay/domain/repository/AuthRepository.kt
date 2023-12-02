package com.assignment.loginandpay.domain.repository

interface AuthRepository {
    suspend fun login(login: String, pw: String): Boolean
}