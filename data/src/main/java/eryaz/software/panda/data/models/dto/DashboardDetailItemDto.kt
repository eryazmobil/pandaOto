package eryaz.software.panda.data.models.dto

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.databinding.ObservableField
import eryaz.software.panda.data.enums.DashboardDetailPermissionType

data class DashboardDetailItemDto(
    @DrawableRes
    val iconRes:Int,
    @StringRes
    val titleRes: Int,
    var type: DashboardDetailPermissionType,
    var hasPermission: ObservableField<Boolean>
)