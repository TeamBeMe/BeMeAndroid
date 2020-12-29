package com.teambeme.beme.mypage.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.teambeme.beme.R
import com.teambeme.beme.reply.view.ReplyActivity

class MyPageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_page, container, false)
        val intent = Intent(view.context, ReplyActivity::class.java)
        startActivity(intent)
        return view
    }
}