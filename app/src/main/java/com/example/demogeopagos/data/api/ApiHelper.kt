package com.example.demogeopagos.data.api

const val  PUBLIC_KEY : String = "TEST-8eb053e5-edd4-4c5d-a2d0-141e77847883"

class ApiHelper(private val apiService: ApiService) {

    suspend fun getPaymentMethods() = apiService.getPaymentMethods(PUBLIC_KEY)
}