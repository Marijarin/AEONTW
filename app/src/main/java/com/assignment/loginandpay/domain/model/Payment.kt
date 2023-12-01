package com.assignment.loginandpay.domain.model

data class Payment (
    val id: Int,
    val title: String,
    val amount: String?,
    val created: Long?
)