package com.example.demogeopagos.presentation.amount

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import com.example.demogeopagos.R
import com.example.demogeopagos.commons.AppPreferences
import com.example.demogeopagos.commons.BaseActivity
import com.example.demogeopagos.databinding.ActivityAmountBinding
import com.example.demogeopagos.presentation.payment.PaymentMethodsActivity
import java.text.NumberFormat
import java.util.*

class AmountActivity : BaseActivity() {

    private lateinit var viewDataBinding: ActivityAmountBinding
    private lateinit var prefs: AppPreferences
    private val locale: Locale = Locale.getDefault()
    val currency: Currency = Currency.getInstance(locale)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefs = AppPreferences(applicationContext)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_amount)

        initViews()
    }

    private fun initViews() {
        viewDataBinding.buttonContinue.setOnClickListener {
            doContinue()
        }
        viewDataBinding.anamount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val stringText = s.toString()
                viewDataBinding.anamount.removeTextChangedListener(this)
                val parsed = parseNumber(stringText)
                val formatted = NumberFormat.getCurrencyInstance(locale).format(parsed / 100)
                viewDataBinding.anamount.setText(formatted)
                viewDataBinding.anamount.setSelection(formatted.length)
                viewDataBinding.anamount.addTextChangedListener(this)
            }
        })
    }

    private fun parseNumber(number : String): Double {
        return number.replace("[${currency.symbol},.]".toRegex(), "").fullTrim().toDouble()
    }

    private fun doContinue() {
        val number = parseNumber(viewDataBinding.anamount.text.toString())
        if (viewDataBinding.anamount.text?.length != 2 || number.equals(0.0)) {
            prefs.amount = number.toString()
            prefs.currency_symbol = currency.symbol
            startActivity(Intent(this, PaymentMethodsActivity::class.java))
        } else {
            viewDataBinding.anamount.error = getString(R.string.error_amount)
        }
    }

    fun String.fullTrim() = trim().replace("\uFEFF", "")
}