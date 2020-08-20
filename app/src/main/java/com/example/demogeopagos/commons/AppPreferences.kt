package com.example.demogeopagos.commons

import android.content.Context
import android.content.SharedPreferences

class AppPreferences (context: Context) {
    val PREFS_NAME = "geopagos-datas"
    val CARD_ID = "card_id"
    val CARD_NAME = "card_name"
    val BANK_ID = "bank_id"
    val BANK_NAME = "bank_name"
    val INSTALLMENT_DETAIL = "installment_detail"
    val AMOUNT = "amount"
    val CURRENCY_SYMBOL = "currency_symbol"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    var currency_symbol: String?
        get() = prefs.getString(CURRENCY_SYMBOL, "")
        set(value) = prefs.edit().putString(CURRENCY_SYMBOL, value).apply()

    var amount: String?
        get() = prefs.getString(AMOUNT, "")
        set(value) = prefs.edit().putString(AMOUNT, value).apply()

    var credit_card_id: String?
        get() = prefs.getString(CARD_ID, "")
        set(value) = prefs.edit().putString(CARD_ID, value).apply()

    var credit_card_name: String?
        get() = prefs.getString(CARD_NAME, "")
        set(value) = prefs.edit().putString(CARD_NAME, value).apply()

    var bank_id: String?
        get() = prefs.getString(BANK_ID, "")
        set(value) = prefs.edit().putString(BANK_ID, value).apply()

    var bank_name: String?
        get() = prefs.getString(BANK_NAME, "")
        set(value) = prefs.edit().putString(BANK_NAME, value).apply()

    var installment_detail: String?
        get() = prefs.getString(INSTALLMENT_DETAIL, "")
        set(value) = prefs.edit().putString(INSTALLMENT_DETAIL, value).apply()

    fun clearPrefs(){
        prefs.edit().clear().commit()
    }
}