package com.example.demogeopagos.utils

import java.net.URL
import java.net.URLConnection

class Utils {

    companion object {
        fun checkImage(url: String): Boolean {
            val connection: URLConnection = URL(url).openConnection()
            val contentType: String = connection.getHeaderField("Content-Type")
            return contentType.startsWith("image/")
        }
    }
}