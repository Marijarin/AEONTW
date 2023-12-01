package com.assignment.loginandpay.data.dto

import com.assignment.loginandpay.domain.model.ServerError
import com.google.gson.annotations.SerializedName

data class ServerErrorDTO (
    @SerializedName("error_code")
    val code: Int,
    @SerializedName("error_msg")
    val message: String
)
fun ServerErrorDTO.toServerError(): ServerError {
    return ServerError(
        code = code,
        message = message
    )
}