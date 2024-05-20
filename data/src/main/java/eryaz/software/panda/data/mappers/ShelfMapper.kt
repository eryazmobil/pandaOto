package eryaz.software.panda.data.mappers

import eryaz.software.panda.data.models.dto.ProductSpecialShelfDto
import eryaz.software.panda.data.models.dto.ShelfDto
import eryaz.software.panda.data.models.dto.ShelfTypeForSupplyDto
import eryaz.software.panda.data.models.remote.response.ProductSpecialShelfResponse
import eryaz.software.panda.data.models.remote.response.ShelfResponse
import eryaz.software.panda.data.models.remote.response.ShelfTypeForSupplyResponse

fun ShelfResponse.toDto() = ShelfDto(
    shelfAddress = fullAddress,
    shelfId = shelfId,
    quantity = quantity.toInt().toString()
)

fun ProductSpecialShelfResponse.toDto() = ProductSpecialShelfDto(
    shelfDto = shelf?.toDto(),
    product = product.toDto(),
    quantity = quantity.toString()
)

fun ShelfTypeForSupplyResponse.toDto() = ShelfTypeForSupplyDto(
    code = code,
    definition = definition
)