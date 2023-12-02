package com.assignment.loginandpay.data.repository

import android.content.Context
import com.assignment.loginandpay.data.dto.toPayment
import com.assignment.loginandpay.data.network.ApiService
import com.assignment.loginandpay.domain.model.Payment
import com.assignment.loginandpay.domain.repository.PaymentRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @ApplicationContext
    private val context: Context
) : PaymentRepository {

    private val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val token = prefs.getString("token", "")

    override suspend fun getAll(): List<Payment> {
        try {
            val response = token?.let {
                apiService.getPayments(it)
            }
            if (!response?.isSuccessful!!) {
                throw Error(response.message())
            }
            val paymentsDTO = response.body() ?: throw throw Error(response.message())
            if (paymentsDTO.success) {
                return paymentsDTO.payments.map { it.toPayment() }
            }
            return emptyList()
        } catch (e: IOException) {
            throw e.fillInStackTrace()
        } catch (e: Exception) {
            throw e.fillInStackTrace()
        }
    }

    override fun deleteToken() {
        with(prefs.edit()) {
            clear()
            apply()
        }
    }

}
