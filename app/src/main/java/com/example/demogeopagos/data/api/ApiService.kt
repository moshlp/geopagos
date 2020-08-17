package com.example.demogeopagos.data.api

import com.example.demogeopagos.data.model.PaymentMethodsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.security.PublicKey

interface ApiService {

    @GET("payment_methods")
    suspend fun getPaymentMethods(@Query("public_key") publicKey: String): List<PaymentMethodsResponse>
}