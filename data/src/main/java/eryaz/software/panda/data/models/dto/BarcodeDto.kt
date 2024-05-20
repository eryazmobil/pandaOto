package eryaz.software.panda.data.models.dto

data class BarcodeDto(
    val id: Int,
    val product: ProductDto,
    val code: String,
    val quantity: Int
)
