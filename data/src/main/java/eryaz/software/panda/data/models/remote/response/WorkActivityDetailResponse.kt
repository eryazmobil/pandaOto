package eryaz.software.panda.data.models.remote.response

import com.google.gson.annotations.SerializedName

data class WorkActivityDetailResponse(
    @SerializedName("workActivity")
    val workActivity: WorkActivityResponse,

    @SerializedName("sourceId")
    val sourceId: Int,

    @SerializedName("product")
    val product: ProductResponse,

    @SerializedName("quantity")
    val quantity: Double,

    @SerializedName("placedCollectedQty")
    val placedCollectQuantity: Double,

    @SerializedName("oldShelf")
    val oldShelf: ShelfResponse,

    @SerializedName("newShelf")
    val newShelf: ShelfResponse,

    @SerializedName("placedReplenishmentQty")
    val placedReplenishmentQty: Int,

    @SerializedName("id")
    val id: Int

)
