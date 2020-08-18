package com.example.demogeopagos.presentation.success

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.demogeopagos.R
import com.example.demogeopagos.commons.BaseActivity
import com.example.demogeopagos.databinding.ActivitySuccessBinding
import com.example.demogeopagos.presentation.amount.AmountActivity

class SuccessActivity : BaseActivity() {

    private lateinit var viewDataBinding: ActivitySuccessBinding
    private var viewModel: SuccessViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_success)
        initViewModel()
        initViews()
    }

    private fun initViews() {
        viewDataBinding.buttonConfirm.setOnClickListener {
            viewDataBinding.progressBar.visibility = View.VISIBLE
            Handler().postDelayed(
                {
                    viewDataBinding.progressBar.visibility = View.GONE
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Success!")
                    builder.setMessage("Your transaction has been sent successfully")
                    builder.setPositiveButton(android.R.string.ok) { dialog, which ->
                        prefs.clearPrefs()
                        startActivity(Intent(this, AmountActivity::class.java))
                        finish()
                    }
                    builder.show()
                },
                3000
            )

        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SuccessViewModel::class.java)
        viewDataBinding.viewmodel = viewModel
    }
}