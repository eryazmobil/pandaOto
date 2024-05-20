package eryaz.software.panda.data.mappers

import eryaz.software.panda.data.models.dto.WaybillListDetailDto
import eryaz.software.panda.data.models.remote.response.WaybillListDetailResponse

fun WaybillListDetailResponse.toDto() = WaybillListDetailDto(
    product = product.toDto(),
    quantity = quantity,
    quantityOrder = quantityOrder,
    quantityPlaced = quantityPlaced,
    quantityControlled = quantityControlled.toString(),
    id = id
)