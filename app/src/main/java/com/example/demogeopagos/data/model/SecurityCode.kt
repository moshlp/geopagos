package com.example.demogeopagos.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class SecurityCode {
    @SerializedName("length")
    @Expose
    var length: Int? = null

    @SerializedName("card_location")
    @Expose
    var cardLocation: String? = null

    @SerializedName("mode")
    @Expose
    var mode: String? = null
}