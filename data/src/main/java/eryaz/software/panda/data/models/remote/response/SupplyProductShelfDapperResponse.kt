package eryaz.software.panda.data.models.remote.response

import com.google.gson.annotations.SerializedName

data class SupplyProductShelfDapperResponse(
    @SerializedName("productId")
    var productId: Int,
    @SerializedName("productCode")
    var productCode: String,
    @SerializedName("productDef")
    var productDef: String,
    @SerializedName("gatherAddressId")
    var gatherAddressId: Int,
    @SerializedName("gatherAddress")
    var gatherAddress: String,
    @SerializedName("gatherQuantity")
    var gatherQuantity: Int,
    @SerializedName("stockAddressId")
    var stockAddressId: Int,
    @SerializedName("stockAddress")
    var stockAddress: String,
    @SerializedName("stockQuantity")
    var stockQuantity: Int,
    @SerializedName("minSupplyQuantity")
    var minSupplyQuantity: Int,
)
