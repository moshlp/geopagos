package com.example.demogeopagos.presentation.banks

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
import com.example.demogeopagos.data.model.BankResponse
import com.example.demogeopagos.databinding.ActivityBanksBinding
import com.example.demogeopagos.presentation.banks.adapter.BanksAdapter
import com.example.demogeopagos.presentation.banks.viewmodel.BanksViewModel
import com.example.demogeopagos.presentation.installments.InstallmentsActivity
import com.example.demogeopagos.presentation.payment.PaymentMethodsActivity
import com.example.demogeopagos.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class BanksActivity : BaseActivity() {

    private lateinit var viewDataBinding: ActivityBanksBinding
    private val viewModel by viewModel<BanksViewModel>()
    private lateinit var adapter: BanksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIntent()
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_banks)
        initViews()
        subscribeViewModel()
    }

    private fun checkIntent() {
        if (intent != null){
            if (intent.getBooleanExtra("no_installments",false)){
                Toast.makeText(applicationContext, R.string.no_installments, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun subscribeViewModel() {
        val pm = prefs.credit_card_id
        if (pm != null) {
            viewModel.getBanks(pm).observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            viewDataBinding.rvBanks.visibility = View.VISIBLE
                            viewDataBinding.progressBar.visibility = View.GONE
                            resource.data?.let { banks -> retrieveList(banks) }
                        }
                        Status.ERROR -> {
                            viewDataBinding.rvBanks.visibility = View.VISIBLE
                            viewDataBinding.progressBar.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            viewDataBinding.progressBar.visibility = View.VISIBLE
                            viewDataBinding.rvBanks.visibility = View.GONE
                        }
                    }
                }
            })
        }
    }

    private fun retrieveList(banks: List<BankResponse>) {
        if (banks.isEmpty()){
            val intent = Intent(this, PaymentMethodsActivity::class.java)
            intent.putExtra("no_banks", true)
            startActivity(intent)
        }
        adapter.apply {
            setData(banks)
        }
    }

    private fun initViews() {
        viewDataBinding.rvBanks.layoutManager = LinearLayoutManager(this)
        adapter = BanksAdapter(viewDataBinding.rvBanks, BanksAdapter.ThumbnailListener {
            it.id?.let { id -> prefs.bank_id = id }
            it.name?.let { name -> prefs.bank_name = name }
            startActivity(Intent(this, InstallmentsActivity::class.java))
        })
        viewDataBinding.rvBanks.addItemDecoration(
            DividerItemDecoration(
                viewDataBinding.rvBanks.context,
                (viewDataBinding.rvBanks.layoutManager as LinearLayoutManager).orientation
            )
        )
        viewDataBinding.rvBanks.adapter = adapter
    }
}