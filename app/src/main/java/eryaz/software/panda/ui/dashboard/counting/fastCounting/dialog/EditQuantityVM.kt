package eryaz.software.panda.ui.dashboard.counting.fastCounting.dialog

import eryaz.software.panda.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class EditQuantityVM(
    val productCode: String,
    val productQuantity: Int,
    val productId: Int
) : BaseViewModel() {
    val quantity = MutableStateFlow(productQuantity.toString())

    val productCodeTxt = MutableStateFlow(productCode)
}