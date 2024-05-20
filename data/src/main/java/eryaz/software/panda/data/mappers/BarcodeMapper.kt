package eryaz.software.panda.data.mappers

import eryaz.software.panda.data.models.dto.BarcodeDto
import eryaz.software.panda.data.models.remote.response.BarcodeResponse

fun BarcodeResponse.toDto() = BarcodeDto(
    id = id,
    product = product.toDto(),
    code = code,
    quantity = quantity
)