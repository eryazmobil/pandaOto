package eryaz.software.panda.ui.dashboard.movement.transferStockCorrection.stockType

import eryaz.software.panda.R
import eryaz.software.panda.data.models.dto.StockTypeDto
import eryaz.software.panda.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StockTyeDialogVM : BaseViewModel() {

    private val _stockTypeList = MutableStateFlow(getItemList())
    val stockTypeList = _stockTypeList.asStateFlow()

    private fun getItemList() =
            listOf(
                StockTypeDto(
                    type = 1,
                    titleRes = R.string.add_product_
                ),
                StockTypeDto(
                    type = 2,
                    titleRes = R.string.remove_product
                )
            )
}