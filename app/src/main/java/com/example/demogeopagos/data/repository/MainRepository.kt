package com.example.demogeopagos.data.repository

import com.example.demogeopagos.data.api.ApiHelper

class MainRepository (private val apiHelper: ApiHelper) {

    suspend fun getPaymentMethods() = apiHelper.getPaymentMethods()
}


