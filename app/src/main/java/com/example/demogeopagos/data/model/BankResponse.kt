package com.example.demogeopagos.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class BankResponse (

    var id: String? = null,
    var name: String? = null,

    @SerializedName("secure_thumbnail")
    @Expose
    var secureThumbnail: String? = null,
    var thumbnail: String? = null,

    @SerializedName("processing_mode")
    @Expose
    var processingMode: String? = null,

    @SerializedName("merchant_account_id")
    @Expose
    var merchantAccountId: Any? = null
)