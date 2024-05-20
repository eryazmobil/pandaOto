package eryaz.software.panda.ui.dashboard.inbound.dat.placement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import eryaz.software.panda.R
import eryaz.software.panda.data.api.utils.onError
import eryaz.software.panda.data.api.utils.onSuccess
import eryaz.software.panda.data.enums.ActionType
import eryaz.software.panda.data.models.dto.WarningDialogDto
import eryaz.software.panda.data.models.dto.WorkActionDto
import eryaz.software.panda.data.models.dto.WorkActivityDto
import eryaz.software.panda.data.persistence.SessionManager
import eryaz.software.panda.data.persistence.TemporaryCashManager
import eryaz.software.panda.data.repositories.PlacementRepo
import eryaz.software.panda.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class DatPlacementListVM(private val repo: PlacementRepo) : BaseViewModel() {

    private val _placementList = MutableLiveData<List<WorkActivityDto?>>(emptyList())
    val placementWorkActivityList: LiveData<List<WorkActivityDto?>> = _placementList

    val search = MutableLiveData("")

    private val _workActionDto = MutableSharedFlow<WorkActionDto>()
    val workActionDto = _workActionDto.asSharedFlow()

    fun searchList() = search.switchMap { query ->
        MutableLiveData<List<WorkActivityDto?>>().apply {
            value = filterData(query)
        }
    }

    private fun filterData(query: String): List<WorkActivityDto?> {
        val dataList = _placementList.value.orEmpty()

        val filteredList = dataList.filter { data ->
            data?.client?.name?.contains(query, ignoreCase = true) ?: true
        }
        return filteredList
    }

    fun getPlacementList() {
        executeInBackground(_uiState) {
            repo.getTransferRequestWorkActivityList(
                companyId = SessionManager.companyId,
                warehouseId = SessionManager.warehouseId
            ).onSuccess {
                if (it.isEmpty()) {
                    _placementList.value = emptyList()
                    showWarning(
                        WarningDialogDto(
                            title = stringProvider.invoke(R.string.not_found_work_activity),
                            message = stringProvider.invoke(R.string.placement_is_empty)
                        )
                    )
                } else {
                    _placementList.value = it
                }
            }.onError { _, _ ->
                _placementList.value = emptyList()

            }
        }
    }

    fun getWorkAction() {
        executeInBackground(
            _uiState,
            showErrorDialog = false,
            checkErrorState = false
        ) {
            repo.getWorkAction(
                companyId = SessionManager.companyId,
                warehouseId = SessionManager.warehouseId
            ).onSuccess {
                _workActionDto.emit(it)
                TemporaryCashManager.getInstance().workAction = it
            }.onError { _, _ ->
                createWorkAction()
            }
        }
    }

    private fun createWorkAction() {
        executeInBackground {
            val activityID = TemporaryCashManager.getInstance().workActivity?.workActivityId ?: 0
            repo.createWorkAction(
                activityId = activityID,
                actionTypeCode = ActionType.PLACEMENT.type
            ).onSuccess {
                TemporaryCashManager.getInstance().workAction = it
                _workActionDto.emit(it)
            }
        }
    }
}