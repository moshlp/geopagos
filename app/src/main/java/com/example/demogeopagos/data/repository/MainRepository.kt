package com.example.demogeopagos.data.repository

import com.example.demogeopagos.data.api.ApiHelper

class MainRepository (private val apiHelper: ApiHelper) {

    suspend fun getPaymentMethods() = apiHelper.getPaymentMethods()

    suspend fun getBanks(pms : String) = apiHelper.getBanks(pms)

    suspend fun getInstallments(pms : String, amount : String, issuer_id : String) = apiHelper.getInstallments(pms, amount, issuer_id)
}


