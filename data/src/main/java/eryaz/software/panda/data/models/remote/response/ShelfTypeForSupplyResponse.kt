package eryaz.software.panda.data.models.remote.response

import com.google.gson.annotations.SerializedName

data class ShelfTypeForSupplyResponse(

    @SerializedName("code")
    val code: String?,

    @SerializedName("definition")
    val definition: String?

)
