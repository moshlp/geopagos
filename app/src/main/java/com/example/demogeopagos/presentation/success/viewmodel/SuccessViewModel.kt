package com.example.demogeopagos.presentation.success.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.demogeopagos.commons.AppPreferences
import com.example.demogeopagos.data.model.Transaction
import java.text.NumberFormat

class SuccessViewModel (application: Application) : AndroidViewModel(application) {

    private val prefs = AppPreferences(getApplication<Application>().applicationContext)

    fun getTransaction() : Transaction{
        return Transaction("Amount: " + prefs.currency_symbol + prefs.amount, "Payment Method: " + prefs.credit_card_name, "Bank: " + prefs.bank_name, "Installment: " + prefs.installment_detail)
    }


}