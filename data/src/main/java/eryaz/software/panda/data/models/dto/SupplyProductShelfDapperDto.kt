package eryaz.software.panda.data.models.dto

data class SupplyProductShelfDapperDto(
    val productId: Int,
    val productCode: String,
    val productDef: String,
    val gatherAddressId: Int,
    val gatherAddress: String,
    val gatherQuantity: Int,
    val stockAddressId: Int,
    val stockAddress: String,
    val stockQuantity: Int,
    val minSupplyQuantity: String,
    var isChecked :Boolean = false
)
