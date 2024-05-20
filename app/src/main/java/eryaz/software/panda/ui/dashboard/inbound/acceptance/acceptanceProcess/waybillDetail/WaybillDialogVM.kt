package eryaz.software.panda.ui.dashboard.inbound.acceptance.acceptanceProcess.waybillDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import eryaz.software.panda.R
import eryaz.software.panda.data.api.utils.onError
import eryaz.software.panda.data.api.utils.onSuccess
import eryaz.software.panda.data.models.dto.ErrorDialogDto
import eryaz.software.panda.data.models.dto.WaybillListDetailDto
import eryaz.software.panda.data.persistence.TemporaryCashManager
import eryaz.software.panda.data.repositories.WorkActivityRepo
import eryaz.software.panda.ui.base.BaseViewModel

class WaybillDialogVM(
    private val repo: WorkActivityRepo
) : BaseViewModel() {

    private val _waybillDetailList = MutableLiveData<List<WaybillListDetailDto>>(emptyList())
    val waybillDetailList: LiveData<List<WaybillListDetailDto>> = _waybillDetailList

    val search = MutableLiveData("")

    init {
        getWaybillListDetail()
    }

    fun searchList() = search.switchMap { query ->
        MutableLiveData<List<WaybillListDetailDto>>().apply {
            value = _waybillDetailList.value.orEmpty().filter { data ->
                data.product.code.contains(query, ignoreCase = true)
            }
        }
    }

     fun getWaybillListDetail() {
        executeInBackground(_uiState) {
            val workActivityId =
                TemporaryCashManager.getInstance().workActivity?.workActivityId ?: 0
            repo.getWaybillListDetail(
                workActivityId = workActivityId)
                .onSuccess {
                    _waybillDetailList.value = it
                }.onError {message, _ ->
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