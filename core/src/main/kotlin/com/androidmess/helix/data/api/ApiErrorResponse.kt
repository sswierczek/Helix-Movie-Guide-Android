package com.androidmess.helix.data.api

import com.google.gson.annotations.SerializedName

data class ApiErrorResponse(
    @SerializedName("status_message")
    val message: String,
    @SerializedName("status_code")
    val code: Boolean,
)