package com.assignment.loginandpay.domain.model

import com.google.gson.annotations.SerializedName

data class ServerResult(
    val success: Boolean,
    val error: ServerError? = null,
    val response: ServerResponse? = null
)