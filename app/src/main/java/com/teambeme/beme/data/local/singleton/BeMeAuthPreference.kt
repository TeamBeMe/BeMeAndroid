package com.teambeme.beme.data.local.singleton

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object BeMeAuthPreference {
    private const val AUTH_KEY = "AUTH_TOKEN"
    private const val FB_KEY = "FB_TOKEN"
    private const val PREF_KEY = "haskhey"
    private const val ID_KEY = "ID_KEY"
    private const val PASSWORD_KEY = "PASSWORD_KEY"
    private const val IS_FIRST_KEY = "ONBOARDING_KEY"

    private lateinit var preferences: SharedPreferences
    private lateinit var authPreferences: SharedPreferences
    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        authPreferences = EncryptedSharedPreferences.create(
            PREF_KEY,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var userToken: String
        get() = preferences.getString(AUTH_KEY, "BeMe") ?: ""
        set(value) = preferences.edit { it.putString(AUTH_KEY, value) }

    var fireBaseToken: String
        get() = preferences.getString(FB_KEY, "FireBeMe") ?: ""
        set(value) = preferences.edit { it.putString(FB_KEY, value) }

    var userId: String
        get() = authPreferences.getString(ID_KEY, "") ?: ""
        set(value) = authPreferences.edit { it.putString(ID_KEY, value) }

    var userPassword: String
        get() = authPreferences.getString(PASSWORD_KEY, "") ?: ""
        set(value) = authPreferences.edit { it.putString(PASSWORD_KEY, value) }

    var isFirst: Boolean
        get() = authPreferences.getBoolean(IS_FIRST_KEY, true)
        set(value) = authPreferences.edit { it.putBoolean(IS_FIRST_KEY, value) }
}