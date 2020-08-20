package com.example.demogeopagos.commons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demogeopagos.data.api.ApiHelper
import com.example.demogeopagos.data.repository.MainRepository
import com.example.demogeopagos.presentation.banks.viewmodel.BanksViewModel

class ViewModelFactoryBanks(
    private val apiHelper: ApiHelper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BanksViewModel::class.java)) {
            return BanksViewModel(
                MainRepository(apiHelper)
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")

    }
}