package com.assignment.loginandpay.data.dto

import com.assignment.loginandpay.domain.model.Payment
import com.google.gson.annotations.SerializedName

data class PaymentDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("amount")
    val amount: Any? = null,
    @SerializedName("created")
    val created: Long? = null,
)

fun PaymentDTO.toPayment(): Payment {
    return Payment(
        id = id,
        title = title,
        created = created,
        amount = when {
            amount!= null -> amount.toString()
            else -> "--"
        }
    )
}