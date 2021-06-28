package com.dev.ui.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.dev.R
import com.dev.databinding.DialogCustomAlertBinding

class AppAlertDialog(
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
) : DialogFragment() {

    lateinit var binding: DialogCustomAlertBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogCustomAlertBinding.inflate(inflater, container, false)

        initListeners()
        return binding.root
    }

    private fun initListeners() {
        isCancelable = cancelable

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

        binding.apply {


            tvTitle.text = msg
            tvCancel.text = negativeText

            if (useBlueForCancel) {
                btnCancel.setBackgroundResource(R.drawable.blue_round_rect)
                tvCancel.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            } else if (reverseColors) {
                btnCancel.setBackgroundResource(R.drawable.blue_round_rect)
                tvCancel.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                btnOk.setBackgroundResource(R.drawable.borderliner_8dp_lightgray)
                tvOk.setTextColor(ContextCompat.getColor(requireContext(), R.color.darkIndigo))
            } else {
                btnCancel.setBackgroundResource(R.drawable.borderliner_8dp_lightgray)
                tvCancel.setTextColor(ContextCompat.getColor(requireContext(), R.color.darkIndigo))
            }

            tvMainTitle.isVisible = title.isNotEmpty()
            tvMainTitle.text = title
            if (showCancelBtn) {
                btnCancel.visibility = View.VISIBLE
                layoutCancel.visibility = View.VISIBLE

                if (positiveText == null) {
                    tvOk.text = mContext.getString(R.string.yes)
                } else {
                    tvOk.text = positiveText
                }

            } else {
                btnCancel.visibility = View.GONE
                layoutCancel.visibility = View.GONE
                if (positiveText == null) {
                    tvOk.text = "Ok"
                } else {
                    tvOk.text = positiveText
                }
            }

            btnOk.setOnClickListener {
                dismiss()
                positiveListener?.onClick(btnOk)
            }

            btnCancel.setOnClickListener {
                dismiss()
                negativeListener?.onClick(btnCancel)
            }

        }
    }



    override fun onStart() {
        super.onStart()
        val window: Window? = dialog?.window
        window?.getAttributes()?.let {

            val windowParams: WindowManager.LayoutParams = it
            //windowParams.dimAmount = 0f/*0.90f*/

            windowParams.flags = windowParams.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
            window?.setAttributes(windowParams)
        }
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        );
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getDialog()?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme)
    }
}