package com.dev.ui.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dev.R
import com.dev.ui.dialogs.AppAlertDialog
import com.dev.ui.dialogs.CustomAlertDialog

abstract class BaseActivity : AppCompatActivity() {


    companion object{
        var dialogspinner: Dialog? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    abstract fun initViews()
    abstract fun initListener()
    abstract fun setData()

    fun initAll(){
        initViews()
        initListener()
        setData()
    }

    fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


    fun hideLoader() {
        dialogspinner?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    fun showLoader() {
        hideLoader()
        if (!isFinishing) {
            dialogspinner =
                Dialog(this, R.style.startDialog)

            dialogspinner?.let {

                it.window?.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                )
                it.setContentView(R.layout.progressbar)
                it.window?.attributes?.width =
                    ViewGroup.LayoutParams.WRAP_CONTENT
                it.window?.attributes?.height =
                    ViewGroup.LayoutParams.WRAP_CONTENT
                it.setCancelable(false)
                it.show()
                it.window?.decorView?.systemUiVisibility = window.decorView.systemUiVisibility
                it.window
                    ?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
            }
        }
    }


    fun showDialog(
        msg: String? = "",
        positiveText: String = getString(R.string.ok),
        negativeText: String = getString(R.string.no),
        title: String = /*getString(R.string.app_name)*/"",
        positiveListener: View.OnClickListener = View.OnClickListener {},
        negativeListener: View.OnClickListener = View.OnClickListener {},
        showCancelBtn: Boolean = false,
        cancelable: Boolean = true,
        customView: View? = null,
        useBlueForCancel:Boolean = false,
        reverseColors:Boolean = false,
    ) {

        AppAlertDialog(
            this,
            msg,
            positiveText,
            negativeText,
            title,
            positiveListener,
            negativeListener,
            showCancelBtn,
            cancelable,
            customView,
            -1,
            useBlueForCancel,
            reverseColors
        ).show(supportFragmentManager,"")

    }


}