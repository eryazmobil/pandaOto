package eryaz.software.panda.data.models.remote.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error")
    val error: ErrorModel
)