package com.example.demogeopagos

import android.app.Application
import com.example.demogeopagos.di.geopagosApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level


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

    override fun onCreate() {
        super.onCreate()

        // start Koin context
        startKoin {
            androidContext(this@GeoPagosApplication)
            androidLogger(Level.DEBUG)
            modules(geopagosApp)
        }
    }
}