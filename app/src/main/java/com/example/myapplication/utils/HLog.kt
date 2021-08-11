package com.example.myapplication.utils

import android.util.Log

// Here can be the collection of data for analytics (firebase & etc.) and user feedback

object HLog {
    fun verbose(tag: String, message: String, tr: Throwable) = Log.v(tag, message, tr)
    fun verbose(tag: String, message: String) = Log.v(tag, message)

    fun debug(tag: String, message: String, exception: Exception?) = Log.d(tag, message, exception)
    fun debug(tag: String, message: String) = Log.d(tag, message)

    fun info(tag: String, message: String, tr: Throwable) = Log.i(tag, message, tr)
    fun info(tag: String, message: String) = Log.i(tag, message)

    fun warning(tag: String, message: String, tr: Throwable) = Log.w(tag, message, tr)
    fun warning(tag: String, message: String) = Log.w(tag, message)

    fun error(tag: String, message: String, exception: Exception?) = Log.e(tag, message, exception)
    fun error(tag: String, message: String) = Log.e(tag, message)
}