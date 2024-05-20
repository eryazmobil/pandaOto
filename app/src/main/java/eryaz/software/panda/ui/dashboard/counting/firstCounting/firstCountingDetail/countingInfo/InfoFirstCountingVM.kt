package eryaz.software.panda.ui.dashboard.counting.firstCounting.firstCountingDetail.countingInfo

import eryaz.software.panda.data.api.utils.onSuccess
import eryaz.software.panda.data.models.dto.StockTackingProcessDto
import eryaz.software.panda.data.repositories.CountingRepo
import eryaz.software.panda.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InfoFirstCountingVM(
    val repo: CountingRepo,
    val stHeaderId: Int,
    val selectedShelfId: Int
) : BaseViewModel() {

    private val _stockTakingProcessList = MutableStateFlow(listOf<StockTackingProcessDto>())
    val stProcessList = _stockTakingProcessList.asStateFlow()

    val shelfAddress = MutableStateFlow("")

    init {
        getSTActionProcessListForShelf()
    }

    private fun getSTActionProcessListForShelf() {
        executeInBackground(
            showProgressDialog = true
        ) {
            repo.getSTActionProcessListForShelf(
                stHeaderId = stHeaderId,
                assignedShelfId = selectedShelfId
            ).onSuccess {
                _stockTakingProcessList.emit(it)
                shelfAddress.emit(it[0].shelf!!.shelfAddress)
            }
        }
    }

    fun updateQuantitySTActionProcess(
        selectedShelfId: Int,
        stActionProcessId: Int,
        productId: Int,
        quantity: Int
    ) {
        executeInBackground(showProgressDialog = true) {

            repo.updateQuantitySTActionProcess(
                stActionProcessId = stActionProcessId,
                assignedShelfId = selectedShelfId,
                productId = productId,
                countedQuantity = quantity
            ).onSuccess {
                getSTActionProcessListForShelf()
            }
        }
    }

    fun deleteSTActionProcess(stActionProcessId: Int) {
        executeInBackground(showProgressDialog = true) {
            repo.deleteSTActionProcess(stActionProcessId = stActionProcessId)
                .onSuccess {
                    getSTActionProcessListForShelf()
                }
        }
    }
}