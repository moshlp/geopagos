package com.example.demogeopagos.presentation.installments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demogeopagos.R
import com.example.demogeopagos.commons.BaseActivity
import com.example.demogeopagos.commons.ViewModelFactoryInstallments
import com.example.demogeopagos.data.api.ApiHelper
import com.example.demogeopagos.data.api.RetrofitBuilder
import com.example.demogeopagos.data.model.InstallmentsResponse
import com.example.demogeopagos.databinding.ActivityInstallmentsBinding
import com.example.demogeopagos.presentation.banks.BanksActivity
import com.example.demogeopagos.presentation.banks.viewmodel.BanksViewModel
import com.example.demogeopagos.presentation.installments.adapter.InstallmentsAdapter
import com.example.demogeopagos.presentation.installments.viewmodel.InstallmentsViewModel
import com.example.demogeopagos.presentation.success.SuccessActivity
import com.example.demogeopagos.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class InstallmentsActivity : BaseActivity() {

    private lateinit var viewDataBinding: ActivityInstallmentsBinding
    private lateinit var adapter: InstallmentsAdapter
    private val viewModel by viewModel<InstallmentsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_installments)
        initViews()
        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        val pm = prefs.credit_card_id
        val amount = prefs.amount
        val issuer_id = prefs.bank_id
        if (pm != null && amount != null && issuer_id != null) {
            viewModel.getInstallments(pm, amount, issuer_id).observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            viewDataBinding.rvInstallments.visibility = View.VISIBLE
                            viewDataBinding.progressBar.visibility = View.GONE
                            resource.data?.let { it -> retrieveList(it) }
                        }
                        Status.ERROR -> {
                            viewDataBinding.rvInstallments.visibility = View.VISIBLE
                            viewDataBinding.progressBar.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            viewDataBinding.progressBar.visibility = View.VISIBLE
                            viewDataBinding.rvInstallments.visibility = View.GONE
                        }
                    }
                }
            })
        }
    }

    private fun retrieveList(installments: List<InstallmentsResponse>) {
        if (installments.isEmpty()) {
            val intent = Intent(this, BanksActivity::class.java)
            intent.putExtra("no_installments", true)
            startActivity(intent)
        } else {
            adapter.apply {
                setData(installments[0].payerCosts)
            }
        }


    }

    private fun initViews() {
        viewDataBinding.rvInstallments.layoutManager = LinearLayoutManager(this)
        adapter = InstallmentsAdapter(
            viewDataBinding.rvInstallments,
            InstallmentsAdapter.ThumbnailListener {
                it.recommendedMessage.let { installment_detail ->
                    prefs.installment_detail = installment_detail
                    startActivity(Intent(this, SuccessActivity::class.java))
                }
            })
        viewDataBinding.rvInstallments.addItemDecoration(
            DividerItemDecoration(
                viewDataBinding.rvInstallments.context,
                (viewDataBinding.rvInstallments.layoutManager as LinearLayoutManager).orientation
            )
        )
        viewDataBinding.rvInstallments.adapter = adapter
    }

}