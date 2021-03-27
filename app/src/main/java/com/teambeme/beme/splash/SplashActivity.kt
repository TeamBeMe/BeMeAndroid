package com.teambeme.beme.splash

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets.*
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.local.singleton.BeMeAuthPreference
import com.teambeme.beme.data.repository.LoginRepository
import com.teambeme.beme.databinding.ActivitySplashBinding
import com.teambeme.beme.login.model.ResponseLogin
import com.teambeme.beme.login.view.LoginActivity
import com.teambeme.beme.main.view.MainActivity
import com.teambeme.beme.onboarding.view.OnBoardingActivity
import com.teambeme.beme.util.ErrorBody
import com.teambeme.beme.util.StatusBarUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    @Inject
    lateinit var loginRepository: LoginRepository
    private val appUpdateManager by lazy() { AppUpdateManagerFactory.create(this) }
    private val REQUEST_CODE_UPDATE = 9999
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBar(this, R.color.black)
        initInAppUpdate(false)
        setFullScreen()
    }

    override fun onResume() {
        super.onResume()

        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                runCatching {
                    appUpdateManager.startUpdateFlowForResult(
                        it,
                        AppUpdateType.IMMEDIATE, // or AppUpdateType.IMMEDIATE
                        this,
                        REQUEST_CODE_UPDATE
                    )
                }.onFailure {
                    it.printStackTrace()
                }
            }
        }
    }

    private fun setFullScreen() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let {
                it.hide(Type.statusBars() or Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        }
    }

    private fun navigateToOnBoarding() {
        val intent =
            Intent(this, OnBoardingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    private fun setNextActivity() {
        if (BeMeAuthPreference.userId.isEmpty()) {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        } else {
            requestLogin()
        }
    }

    private fun requestLogin() {
        loginRepository.login(
            BeMeAuthPreference.userId,
            BeMeAuthPreference.userPassword
        ).enqueue(object :
            Callback<ResponseLogin> {
            override fun onResponse(
                call: Call<ResponseLogin>,
                response: Response<ResponseLogin>
            ) {
                if (response.isSuccessful) {
                    BeMeAuthPreference.userToken =
                        response.body()!!.data!!.token
                    val intent =
                        Intent(this@SplashActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                } else {
                    val gson = Gson()
                    val type = object : TypeToken<ErrorBody>() {}.type
                    val errorResponse: ErrorBody? =
                        gson.fromJson(response.errorBody()!!.charStream(), type)
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                for (element in t.stackTrace) {
                    Log.d("Network element", element.toString())
                    Log.d("Network className", element.className)
                    Log.d("Network methodName", element.methodName)
                    Log.d("Network fileName", element.fileName)
                    Log.d("Network lineNumber", element.lineNumber.toString())
                }
            }
        })
    }

    private fun initInAppUpdate(inAppUpdateAvailable: Boolean) {
        if (inAppUpdateAvailable) {
            val appUpdateInfoTask = appUpdateManager.appUpdateInfo
            appUpdateManager.registerListener(object : InstallStateUpdatedListener {
                override fun onStateUpdate(state: InstallState) {
                    when (state.installStatus()) {
                        InstallStatus.DOWNLOADED -> {
                            popupSnackbarForCompleteUpdate()
                        }
                        InstallStatus.INSTALLED -> {
                            appUpdateManager.unregisterListener(this)
                            navigateNextScreen()
                        }
                        else -> {
                        }
                    }
                }
            })

            appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
                when (appUpdateInfo.updateAvailability()) {
                    UpdateAvailability.UPDATE_AVAILABLE -> {
                        appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
                        requestUpdate(appUpdateInfo)
                    }
                    else -> {
                        navigateNextScreen()
                    }
                }
            }
        } else {
            navigateNextScreen()
        }
    }

    private fun popupSnackbarForCompleteUpdate() {
        Snackbar.make(
            binding.root,
            "업데이트가 완료되었습니다.",
            Snackbar.LENGTH_INDEFINITE
        ).apply {
            setAction("RESTART") { appUpdateManager.completeUpdate() }
            setActionTextColor(Color.WHITE)
            show()
        }
    }

    private fun requestUpdate(appUpdateInfo: AppUpdateInfo) {
        runCatching {
            appUpdateManager.startUpdateFlowForResult(
                appUpdateInfo,
                AppUpdateType.IMMEDIATE,
                this,
                REQUEST_CODE_UPDATE
            )
        }.onFailure {
            it.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_UPDATE -> {
                if (resultCode != Activity.RESULT_OK) {
                    Toast.makeText(this, "업데이트가 취소되었습니다.", Toast.LENGTH_SHORT).show()
                    finishAffinity()
                }
            }
            else -> {
            }
        }
    }

    private fun navigateNextScreen() {
        lifecycleScope.launchWhenCreated {
            delay(1000L)
            if (BeMeAuthPreference.isFirst) {
                navigateToOnBoarding()
            } else {
                setNextActivity()
            }
        }
    }
}
