package com.example.demogeopagos.presentation.amount

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demogeopagos.R
import com.example.demogeopagos.commons.AppPreferences
import com.example.demogeopagos.commons.BaseActivity
import com.example.demogeopagos.databinding.ActivityAmountBinding
import com.example.demogeopagos.presentation.amount.viewmodel.AmountViewModel
import com.example.demogeopagos.presentation.payment.PaymentMethodsActivity

class AmountActivity : BaseActivity() {

    private lateinit var viewDataBinding: ActivityAmountBinding
    private var viewModel: AmountViewModel? = null
    private lateinit var prefs : AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefs = AppPreferences(applicationContext)
        viewModel = ViewModelProvider(this).get(AmountViewModel::class.java)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_amount)

        initViews()
        subscribeViewModel()

    }

    private fun subscribeViewModel() {
        viewModel?.currency?.observe(this, Observer {
            viewDataBinding.anamount.setCurrencySymbol(it)
        })
    }

    private fun initViews() {
        viewModel?.getCurrency()
        viewDataBinding.buttonContinue.setOnClickListener {
            doContinue()
        }
    }

    private fun doContinue() {
        if (viewDataBinding.anamount.text?.length != 2){
            prefs.amount = viewDataBinding.anamount.getNumericValue().toString()
            startActivity(Intent(this, PaymentMethodsActivity::class.java))
        } else {
            viewDataBinding.anamount.error = getString(R.string.error_amount)
        }
    }
}