package com.dev.ui.bottomsheet

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.R
import com.dev.databinding.FragmentDefaultBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DefaultBottomsheetFragment() : BottomSheetDialogFragment() {

    lateinit var binding: FragmentDefaultBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDefaultBottomSheetBinding.inflate(inflater,container,false)

        initListener()

        return binding.root
    }

    private fun initListener() {
        binding.apply {

            btnOption1.setOnClickListener {
                listener.onOption1()
            }

            btnOption2.setOnClickListener {
                listener.onOption2()
            }

            cbCheck.setOnClickListener {
                cbCheck.isSelected = cbCheck.isSelected.not()
            }
        }
    }


    interface ActionListener {
        fun onOption1()
        fun onOption2()
    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        (view?.parent as View?)?.setBackgroundColor(Color.TRANSPARENT)
    }


    lateinit var listener: ActionListener

    companion object {
        @JvmStatic
        fun newInstance(
            listener: ActionListener
        ): DefaultBottomsheetFragment {
            val fragment = DefaultBottomsheetFragment()
            fragment.listener = listener

            return fragment
        }
    }

}