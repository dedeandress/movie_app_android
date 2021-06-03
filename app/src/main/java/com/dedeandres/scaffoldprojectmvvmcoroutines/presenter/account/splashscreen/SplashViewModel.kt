package com.dedeandres.scaffoldprojectmvvmcoroutines.presenter.account.splashscreen

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.entity.SessionState
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.usecase.FetchSessionStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val fetchSessionStateUseCase: FetchSessionStateUseCase
) : ViewModel() {

    internal val sessionStateLiveData = MutableLiveData<SessionState>()

    init {
        navigateNextScreen()
    }

    private fun navigateNextScreen() {
        viewModelScope.launch {
            fetchSessionStateUseCase {
                it.fold(
                    {

                    },
                    { result ->
                        sessionStateLiveData.postValue(result)
                    }
                )
            }
        }
    }
}