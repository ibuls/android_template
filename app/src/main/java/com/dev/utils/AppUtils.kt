package com.dev.utils

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.dev.R

class AppUtils {
    private companion object{
        val DELAY_IN_MS: Long = 500
    }


    fun View.preventMultiClick() {
        if (!isClickable) {
            return
        }
        isClickable = false
        postDelayed({ isClickable = true }, DELAY_IN_MS)
    }



}