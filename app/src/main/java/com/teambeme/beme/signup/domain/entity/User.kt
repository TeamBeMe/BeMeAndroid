package com.teambeme.beme.signup.domain.entity

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

data class User(
    val email: String,
    val nickName: String,
    val password: String
) {
    fun toRequestBody(): HashMap<String, RequestBody> {
        return hashMapOf(
            "email" to email.toRequestBody("text/plain".toMediaTypeOrNull()),
            "nickname" to nickName.toRequestBody("text/plain".toMediaTypeOrNull()),
            "password" to password.toRequestBody("text/plain".toMediaTypeOrNull())
        )
    }
}
