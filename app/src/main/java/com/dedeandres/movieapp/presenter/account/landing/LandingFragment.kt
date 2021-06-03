package com.dedeandres.movieapp.presenter.account.landing

import androidx.navigation.fragment.findNavController
import com.dedeandres.movieapp.R
import com.dedeandres.movieapp.common.base.BaseFragment
import com.dedeandres.movieapp.databinding.FragmentLandingBinding

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