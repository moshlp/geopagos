package com.example.demogeopagos.data.api

const val  PUBLIC_KEY : String = "TEST-8eb053e5-edd4-4c5d-a2d0-141e77847883"

class ApiHelper(private val apiService: ApiService) {

    suspend fun getPaymentMethods() = apiService.getPaymentMethods(PUBLIC_KEY)

    suspend fun getBanks(pms : String) = apiService.getBanks(PUBLIC_KEY, pms)

    suspend fun getInstallments(pms : String, amount : String, issuer_id : String) = apiService.getInstallments(PUBLIC_KEY, pms, amount, issuer_id)

}