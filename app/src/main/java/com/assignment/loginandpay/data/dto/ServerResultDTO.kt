package com.assignment.loginandpay.data.dto

import com.assignment.loginandpay.domain.model.ServerResult
import com.google.gson.annotations.SerializedName

data class ServerResultDTO(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("error")
    val error: ServerErrorDTO? = null,
    @SerializedName("response")
    val response: ServerResponseDTO? = null
)
fun ServerResultDTO.toServerResult(): ServerResult {
    return ServerResult(
        success = success,
        error = error?.toServerError(),
        response = response?.toServerResponse()
    )
}