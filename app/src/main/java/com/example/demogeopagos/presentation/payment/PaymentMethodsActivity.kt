package com.example.demogeopagos.presentation.payment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demogeopagos.R
import com.example.demogeopagos.commons.BaseActivity
import com.example.demogeopagos.data.model.PaymentMethodsResponse
import com.example.demogeopagos.databinding.ActivityPaymentMethodsBinding
import com.example.demogeopagos.presentation.banks.BanksActivity
import com.example.demogeopagos.presentation.payment.adapter.PaymentMethodsAdapter
import com.example.demogeopagos.presentation.payment.viewmodel.PaymentMethodsViewModel
import com.example.demogeopagos.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class PaymentMethodsActivity : BaseActivity() {

    private lateinit var viewDataBinding: ActivityPaymentMethodsBinding
    private val viewModel by viewModel<PaymentMethodsViewModel>()
    private lateinit var adapter: PaymentMethodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkIntent()
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment_methods)
        initViews()
        subscribeViewModel();
    }

    private fun checkIntent() {
        if (intent != null) {
            if (intent.getBooleanExtra("no_banks", false)) {
                Toast.makeText(applicationContext, R.string.no_banks, Toast.LENGTH_LONG).show()

            }
        }
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

    private fun initViews() {
        viewDataBinding.rvPaymentMethods.layoutManager = LinearLayoutManager(this)
        adapter = PaymentMethodsAdapter(
            viewDataBinding.rvPaymentMethods,
            PaymentMethodsAdapter.ThumbnailListener {
                it.id?.let { id -> prefs.credit_card_id = id }
                it.name?.let { name -> prefs.credit_card_name = name }
                startActivity(Intent(this, BanksActivity::class.java))
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