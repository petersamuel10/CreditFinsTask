package com.peter.creditfins.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.peter.creditfins.R
import javax.inject.Singleton

@Singleton
class ProgressDialog(private val context: Context) {

    private var dialog: Dialog? = null

    fun show() {
        if (dialog != null && dialog!!.isShowing) {
            return
        }
        dialog = Dialog(context)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setContentView(R.layout.progressbar_dialog_layout)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.show()
    }

    fun dismiss() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }

}