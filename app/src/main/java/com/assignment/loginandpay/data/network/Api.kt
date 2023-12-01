package com.assignment.loginandpay.data.network

import com.assignment.loginandpay.data.dto.ServerResultDTO
import com.assignment.loginandpay.data.dto.PaymentDTO
import com.assignment.loginandpay.data.dto.PaymentsDTO
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("app-key: 12345", "v: 1")
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("login") login: String,
        @Field("password") password: String
    ): Response<ServerResultDTO>

    @Headers("app-key: 12345", "v: 1")
    @GET("payments")
    suspend fun getPayments(@Header("token") token: String): Response<PaymentsDTO>
}