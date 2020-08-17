package com.example.demogeopagos.presentation.amount.viewmodel

import androidx.lifecycle.ViewModel
import java.util.*

class AmountViewModel : ViewModel() {

    var currency = Currency.getInstance(Locale.getDefault()).getSymbol(Locale.getDefault())




}