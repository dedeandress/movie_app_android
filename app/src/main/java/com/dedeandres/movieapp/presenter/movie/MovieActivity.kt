package com.dedeandres.movieapp.presenter.movie

import com.dedeandres.movieapp.common.base.BaseActivity
import com.dedeandres.movieapp.databinding.ActivityMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : BaseActivity<ActivityMovieBinding>() {

    override fun getViewBinding(): ActivityMovieBinding {
        return ActivityMovieBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()

        supportActionBar?.hide()
    }

}