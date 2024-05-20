package eryaz.software.panda.data.models.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkActivityDetailDto(
    val workActivity: WorkActivityDto,
    val sourceId: Int,
    val product: ProductDto,
    val quantity: Double,
    val placedCollectQuantity: Double,
    val oldShelf: ShelfDto,
    val newShelf: ShelfDto,
    val placedReplenishmentQty: Int,
    val id: Int
) : Parcelable
