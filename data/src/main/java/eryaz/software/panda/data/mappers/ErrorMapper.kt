package eryaz.software.panda.data.mappers

import eryaz.software.panda.data.models.dto.ErrorDto
import eryaz.software.panda.data.models.remote.response.ErrorModel

fun ErrorModel.toDto() = ErrorDto(
    code = code,
    message = message
)