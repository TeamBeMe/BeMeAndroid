package com.teambeme.beme.login.view

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.local.singleton.BeMeAuthPreference
import com.teambeme.beme.data.remote.datasource.LoginDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.ActivityLoginBinding
import com.teambeme.beme.login.model.ResponseLogin
import com.teambeme.beme.data.repository.LoginRepositoryImpl
import com.teambeme.beme.login.viewmodel.LoginViewModel
import com.teambeme.beme.login.viewmodel.LoginViewModelFactory
import com.teambeme.beme.main.view.MainActivity
import com.teambeme.beme.signup.view.SignUpActivity
import com.teambeme.beme.util.KeyboardVisibilityUtils
import com.teambeme.beme.util.StatusBarUtil
import com.teambeme.beme.util.recordClickEvent

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var keyboardVisibilityUtils: KeyboardVisibilityUtils
    private var visibleDisplayFrameHeight = 0
    private var keyboardHeight = 0
    private val loginViewModelFactory =
        LoginViewModelFactory(LoginRepositoryImpl(LoginDataSourceImpl(RetrofitObjects.getLoginService())))
    private val loginViewModel: LoginViewModel by viewModels { loginViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this
        initView()
    }

    private fun initView() {
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
        setUIListener()
        subscribeData()
    }

    private fun setUIListener() {
        with(binding) {
            btnLoginSignup.setOnClickListener {
                recordClickEvent("BUTTON", "CLICK_SIGN_SIGN")
                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            }
            btnRegisterFindId.setOnClickListener { recordClickEvent("BUTTON", "CLICK_SEARCHID_LOGIN") }
            btnRegisterFindPassword.setOnClickListener { recordClickEvent("BUTTON", "CLICK_FINDPWD_LOGIN") }
            txtlayoutLoginPassword.setEndIconOnClickListener { loginViewModel?.setShowPassword() }
        }
        keyboardVisibilityUtils = KeyboardVisibilityUtils(window,
            onShowKeyboard = { keyboardHeight, visibleDisplayFrameHeight ->
                this.keyboardHeight = keyboardHeight
                this.visibleDisplayFrameHeight = visibleDisplayFrameHeight
                val animator = setValueChangeAnimator(0, visibleDisplayFrameHeight - keyboardHeight)
                animator.addUpdateListener { updatedAnimation ->
                    binding.constraintLoginView
                        .setPaddingBottomAnimator(updatedAnimation)
                }
            },
            onHideKeyboard = {
                val animator = setValueChangeAnimator(visibleDisplayFrameHeight - keyboardHeight, 0)
                animator.addUpdateListener { updatedAnimation ->
                    binding.constraintLoginView
                        .setPaddingBottomAnimator(updatedAnimation)
                }
            }
        )
    }

    private fun subscribeData() {
        loginViewModel.responseValue.observe(this) { data ->
            runCatching { requireNotNull(data) { "로그인에 실패하였습니다. 다시 입력해주세요" } }
                .onSuccess {
                    if (it.success) loginBy(it)
                    else "로그인 실패 ${it.message}".toast()
                }.onFailure { it.message!!.toast() }
        }
        loginViewModel.showPassword.observe(this) { showPassword ->
            showPassword?.let {
                if (showPassword) {
                    binding.txtlayoutLoginPassword.setEndIconDrawable(R.drawable.ic_hide_password)
                    binding.editxtLoginPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                } else {
                    binding.txtlayoutLoginPassword.setEndIconDrawable(R.drawable.ic_show_password)
                    binding.editxtLoginPassword.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                }
            }
        }
        loginViewModel.errorMessage.observe(this) { errorMessage ->
            if (!errorMessage.isNullOrBlank()) { binding.linearLoginError.visibility = View.VISIBLE }
        }
    }

    private fun loginBy(it: ResponseLogin) {
        BeMeAuthPreference.userToken = it.data!!.token
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        "로그인 성공".toast()
    }

    private fun setValueChangeAnimator(from: Int, to: Int): ValueAnimator {
        return ValueAnimator.ofInt(from, to).apply {
            duration = 400
            start()
        }
    }

    private fun ConstraintLayout.setPaddingBottomAnimator(valueAnimator: ValueAnimator) {
        this.setPadding(
            0,
            0,
            0,
            valueAnimator.animatedValue as Int
        )
    }

    private fun String.toast() {
        Toast.makeText(this@LoginActivity, this, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        keyboardVisibilityUtils.detachKeyboardListeners()
    }
}