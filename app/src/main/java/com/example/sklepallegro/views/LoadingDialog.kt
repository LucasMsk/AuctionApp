package com.example.sklepallegro.views

import android.app.Activity
import android.app.Dialog
import com.example.sklepallegro.R

class LoadingDialog(activity: Activity) {

    private val dialog: Dialog = Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar)

    init {
        dialog.setContentView(activity.layoutInflater.inflate(R.layout.dialog_loading,null))
        dialog.setCancelable(false)
    }

    fun show() {
        dialog.show()
    }

    fun hide() {
        dialog.dismiss()
    }
}