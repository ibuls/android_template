package com.dev.ui.dialogs

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.dev.R


class CustomAlertDialog(
    private val mContext: Context,
    val msg: String? = "",
    private val positiveText: String?,
    private val negativeText: String = mContext.getString(R.string.no),
    val title: String =/* mContext.getString(R.string.app_name)*/ "",
    private var positiveListener: View.OnClickListener?,
    private var negativeListener: View.OnClickListener?,
    private val showCancelBtn: Boolean = false,
    private val cancelable: Boolean = true,
    val customView: View? = null,
    val currentSelection: Int = -1,
    var useBlueForCancel: Boolean = false,
    var reverseColors: Boolean = false,
) : Dialog(mContext, R.style.ThemeDialogCustom) {


    private lateinit var mTvTitle: TextView
    private lateinit var mBtnCancel: LinearLayout
    private lateinit var mBtnOk: LinearLayout
    private lateinit var mTvCancel: TextView
    private lateinit var mTvOk: TextView
    private lateinit var cancelLayout: LinearLayout
    private lateinit var tvMainTitle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_custom_alert)

        window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)


        if (positiveListener == null) {
            positiveListener = View.OnClickListener {
                dismiss()
            }
        }
        if (negativeListener == null) {
            negativeListener = View.OnClickListener {
                dismiss()
            }
        }



        setCancelable(cancelable)


        tvMainTitle = findViewById(R.id.tvMainTitle)
        mTvCancel = findViewById(R.id.tvCancel)
        mTvOk = findViewById(R.id.tvOk)
        mTvTitle = findViewById(R.id.tvTitle)
        mBtnCancel = findViewById(R.id.btnCancel)
        mBtnOk = findViewById(R.id.btnOk)
        cancelLayout = findViewById(R.id.layoutCancel)

        mTvTitle.text = msg
        mTvCancel.text = negativeText


        if (useBlueForCancel) {
            mBtnCancel.setBackgroundResource(R.drawable.blue_round_rect)
            mTvCancel.setTextColor(ContextCompat.getColor(context, R.color.white))
        } else if (reverseColors) {
            mBtnCancel.setBackgroundResource(R.drawable.blue_round_rect)
            mTvCancel.setTextColor(ContextCompat.getColor(context, R.color.white))

            mBtnOk.setBackgroundResource(R.drawable.borderliner_8dp_lightgray)
            mTvOk.setTextColor(ContextCompat.getColor(context, R.color.darkIndigo))
        } else {
            mBtnCancel.setBackgroundResource(R.drawable.borderliner_8dp_lightgray)
            mTvCancel.setTextColor(ContextCompat.getColor(context, R.color.darkIndigo))
        }

        tvMainTitle.isVisible = title.isNotEmpty()
        tvMainTitle.text = title
        if (showCancelBtn) {
            mBtnCancel.visibility = View.VISIBLE
            cancelLayout.visibility = View.VISIBLE

            if (positiveText == null) {
                mTvOk.text = mContext.getString(R.string.yes)
            } else {
                mTvOk.text = positiveText
            }

        } else {
            mBtnCancel.visibility = View.GONE
            cancelLayout.visibility = View.GONE
            if (positiveText == null) {
                mTvOk.text = "Ok"
            } else {
                mTvOk.text = positiveText
            }
        }

        mBtnOk.setOnClickListener {
            dismiss()
            positiveListener?.onClick(mBtnOk)
        }

        mBtnCancel.setOnClickListener {
            dismiss()
            negativeListener?.onClick(mBtnCancel)
        }


    }
}