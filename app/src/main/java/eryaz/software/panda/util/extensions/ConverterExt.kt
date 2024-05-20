package eryaz.software.panda.util.extensions

import eryaz.software.panda.R
import eryaz.software.panda.data.enums.LanguageType

fun LanguageType.getDrawableRes() = when (this) {
    LanguageType.AZ -> R.drawable.ic_az
    LanguageType.EN -> R.drawable.ic_en
    LanguageType.RU -> R.drawable.ic_ru
    LanguageType.AR -> R.drawable.ic_ar
    LanguageType.TR -> R.drawable.ic_tr
}
