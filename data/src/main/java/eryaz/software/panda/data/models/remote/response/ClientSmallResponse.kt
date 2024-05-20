package eryaz.software.panda.data.models.remote.response

import com.google.gson.annotations.SerializedName

data class ClientSmallResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("code")
    val code: String,

    @SerializedName("name")
    val name: String?
)
