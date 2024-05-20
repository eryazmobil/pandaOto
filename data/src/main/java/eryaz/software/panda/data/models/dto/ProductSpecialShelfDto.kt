package eryaz.software.panda.data.models.dto

data class ProductSpecialShelfDto(
    val product: ProductDto,
    val shelfDto: ShelfDto?,
    val quantity: String
)
