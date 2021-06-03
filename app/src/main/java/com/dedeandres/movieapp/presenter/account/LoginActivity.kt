package com.dedeandres.movieapp.presenter.account

import android.content.Context
import android.content.Intent
import com.dedeandres.movieapp.common.base.BaseActivity
import com.dedeandres.movieapp.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {


    companion object{
        fun start(context: Context) {
            Intent(context, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(this)
            }
        }
    }

    override fun getViewBinding() = ActivityLoginBinding.inflate(layoutInflater)
}
