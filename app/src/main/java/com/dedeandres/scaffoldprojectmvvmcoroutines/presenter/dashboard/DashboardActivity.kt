package com.dedeandres.scaffoldprojectmvvmcoroutines.presenter.dashboard

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.dedeandres.scaffoldproject.common.KeyUtils
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.base.BaseActivity
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.sharedpref.SharedPrefs
import com.dedeandres.scaffoldprojectmvvmcoroutines.databinding.ActivityDashboardBinding
import com.dedeandres.scaffoldprojectmvvmcoroutines.domain.account.repository.AccountRepository
import com.dedeandres.scaffoldprojectmvvmcoroutines.presenter.account.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    @Inject
    lateinit var accountRepository: AccountRepository

    override fun initView() {
        super.initView()

        Timber.d("DashboardActivity")

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(
                unAuthorizedLogoutBroadcast,
                IntentFilter(KeyUtils.UN_AUTHORIZATION_LOGOUT)
            )

        binding.btnLogout.setOnClickListener {
            sharedPrefs.clear()
            LoginActivity.start(this)
        }
    }

    private val unAuthorizedLogoutBroadcast = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            LoginActivity.start(this@DashboardActivity)

        }
    }

    companion object {

        fun startFromDashboard(context: Context) {
            Intent(context, DashboardActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(this)
            }
        }

        fun startFromLoginActivity(context: Context) {
            Intent(context, DashboardActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(this)
            }

        }
    }

    override fun getViewBinding() = ActivityDashboardBinding.inflate(layoutInflater)

}
