package com.nikolasdion.moodjournal.util

import android.util.Log

/**
 * Logger class.
 */
class Logger(private val tag: String) {

    fun e(message: String) {
        Log.e(tag, message)
    }

    fun w(message: String) {
        Log.w(tag, message)
    }

    fun i(message: String) {
        Log.i(tag, message)
    }

    fun d(message: String) {
        Log.d(tag, message)
    }

    fun v(message: String) {
        Log.v(tag, message)
    }
}