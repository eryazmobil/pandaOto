package eryaz.software.panda.ui.dashboard.settings.companies

import eryaz.software.panda.data.models.dto.CompanyDto
import eryaz.software.panda.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CompanyListVM(
     val companyListDto :List<CompanyDto>
) : BaseViewModel() {

    private val _companyList = MutableStateFlow(companyListDto)
    val companyList = _companyList.asStateFlow()
}