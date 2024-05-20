package eryaz.software.panda.ui.dashboard.counting.fastCounting.fastCountingDetail.willCounted

import eryaz.software.panda.data.models.dto.CountingComparisonDto
import eryaz.software.panda.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FastWillCountedListVM(productList: List<CountingComparisonDto>) : BaseViewModel() {
    private val _list = MutableStateFlow(productList)
    val list = _list.asStateFlow()

    fun updateProductQuantity(productId: Int, quantity: Int) {
        _list.value.find {
            it.productDto.id == productId
        }?.newQuantity?.set(quantity.toString())
    }
}
