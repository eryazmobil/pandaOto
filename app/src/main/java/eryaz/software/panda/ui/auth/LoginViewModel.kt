package eryaz.software.panda.ui.auth

import eryaz.software.panda.R
import eryaz.software.panda.data.api.utils.onError
import eryaz.software.panda.data.api.utils.onSuccess
import eryaz.software.panda.data.enums.UiState
import eryaz.software.panda.data.models.dto.ErrorDialogDto
import eryaz.software.panda.data.models.remote.request.LoginRequest
import eryaz.software.panda.data.persistence.SessionManager
import eryaz.software.panda.data.persistence.TemporaryCashManager
import eryaz.software.panda.data.repositories.AuthRepo
import eryaz.software.panda.data.repositories.UserRepo
import eryaz.software.panda.ui.base.BaseViewModel
import eryaz.software.panda.util.CombinedStateFlow
import eryaz.software.panda.util.extensions.isValidPassword
import eryaz.software.panda.util.extensions.isValidUserId
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow

class LoginViewModel(
    private val authRepo: AuthRepo,
    private val userRepo: UserRepo
) : BaseViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    private val _navigateToMain = MutableSharedFlow<Boolean>()
    val navigateToMain = _navigateToMain.asSharedFlow()

    val loginEnable = CombinedStateFlow(email, password) {
        email.value.isValidUserId() && password.value.isValidPassword()
    }

    init {
        _uiState.value = UiState.EMPTY
    }

    fun login() = executeInBackground(_uiState, hasNextRequest = true) {
        val request = LoginRequest(
            email = email.value,
            password = password.value
        )

        authRepo.login(request).onSuccess {
            SessionManager.token = it.accessToken

            fetchWorkActionTypeList()
        }.onError { message, _ ->
            showError(
                ErrorDialogDto(
                    titleRes = R.string.error,
                    message = message
                )
            )
        }
    }

    private fun fetchWorkActionTypeList() = executeInBackground(_uiState) {
        userRepo.fetchWorkActionTypeList().onSuccess {
            TemporaryCashManager.getInstance().workActionTypeList = it
            _navigateToMain.emit(true)
        }
    }
}



