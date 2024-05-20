package eryaz.software.panda.data.models.remote.response

import com.google.gson.annotations.SerializedName

data class ProductShelfSupplyResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("type")
    val type: Int?,
    @SerializedName("product")
    val product: ProductResponse?,
    @SerializedName("shelf")
    val shelf: ShelfResponse?,
    @SerializedName("quantity")
    val quantity: Double?,
    @SerializedName("quantityIn")
    val quantityIn: Double?,
    @SerializedName("quantityOut")
    val quantityOut: Double?


)
