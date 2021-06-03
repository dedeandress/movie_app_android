package com.dedeandres.scaffoldprojectmvvmcoroutines.presenter.account.landing

import androidx.navigation.fragment.findNavController
import com.dedeandres.scaffoldprojectmvvmcoroutines.R
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.base.BaseFragment
import com.dedeandres.scaffoldprojectmvvmcoroutines.databinding.FragmentLandingBinding

class LandingFragment : BaseFragment<FragmentLandingBinding>() {

    override fun initViews() {
        super.initViews()

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_landingFragment_to_loginFragment)
        }
    }

    override fun getViewBinding(): FragmentLandingBinding {
        return FragmentLandingBinding.inflate(layoutInflater)
    }

}