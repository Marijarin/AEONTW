package com.assignment.loginandpay.data.dto

import com.google.gson.annotations.SerializedName

data class PaymentsDTO (
    @SerializedName ("success")
    val success: Boolean,
    @SerializedName("response")
    val payments: List<PaymentDTO> = emptyList()
)