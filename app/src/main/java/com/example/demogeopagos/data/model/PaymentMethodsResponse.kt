package com.example.demogeopagos.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class PaymentMethodsResponse (

    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("payment_type_id")
    @Expose
    var paymentTypeId: String? = null,

    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("secure_thumbnail")
    @Expose
    var secureThumbnail: String? = null,

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null,

    @SerializedName("deferred_capture")
    @Expose
    var deferredCapture: String? = null,

    @SerializedName("settings")
    @Expose
    var settings: List<Setting>? = null,

    @SerializedName("additional_info_needed")
    @Expose
    var additionalInfoNeeded: List<String>? = null,

    @SerializedName("min_allowed_amount")
    @Expose
    var minAllowedAmount: Double? = null,

    @SerializedName("max_allowed_amount")
    @Expose
    var maxAllowedAmount: Int? = null,

    @SerializedName("accreditation_time")
    @Expose
    var accreditationTime: Any? = null,

    @SerializedName("financial_institutions")
    @Expose
    var financialInstitutions: List<Any>? = null,

    @SerializedName("processing_modes")
    @Expose
    var processingModes: List<String>? = null

)