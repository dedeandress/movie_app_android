package com.dedeandres.scaffoldprojectmvvmcoroutines.presenter.account.splashscreen

import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dedeandres.scaffoldprojectmvvmcoroutines.R
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.base.BaseViewModelFragment
import com.dedeandres.scaffoldprojectmvvmcoroutines.databinding.FragmentSplashBinding
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.entity.SessionState
import com.dedeandres.scaffoldprojectmvvmcoroutines.presenter.dashboard.DashboardActivity
import timber.log.Timber

class SplashFragment : BaseViewModelFragment<FragmentSplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel by hiltNavGraphViewModels(R.id.login_navigation)

    override fun initLiveDataObservers() {
        super.initLiveDataObservers()

        viewModel.sessionStateLiveData.observe(viewLifecycleOwner, {
            when (it) {
                SessionState.LOGGED_IN -> {
                    Timber.d("navigateTo Dashboard")

                    DashboardActivity.startFromLoginActivity(requireContext())
                }

                else -> {
                    //navigate into landing page
                    Timber.d("navigateTo Landing")
                    this.findNavController()
                        .navigate(R.id.action_splashFragment_to_landingFragment)
                }
            }
        })

    }


    override fun getViewBinding(): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(layoutInflater)
    }

}