package com.teambeme.beme.setting.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.local.singleton.BeMeRepository
import com.teambeme.beme.databinding.ActivitySettingBinding
import com.teambeme.beme.login.view.LoginActivity
import com.teambeme.beme.util.StatusBarUtil

class SettingActivity : BindingActivity<ActivitySettingBinding>(R.layout.activity_setting) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
        setUIListener()
    }

    private fun setUIListener() {
        binding.constraintSettingReport.setOnClickListener { reportClickListener() }
        binding.constraintSettingOpensource.setOnClickListener { navigateOpenSource() }
        binding.btnSettingBack.setOnClickListener { finish() }
        binding.txtSettingLogout.setOnClickListener { logout() }
    }

    private fun logout() {
        with(BeMeRepository) {
            userId = ""
            userPassword = ""
            userToken = ""
        }
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        Toast.makeText(this, "로그아웃되었습니다.", Toast.LENGTH_LONG).show()
        finish()
    }

    private fun reportClickListener() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("teambeme@naver.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "BeMe 문의 ")
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "1. 문의 유형 ( 문의, 버그 제보, 탈퇴하기, 기타) : \n" +
                "2. 회원 닉네임 (필요시 기입) :\n" +
                "3. 문의 내용 :\n" +
                "\n" +
                "문의하신 사항은 BeMe팀이 신속하게 처리하겠습니다. 감사합니다 :)"
        )
        intent.type = "message/rfc822"
        startActivity(intent)
    }

    private fun navigateOpenSource() {
        val intent = Intent(this, OpenSourceActivity::class.java)
        startActivity(intent)
    }
}
