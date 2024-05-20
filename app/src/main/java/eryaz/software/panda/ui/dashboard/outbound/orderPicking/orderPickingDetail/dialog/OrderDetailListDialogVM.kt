package eryaz.software.panda.ui.dashboard.outbound.orderPicking.orderPickingDetail.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import eryaz.software.panda.R
import eryaz.software.panda.data.api.utils.onError
import eryaz.software.panda.data.api.utils.onSuccess
import eryaz.software.panda.data.models.dto.ErrorDialogDto
import eryaz.software.panda.data.models.dto.OrderDetailDto
import eryaz.software.panda.data.persistence.TemporaryCashManager
import eryaz.software.panda.data.repositories.OrderRepo
import eryaz.software.panda.ui.base.BaseViewModel
import eryaz.software.panda.util.extensions.orZero

class OrderDetailListDialogVM(
    private val repo: OrderRepo
) : BaseViewModel() {

    private val _orderDetailList = MutableLiveData<List<OrderDetailDto>>(emptyList())
    val orderDetailList: LiveData<List<OrderDetailDto>> = _orderDetailList

    val search = MutableLiveData("")

    init {
        getOrderListDetail()
    }

    fun searchList() = search.switchMap { query ->
        MutableLiveData<List<OrderDetailDto>>().apply {
            value = _orderDetailList.value.orEmpty().filter { data ->
                data.product.code.contains(query, ignoreCase = true)
            }
        }
    }

    fun getOrderListDetail() {
        executeInBackground(showProgressDialog = true) {
            val workActivityId =
                TemporaryCashManager.getInstance().workActivity?.workActivityId.orZero()
            repo.getOrderDetailListForPda(workActivityId = workActivityId)
                .onSuccess {
                    _orderDetailList.value = it
                }.onError { message, _ ->
                    showError(
                        ErrorDialogDto(
                            title = stringProvider.invoke(R.string.error),
                            message = message
                        )
                    )
                }
        }
    }

}