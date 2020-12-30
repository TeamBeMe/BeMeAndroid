package com.teambeme.beme.reply.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityReplyBinding
import com.teambeme.beme.reply.adapter.ReplyParentAdapter
import com.teambeme.beme.reply.model.initReplyList

class ReplyActivity : BindingActivity<ActivityReplyBinding>(R.layout.activity_reply) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val replyAdapter=ReplyParentAdapter()
        binding.rcvReplyParent.adapter=replyAdapter


    }
}