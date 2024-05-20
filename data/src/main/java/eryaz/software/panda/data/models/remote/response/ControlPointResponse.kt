package eryaz.software.panda.data.models.remote.response

import com.google.gson.annotations.SerializedName

data class ControlPointResponse(
    @SerializedName("code")
    val code: String?,
    @SerializedName("definition")
    val definition: String?,
    @SerializedName("id")
    val id: Int
)