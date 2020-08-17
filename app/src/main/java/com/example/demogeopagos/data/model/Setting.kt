package com.example.demogeopagos.data.model

import com.example.demogeopagos.data.model.Bin
import com.example.demogeopagos.data.model.CardNumber
import com.example.demogeopagos.data.model.SecurityCode
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class Setting (
    @SerializedName("card_number")
    @Expose
    var cardNumber: CardNumber? = null,

    var bin: Bin? = null,

    @SerializedName("security_code")
    @Expose
    var securityCode: SecurityCode? = null
)