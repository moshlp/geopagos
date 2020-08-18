package com.example.demogeopagos.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class PayerCost (
    var installments: Int? = null,

    @SerializedName("installment_rate")
    @Expose
    var installmentRate: Float? = null,

    @SerializedName("discount_rate")
    @Expose
    var discountRate: Int? = null,

    @SerializedName("reimbursement_rate")
    @Expose
    var reimbursementRate: Any? = null,
    var labels: List<String>? = null,

    @SerializedName("installment_rate_collector")
    @Expose
    var installmentRateCollector: List<String>? = null,

    @SerializedName("min_allowed_amount")
    @Expose
    var minAllowedAmount: Int? = null,

    @SerializedName("max_allowed_amount")
    @Expose
    var maxAllowedAmount: Int? = null,

    @SerializedName("recommended_message")
    @Expose
    var recommendedMessage: String? = null,

    @SerializedName("installment_amount")
    @Expose
    var installmentAmount: Float? = null,

    @SerializedName("total_amount")
    @Expose
    var totalAmount: Float? = null,

    @SerializedName("payment_method_option_id")
    @Expose
    var paymentMethodOptionId: String? = null
)