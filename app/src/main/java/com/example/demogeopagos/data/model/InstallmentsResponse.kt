package com.example.demogeopagos.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class InstallmentsResponse (
    @SerializedName("payment_method_id")
    @Expose
    var paymentMethodId: String? = null,

    @SerializedName("payment_type_id")
    @Expose
    var paymentTypeId: String? = null,
    var issuer: Issuer? = null,

    @SerializedName("processing_mode")
    @Expose
    var processingMode: String? = null,

    @SerializedName("merchant_account_id")
    @Expose
    var merchantAccountId: Any? = null,

    @SerializedName("payer_costs")
    @Expose
    var payerCosts: List<PayerCost>? = null,

    @SerializedName("agreements")
    @Expose
    var agreements: Any? = null
)