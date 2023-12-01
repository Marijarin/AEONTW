package com.assignment.loginandpay.domain.model

import com.google.gson.annotations.SerializedName

data class ServerError (
    val code: Int,
    val message: String
)