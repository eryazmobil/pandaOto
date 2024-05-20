package eryaz.software.panda.data.mappers

import eryaz.software.panda.data.models.dto.ClientDto
import eryaz.software.panda.data.models.remote.response.ClientSmallResponse

fun ClientSmallResponse.toDto() = ClientDto(
    id = id,
    code = code,
    name = name ?: "Hatalı işlem"
)