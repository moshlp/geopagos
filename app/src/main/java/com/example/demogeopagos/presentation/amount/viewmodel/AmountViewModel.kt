package com.example.demogeopagos.presentation.amount.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class AmountViewModel : ViewModel() {

    var currency = MutableLiveData<String>()

    fun getCurrency() {
        currency.postValue(Currency.getInstance(Locale.getDefault()).getSymbol(Locale.getDefault()))
    }
}