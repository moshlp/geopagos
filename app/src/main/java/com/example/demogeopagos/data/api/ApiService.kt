package com.example.demogeopagos.data.api

import com.example.demogeopagos.data.model.BankResponse
import com.example.demogeopagos.data.model.InstallmentsResponse
import com.example.demogeopagos.data.model.PaymentMethodsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("payment_methods")
    suspend fun getPaymentMethods(@Query("public_key") publicKey: String): List<PaymentMethodsResponse>

    @GET("payment_methods/card_issuers")
    suspend fun getBanks(@Query("public_key") publicKey: String, @Query("payment_method_id") pmi : String): List<BankResponse>

    @GET("payment_methods/installments")
    suspend fun getInstallments(@Query("public_key") publicKey: String, @Query("payment_method_id") pmi : String, @Query("amount") amount : String, @Query("issuer.id") issuer_id : String): List<InstallmentsResponse>
}