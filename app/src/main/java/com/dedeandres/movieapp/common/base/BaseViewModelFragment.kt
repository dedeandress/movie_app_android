package com.dedeandres.movieapp.common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseViewModelFragment<VB : ViewBinding, VM : ViewModel> : BaseFragment<VB>() {

    protected abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveDataObservers()
    }

    @CallSuper
    protected open fun initLiveDataObservers() = Unit


}