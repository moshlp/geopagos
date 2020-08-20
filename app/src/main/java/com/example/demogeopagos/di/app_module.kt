package com.example.demogeopagos.di

import com.example.demogeopagos.data.api.ApiHelper
import com.example.demogeopagos.data.api.ApiService
import com.example.demogeopagos.data.api.RetrofitBuilder
import com.example.demogeopagos.data.repository.MainRepository
import com.example.demogeopagos.presentation.banks.viewmodel.BanksViewModel
import com.example.demogeopagos.presentation.installments.viewmodel.InstallmentsViewModel
import com.example.demogeopagos.presentation.payment.viewmodel.PaymentMethodsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val geopagosModule = module {
    viewModel { BanksViewModel(get()) }
    viewModel { PaymentMethodsViewModel(get()) }
    viewModel { InstallmentsViewModel(get()) }


    single {
        MainRepository(get())
    }

    single {
        ApiHelper(get())
    }

    single {
        RetrofitBuilder.apiService
    }

}

val geopagosApp = listOf(geopagosModule)