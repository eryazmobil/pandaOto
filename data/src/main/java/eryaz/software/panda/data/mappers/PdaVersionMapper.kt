package eryaz.software.panda.data.mappers

import eryaz.software.panda.data.models.remote.models.PdaVersionModel
import eryaz.software.panda.data.models.dto.PdaVersionDto

fun PdaVersionModel.toDto() = PdaVersionDto(
    version = version,
    downloadLink = downloadLink,
    id = id,
    apkZipName = downloadLink?.substringAfterLast("/"),
    apkFileName = downloadLink?.substringAfterLast("/")?.substringBeforeLast(".")
)
