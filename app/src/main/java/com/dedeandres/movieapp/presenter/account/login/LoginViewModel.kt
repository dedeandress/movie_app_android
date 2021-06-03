package com.dedeandres.movieapp.presenter.account.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dedeandres.scaffoldproject.common.Event
import com.dedeandres.scaffoldproject.common.KeyUtils
import com.dedeandres.movieapp.common.Resource
import com.dedeandres.movieapp.common.setErrorEvent
import com.dedeandres.movieapp.common.setLoadingEvent
import com.dedeandres.movieapp.common.setSuccessEvent
import com.dedeandres.movieapp.domain.account.usecase.FetchLoginUseCase
import com.dedeandres.movieapp.domain.account.usecase.SaveSessionUseCase
import com.dedeandres.movieapp.presenter.account.login.entity.LoginResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val fetchLoginUseCase: FetchLoginUseCase,
    private val saveSessionUseCase: SaveSessionUseCase
) : ViewModel() {

    val saveSessionLiveData = MutableLiveData<Event<Resource<Unit>>>()
    val fetchLoginLiveData = MutableLiveData<Event<Resource<LoginResponseResult>>>()

    fun login(username: String, password: String) {
        fetchLoginLiveData.setLoadingEvent()
        fetchLoginUseCase(
            mapOf(
                KeyUtils.USERNAME to username,
                KeyUtils.PASSWORD to password
            )
        ) {
            it.fold(
                { failure ->
                    Timber.e("fetchLoginLiveData")
                    fetchLoginLiveData.setErrorEvent(failure)
                },
                { result ->
                    Timber.d("fetchLoginLiveData")
                    fetchLoginLiveData.setSuccessEvent(result)
                }
            )
        }
    }

    fun saveSession(token: String) {
        saveSessionLiveData.setLoadingEvent()
        saveSessionUseCase(
            mapOf(
                KeyUtils.TOKEN to token
            )
        ) {
            it.fold(
                { failure ->
                    saveSessionLiveData.setErrorEvent(failure)
                },
                {
                    saveSessionLiveData.setSuccessEvent()
                }
            )
        }
    }

}