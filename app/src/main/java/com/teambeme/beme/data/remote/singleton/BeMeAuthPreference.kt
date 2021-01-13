package com.teambeme.beme.data.remote.singleton

import android.content.Context
import android.content.SharedPreferences

object BeMeAuthPreference {
    private const val AUTH_KEY = "AUTH_TOKEN"

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var userToken: String
        get() = preferences.getString(AUTH_KEY, "BeMe") ?: ""
        set(value) = preferences.edit { it.putString(AUTH_KEY, value) }
}