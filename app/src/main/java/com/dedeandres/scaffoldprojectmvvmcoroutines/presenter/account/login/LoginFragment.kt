package com.dedeandres.scaffoldprojectmvvmcoroutines.presenter.account.login

import android.widget.Toast
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.dedeandres.scaffoldproject.common.EventObserver
import com.dedeandres.scaffoldprojectmvvmcoroutines.R
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.ProgressDialogUtil
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.Resource
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.ResourceState
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.base.BaseViewModelFragment
import com.dedeandres.scaffoldprojectmvvmcoroutines.databinding.FragmentLoginBinding
import com.dedeandres.scaffoldprojectmvvmcoroutines.presenter.account.login.entity.LoginResponseResult
import com.dedeandres.scaffoldprojectmvvmcoroutines.presenter.dashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.lang.ref.WeakReference

@AndroidEntryPoint
class LoginFragment : BaseViewModelFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by hiltNavGraphViewModels(R.id.login_navigation)

    override fun getViewBinding(): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()

        binding.btnLogin.setOnClickListener {
            viewModel.login(binding.etUsername.text.toString(), binding.etPassword.text.toString())
        }
    }

    override fun initLiveDataObservers() {
        super.initLiveDataObservers()
        viewModel.fetchLoginLiveData.observe(viewLifecycleOwner, EventObserver(::handleLogin))
        viewModel.saveSessionLiveData.observe(
            viewLifecycleOwner,
            EventObserver(::handleSaveSession)
        )
    }

    private fun handleLogin(resource: Resource<LoginResponseResult>) {
        when (resource.state) {
            ResourceState.LOADING -> {
                Timber.d("handleLogin Loading")
                ProgressDialogUtil.showProgressDialog(WeakReference(requireContext()))
            }
            ResourceState.SUCCESS -> {
                Timber.d("handleLogin : ${resource.data}")
                resource.data?.token?.let {
                    viewModel.saveSession(resource.data.token)
                }
            }
            ResourceState.ERROR -> {
                ProgressDialogUtil.hideProgressDialog()
                Timber.d("handleLogin error: ${resource.exception}")
                Toast.makeText(requireContext(), "Wrong Password", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun handleSaveSession(resource: Resource<Unit>) {
        when (resource.state) {
            ResourceState.SUCCESS -> {
                ProgressDialogUtil.hideProgressDialog()
                DashboardActivity.startFromLoginActivity(requireContext())
            }
            ResourceState.ERROR -> {
                ProgressDialogUtil.hideProgressDialog()
            }
        }
    }

}