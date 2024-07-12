package eryaz.software.panda.ui.dashboard.recording.addPackage

import androidx.lifecycle.viewModelScope
import eryaz.software.panda.R
import eryaz.software.panda.data.api.utils.onError
import eryaz.software.panda.data.api.utils.onSuccess
import eryaz.software.panda.data.models.dto.ErrorDialogDto
import eryaz.software.panda.data.models.dto.ProductDto
import eryaz.software.panda.data.persistence.SessionManager
import eryaz.software.panda.data.repositories.BarcodeRepo
import eryaz.software.panda.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddPackageVM(private val barcodeRepo: BarcodeRepo) : BaseViewModel() {
    private var productID: Int = 0

    val searchProductCode = MutableStateFlow("")
    val enteredQuantity = MutableStateFlow("")

    private val _productDetail = MutableStateFlow<ProductDto?>(null)
    val productDetail = _productDetail.asStateFlow()

    private val _showProductDetail = MutableStateFlow(false)
    val showProductDetail = _showProductDetail.asStateFlow()

    fun getProductByCode() = executeInBackground(
        showProgressDialog = true, showErrorDialog = false
    ) {
        barcodeRepo.getProductByCode(
            SessionManager.companyId,
            searchProductCode.value.trim(),
        ).onSuccess {
            productID = it.id
            _productDetail.emit(it)
            _showProductDetail.emit(true)
        }.onError { _, _ ->
            showError(
                ErrorDialogDto(
                    titleRes = R.string.error,
                    messageRes = R.string.msg_no_product
                )
            )
        }
    }

    private fun isValidFields(): Boolean {
        when {
            productID == 0 -> {
                showError(
                    ErrorDialogDto(
                        titleRes = R.string.error, messageRes = R.string.product_code_empty
                    )
                )
                return false
            }

            enteredQuantity.value.toInt() == 0 -> {
                showError(
                    ErrorDialogDto(
                        titleRes = R.string.error, messageRes = R.string.enter_valid_qty
                    )
                )
                return false
            }

            else -> return true
        }
    }
    fun setEnteredProduct(dto: ProductDto) {
        productID = dto.id
        viewModelScope.launch {
            _productDetail.emit(dto)
            _showProductDetail.emit(true)
        }
    }


}