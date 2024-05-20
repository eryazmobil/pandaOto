package eryaz.software.panda.data.models.remote.response

import androidx.databinding.ObservableField
import eryaz.software.panda.data.enums.LanguageType

data class LanguageModel(
    val lang: LanguageType,
    val isSelected: ObservableField<Boolean>
)