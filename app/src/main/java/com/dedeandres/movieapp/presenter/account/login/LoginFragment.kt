package com.dedeandres.movieapp.presenter.account.login

import android.widget.Toast
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.dedeandres.scaffoldproject.common.EventObserver
import com.dedeandres.movieapp.R
import com.dedeandres.movieapp.common.ProgressDialogUtil
import com.dedeandres.movieapp.common.Resource
import com.dedeandres.movieapp.common.ResourceState
import com.dedeandres.movieapp.common.base.BaseViewModelFragment
import com.dedeandres.movieapp.databinding.FragmentLoginBinding
import com.dedeandres.movieapp.presenter.account.login.entity.LoginResponseResult
import com.dedeandres.movieapp.presenter.dashboard.DashboardActivity
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