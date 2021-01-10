package com.teambeme.beme.login.view

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.remote.datasource.LoginDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.ActivityLoginBinding
import com.teambeme.beme.login.repository.LoginRepositoryImpl
import com.teambeme.beme.login.viewmodel.LoginViewModel
import com.teambeme.beme.login.viewmodel.LoginViewModelFactory
import com.teambeme.beme.util.KeyboardVisibilityUtils
import com.teambeme.beme.util.StatusBarUtil
import kotlin.properties.Delegates

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var keyboardVisibilityUtils: KeyboardVisibilityUtils
    private var visibleDisplayFrameHeight by Delegates.notNull<Int>()
    private var keyboardHeight by Delegates.notNull<Int>()
    private val loginViewModelFactory =
        LoginViewModelFactory(LoginRepositoryImpl(LoginDataSourceImpl(RetrofitObjects.getLoginService())))
    private val loginViewModel: LoginViewModel by viewModels { loginViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this
        setKeyboardVisibilityListener()
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
        loginViewModel.responseValue.observe(this) { data ->
            Log.d("Network data", "$data")
            if (data != null) {
                Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setKeyboardVisibilityListener() {
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

    override fun onDestroy() {
        super.onDestroy()
        keyboardVisibilityUtils.detachKeyboardListeners()
    }
}