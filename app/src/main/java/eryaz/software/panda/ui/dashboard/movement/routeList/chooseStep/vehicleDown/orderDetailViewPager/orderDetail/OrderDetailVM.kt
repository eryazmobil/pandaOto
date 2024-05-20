package eryaz.software.panda.ui.dashboard.movement.routeList.chooseStep.vehicleDown.orderDetailViewPager.orderDetail

import eryaz.software.panda.R
import eryaz.software.panda.data.api.utils.onError
import eryaz.software.panda.data.api.utils.onSuccess
import eryaz.software.panda.data.models.dto.PackageOrderDetailDto
import eryaz.software.panda.data.models.dto.WarningDialogDto
import eryaz.software.panda.data.repositories.OrderRepo
import eryaz.software.panda.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OrderDetailVM(
    private val orderHeaderId: Int,
    private val repo: OrderRepo
) : BaseViewModel() {

    private val _orderList = MutableStateFlow(listOf<PackageOrderDetailDto>())
    val orderList = _orderList.asStateFlow()

    fun fetchOrderHeaderRouteDetailList() {
        executeInBackground(showProgressDialog = true) {
            repo.getOrderDetailForRoute(
                orderHeaderId = orderHeaderId
            ).onSuccess {
                if (it.isEmpty()) {
                    _orderList.value = emptyList()
                    showWarning(
                        WarningDialogDto(
                            title = stringProvider.invoke(R.string.warning),
                            message = stringProvider.invoke(R.string.list_is_empty)
                        )
                    )
                }
                _orderList.value = it
            }.onError { _, _ ->
                _orderList.value = emptyList()
            }
        }
    }

}