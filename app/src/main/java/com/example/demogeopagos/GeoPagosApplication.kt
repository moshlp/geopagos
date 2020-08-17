package com.example.demogeopagos

import android.app.Application

class GeoPagosApplication : Application() {

    fun GeoPagosApplication() {
        instance = this
    }
    companion object {
        private var instance: GeoPagosApplication? = null
        fun getContext(): GeoPagosApplication? {
            return instance
        }
    }
}