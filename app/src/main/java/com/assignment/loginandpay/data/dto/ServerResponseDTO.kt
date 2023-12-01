package com.assignment.loginandpay.data.dto

import com.assignment.loginandpay.domain.model.ServerResponse
import com.google.gson.annotations.SerializedName

data class ServerResponseDTO (
    @SerializedName("token")
    val token: String
)
fun ServerResponseDTO.toServerResponse(): ServerResponse {
    return ServerResponse(
        token = token
    )
}