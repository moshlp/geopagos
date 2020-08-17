package com.example.demogeopagos.presentation.payment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demogeopagos.R
import com.example.demogeopagos.commons.AppPreferences
import com.example.demogeopagos.commons.BaseActivity
import com.example.demogeopagos.commons.ViewModelFactory
import com.example.demogeopagos.data.api.ApiHelper
import com.example.demogeopagos.data.api.RetrofitBuilder
import com.example.demogeopagos.data.model.PaymentMethodsResponse
import com.example.demogeopagos.databinding.ActivityPaymentMethodsBinding
import com.example.demogeopagos.presentation.payment.adapter.PaymentMethodsAdapter
import com.example.demogeopagos.presentation.payment.viewmodel.PaymentMethodsViewModel
import com.example.demogeopagos.utils.Status

class PaymentMethodsActivity : BaseActivity() {

    private lateinit var viewDataBinding: ActivityPaymentMethodsBinding
    private lateinit var viewModel: PaymentMethodsViewModel
    private lateinit var adapter: PaymentMethodsAdapter

    companion object {
        lateinit var prefs: AppPreferences
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment_methods)
        prefs = AppPreferences(applicationContext)
        setupViewModel();
        initViews()
        subscribeViewModel();
    }

    private fun subscribeViewModel() {
        viewModel.getPaymentMethods().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        viewDataBinding.rvPaymentMethods.visibility = View.VISIBLE
                        viewDataBinding.progressBar.visibility = View.GONE
                        resource.data?.let { pm -> retrieveList(pm) }
                    }
                    Status.ERROR -> {
                        viewDataBinding.rvPaymentMethods.visibility = View.VISIBLE
                        viewDataBinding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        viewDataBinding.progressBar.visibility = View.VISIBLE
                        viewDataBinding.rvPaymentMethods.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(pm: List<PaymentMethodsResponse>) {
        adapter.apply {
            setData(pm)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(PaymentMethodsViewModel::class.java)
    }

    private fun initViews() {
        viewDataBinding.rvPaymentMethods.layoutManager = LinearLayoutManager(this)
        adapter = PaymentMethodsAdapter(viewDataBinding.rvPaymentMethods, PaymentMethodsAdapter.ThumbnailListener {
            it.id?.let { id -> prefs.credit_card_id = id }
            it.name?.let { name -> prefs.credit_card_name = name }
        })
        viewDataBinding.rvPaymentMethods.addItemDecoration(
            DividerItemDecoration(
                viewDataBinding.rvPaymentMethods.context,
                (viewDataBinding.rvPaymentMethods.layoutManager as LinearLayoutManager).orientation
            )
        )
        viewDataBinding.rvPaymentMethods.adapter = adapter
    }
}