package eryaz.software.panda.ui.dashboard.movement.transferStockCorrection.storageList

import eryaz.software.panda.data.api.utils.onSuccess
import eryaz.software.panda.data.models.dto.StorageDto
import eryaz.software.panda.data.persistence.SessionManager
import eryaz.software.panda.data.repositories.UserRepo
import eryaz.software.panda.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StorageListDialogVM(
    private val userRepo: UserRepo,
) : BaseViewModel() {

    private val _storageList = MutableStateFlow(listOf<StorageDto>())
    val storageList = _storageList.asStateFlow()

    init {
        getStorageListByWarehouse()
    }

    fun getStorageListByWarehouse() = executeInBackground(_uiState) {
        userRepo.getStorageListByWarehouse(
            warehouseId = SessionManager.warehouseId
        ).onSuccess {
            _storageList.emit(it)
        }
    }
}