package eryaz.software.panda.ui.dashboard.movement.routeList.chooseStep.vehicleDown.orderDetailViewPager.packageDetail

import eryaz.software.panda.R
import eryaz.software.panda.data.api.utils.onError
import eryaz.software.panda.data.api.utils.onSuccess
import eryaz.software.panda.data.models.dto.RouteDto
import eryaz.software.panda.data.models.dto.WarningDialogDto
import eryaz.software.panda.data.repositories.OrderRepo
import eryaz.software.panda.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PackageDetailVM(
    private val shippingRouteId: Int,
    private val orderHeaderId: Int,
    private val repo: OrderRepo
) : BaseViewModel() {

    private val _packageList = MutableStateFlow(listOf<RouteDto>())
    val packageList = _packageList.asStateFlow()

    fun getOrderHeaderRouteDetailList() {
        executeInBackground(showProgressDialog = true) {
            repo.getOrderHeaderRouteDetailList(
               shippingRouteId = shippingRouteId,
                orderHeaderId = orderHeaderId
            ).onSuccess {
                if (it.isEmpty()) {
                    _packageList.value = emptyList()
                    showWarning(
                        WarningDialogDto(
                            title = stringProvider.invoke(R.string.warning),
                            message = stringProvider.invoke(R.string.list_is_empty)
                        )
                    )
                }
                _packageList.value = it
            }.onError { _, _ ->
                _packageList.value = emptyList()
            }
        }
    }

}