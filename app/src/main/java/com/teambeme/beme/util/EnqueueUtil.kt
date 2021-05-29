package com.teambeme.beme.util

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <ResponseType> Call<ResponseType>.bemeEnqueue(
    onSuccess: (ResponseType) -> Unit,
    onFailure: () -> Unit,
    onError: () -> Unit
) {
    this.enqueue(object : Callback<ResponseType> {
        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            response.body()?.let {
                onSuccess(it)
            } ?: onError()
            Log.d("Network On Error", response.toString())
        }

        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            Log.d("Network On Fail", t.message.toString())
            onFailure()
        }
    })
}
