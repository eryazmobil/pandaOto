package eryaz.software.panda.data.mappers

import eryaz.software.panda.data.models.dto.OrderPickingDto
import eryaz.software.panda.data.models.dto.PickingSuggestionDto
import eryaz.software.panda.data.models.dto.StockShelfQuantityDto
import eryaz.software.panda.data.models.dto.TransferRequestAllDetailDto
import eryaz.software.panda.data.models.dto.WorkActivityDetailDto
import eryaz.software.panda.data.models.dto.WorkActivityDto
import eryaz.software.panda.data.models.dto.WorkActivityTypeDto
import eryaz.software.panda.data.models.remote.response.OrderPickingResponse
import eryaz.software.panda.data.models.remote.response.PickingSuggestionResponse
import eryaz.software.panda.data.models.remote.response.StockShelfQuantityResponse
import eryaz.software.panda.data.models.remote.response.TransferRequestAllDetailResponse
import eryaz.software.panda.data.models.remote.response.WorkActivityDetailResponse
import eryaz.software.panda.data.models.remote.response.WorkActivityResponse
import eryaz.software.panda.data.models.remote.response.WorkActivityTypeResponse
import eryaz.software.panda.data.utils.getFormattedDate

fun WorkActivityResponse.toDto() = WorkActivityDto(
    workActivityId = id,
    workActivityCode = code,
    workActivityType = workActivityTypeResponse?.toDto(),
    client = client?.toDto(),
    creationTime = creationTime.getFormattedDate("dd.MM.yyyy HH:mm"),
    isLocked = isLocked,
    company = company?.toDto(),
    shippingType = shippingType?.toDto(),
    notes = notes.orEmpty(),
    controlPointDefinition = controlPointDefinition.orEmpty(),
    hasPriority = hasPriority,
    replenishmentShelf = replenishmentShelf?.toDto()
)

fun WorkActivityDetailResponse.toDto() = WorkActivityDetailDto(
    workActivity = workActivity.toDto(),
    sourceId = sourceId,
    product = product.toDto(),
    quantity = quantity,
    placedCollectQuantity = placedCollectQuantity,
    oldShelf = oldShelf.toDto(),
    newShelf = newShelf.toDto(),
    placedReplenishmentQty = placedReplenishmentQty,
    id = id
)

fun WorkActivityTypeResponse.toDto() = WorkActivityTypeDto(
    id = id,
    code = code,
    definition = definition
)

fun StockShelfQuantityResponse.toDto() = StockShelfQuantityDto(
    shelf = shelf.toDto(),
    quantity = quantity
)

fun PickingSuggestionResponse.toDto() = PickingSuggestionDto(
    id = id,
    product = product.toDto(),
    shelfForPicking = shelfForPicking.toDto(),
    quantityWillBePicked = quantityWillBePicked,
    quantityPicked = quantityPicked,
    controlPoint = controlPoint?.toDto(),
    collectPoint = collectPoint.orEmpty(),
    startDate = startDate.getFormattedDate("dd.MM.yyyy HH:mm")
)

fun OrderPickingResponse.toDto() = OrderPickingDto(
    orderDetailList = orderDetailList.map { it.toDto() },
    pickingSuggestionList = pickingSuggestionList.map { it.toDto() }
)

fun TransferRequestAllDetailResponse.toDto() = TransferRequestAllDetailDto(
    transferRequestHeader = transferRequestHeader.toDto(),
    quantityShippedAll = quantityShippedAll,
    quantityPickedAll = quantityPickedAll,
    quantityAll = quantityAll,
    transferRequestDetailPdaDto = transferRequestDetailResponse.map { it.toDto() },
)