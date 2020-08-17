package com.example.demogeopagos.commons

import android.content.Context
import android.content.SharedPreferences

class AppPreferences (context: Context) {
    val PREFS_NAME = "geopagos-datas"
    val CARD_ID = "card_id"
    val CARD_NAME = "card_name"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    var credit_card_id: String?
        get() = prefs.getString(CARD_ID, "")
        set(value) = prefs.edit().putString(CARD_ID, value).apply()

    var credit_card_name: String?
        get() = prefs.getString(CARD_NAME, "")
        set(value) = prefs.edit().putString(CARD_NAME, value).apply()
}