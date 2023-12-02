package com.assignment.loginandpay.domain.model

data class ServerResult(
    val success: Boolean,
    val error: ServerError? = null,
    val response: ServerResponse? = null
)