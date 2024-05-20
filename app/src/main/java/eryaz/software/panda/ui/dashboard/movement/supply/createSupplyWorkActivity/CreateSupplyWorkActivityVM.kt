package eryaz.software.panda.ui.dashboard.movement.supply.createSupplyWorkActivity

import androidx.lifecycle.MutableLiveData
import eryaz.software.panda.data.api.utils.onSuccess
import eryaz.software.panda.data.models.dto.ProductShelfWorkActivityDto
import eryaz.software.panda.data.models.dto.SupplyProductShelfDapperDto
import eryaz.software.panda.data.persistence.SessionManager
import eryaz.software.panda.data.persistence.TemporaryCashManager
import eryaz.software.panda.data.repositories.WorkActivityRepo
import eryaz.software.panda.ui.base.BaseViewModel
import eryaz.software.panda.util.extensions.orZero
import kotlinx.coroutines.flow.MutableStateFlow

class CreateSupplyWorkActivityVM(
    private val workActivityRepo: WorkActivityRepo
) : BaseViewModel() {

    val supplyProductShelfDapperDtoList =
        MutableLiveData<List<SupplyProductShelfDapperDto>>(emptyList())
    val finishThePage = MutableStateFlow(false)

    fun getReportProductShelfListForWorkActivityForPda(typeId: Int) {
        executeInBackground(_uiState) {
            workActivityRepo.getReportProductShelfListForWorkActivityForPda(
                typeId,
                SessionManager.companyId,
                SessionManager.warehouseId
            ).onSuccess {
                supplyProductShelfDapperDtoList.value = it
            }
        }
    }

    fun createProductShelfWorkActivityForPda(
        typeId: Int,
        productShelfIdList: List<Int?>?
    ) {
        executeInBackground(
            showProgressDialog = true,
            showErrorDialog = true
        ) {
            val productShelfWorkActivityDto = ProductShelfWorkActivityDto(
                productShelfIdList = productShelfIdList
            )
            workActivityRepo.createProductShelfWorkActivityForPda(
                typeId,
                productShelfWorkActivityDto
            ).onSuccess {
                finishWorkAction()
            }
        }
    }

    private fun finishWorkAction() {
        executeInBackground(showProgressDialog = true) {
            workActivityRepo.finishWorkAction(actionId = TemporaryCashManager.getInstance().workAction?.workActionId.orZero())
                .onSuccess {
                    finishThePage.emit(true)
                }
        }
    }
}