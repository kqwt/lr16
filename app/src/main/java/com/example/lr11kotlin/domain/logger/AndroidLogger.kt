package com.example.lr11kotlin.data.logger

import android.util.Log
import com.example.lr11kotlin.domain.logger.Logger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidLogger @Inject constructor() : Logger {

    override fun d(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun e(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) {
            Log.e(tag, message, throwable)
        } else {
            Log.e(tag, message)
        }
    }

    override fun i(tag: String, message: String) {
        Log.i(tag, message)
    }
}