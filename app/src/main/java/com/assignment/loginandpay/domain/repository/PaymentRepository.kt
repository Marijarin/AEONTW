package com.assignment.loginandpay.domain.repository

import com.assignment.loginandpay.domain.model.Payment

interface PaymentRepository {
    suspend fun getAll(): List<Payment>
    fun deleteToken(){}
}