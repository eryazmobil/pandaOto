package eryaz.software.panda.data.mappers

import eryaz.software.panda.data.models.dto.ProductDto
import eryaz.software.panda.data.models.dto.ProductShelfDto
import eryaz.software.panda.data.models.dto.ProductShelfQuantityDto
import eryaz.software.panda.data.models.dto.ProductShelfSupplyDto
import eryaz.software.panda.data.models.dto.ProductStorageQuantityDto
import eryaz.software.panda.data.models.dto.ShippingTypeDto
import eryaz.software.panda.data.models.dto.SupplyProductShelfDapperDto
import eryaz.software.panda.data.models.remote.response.ProductResponse
import eryaz.software.panda.data.models.remote.response.ProductShelfQuantityResponse
import eryaz.software.panda.data.models.remote.response.ProductShelfResponse
import eryaz.software.panda.data.models.remote.response.ProductShelfSupplyResponse
import eryaz.software.panda.data.models.remote.response.ProductStorageQuantityResponse
import eryaz.software.panda.data.models.remote.response.ShippingTypeResponse
import eryaz.software.panda.data.models.remote.response.SupplyProductShelfDapperResponse

fun ProductResponse.toDto() = ProductDto(
    company = company?.toDto(),
    code = code,
    definition = definition.orEmpty(),
    definition2 = definition2.orEmpty(),
    manufacturer = manufacturer.orEmpty(),
    manufacturerCode = manufacturerCode.orEmpty(),
    unit = unit.orEmpty(),
    hasSerial = hasSerial,
    id = id
)

fun ShippingTypeResponse.toDto() = ShippingTypeDto(
    code = code.orEmpty(),
    definition = definition.orEmpty(),
    id = id,
    shortCode = shortCode.orEmpty()
)

fun ProductShelfResponse.toDto() = ProductShelfDto(
    id = id,
    product = product.toDto(),
    shelf = shelf.toDto(),
    safetyPercent = safetyPercent,
    maxQuantity = maxQuantity,
    quantity = quantity
)

fun ProductShelfSupplyResponse.toDto() = ProductShelfSupplyDto(
    id = id,
    product = product?.toDto(),
    shelf = shelf?.toDto(),
    quantityIn = quantityIn.toString(),
    quantityOut = quantityOut.toString(),
    quantity = quantity.toString(),
    type = type
)

fun ProductStorageQuantityResponse.toDto() = ProductStorageQuantityDto(
    id = id,
    product = product.toDto(),
    warehouse = warehouse.toDto(),
    storage = storage.toDto(),
    quantity = quantity.toString(),
    company = company.toDto(),
    storageText = "${storage.code} - ${storage.definition}"
)

fun ProductShelfQuantityResponse.toDto() = ProductShelfQuantityDto(
    id = id,
    product = product.toDto(),
    warehouse = warehouse.toDto(),
    storage = storage.toDto(),
    quantity = quantity.toInt().toString(),
    company = company?.toDto(),
    storageText = "${storage.code} - ${storage.definition}",
    shelf = shelf?.toDto()
)

fun SupplyProductShelfDapperResponse.toDto() = SupplyProductShelfDapperDto(
    productId = productId,
    productCode = productCode,
    productDef = productDef,
    gatherAddressId = gatherAddressId,
    gatherAddress = gatherAddress,
    gatherQuantity = gatherQuantity,
    stockAddressId = stockAddressId,
    stockAddress = stockAddress,
    stockQuantity=stockQuantity,
    minSupplyQuantity = minSupplyQuantity.toString()
)