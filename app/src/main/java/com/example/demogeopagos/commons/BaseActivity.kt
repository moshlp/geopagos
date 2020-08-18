package com.example.demogeopagos.commons

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.demogeopagos.R

open class BaseActivity : AppCompatActivity() {

    companion object {
        lateinit var prefs: AppPreferences
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        prefs = AppPreferences(applicationContext)
    }

}