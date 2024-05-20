package eryaz.software.panda.data.models.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductShelfSupplyDto(
    val id: Int?,
    val type: Int?,
    val product: ProductDto?,
    val shelf: ShelfDto?,
    val quantity: String?,
    val quantityIn: String?,
    val quantityOut: String?
) : Parcelable
