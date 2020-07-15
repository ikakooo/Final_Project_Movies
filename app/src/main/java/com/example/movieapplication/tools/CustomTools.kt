package com.example.movieapplication.tools


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Handler
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager

import com.example.movieapplication.AppRoot
import com.example.movieapplication.R
import kotlinx.android.synthetic.main.error_dialog_layout.*


object CustomTools {

    @Suppress("DEPRECATION")
    @JvmStatic
    fun Context.isConnectedToNetwork(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting == true
    }

    @Suppress("DEPRECATION")
    @SuppressLint("SetTextI18n")
    fun networkErrorDialog(context: Context) {
        val dialog = Dialog(context)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.error_dialog_layout)

        val params: ViewGroup.LayoutParams = dialog.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = params as WindowManager.LayoutParams
        dialog.dialogTitleTextViewID.text = "No Internet Connection"
        dialog.dialogDescriptionTextViewID.text = "Please Connect The Internet"
        dialog.dialogOkButton.setOnClickListener {
            dialog.dismiss()
            if (!AppRoot.instance.getContext().isConnectedToNetwork()) {
                Handler().postDelayed({
                    dialog.show()
                }, 2000)

            }
        }
        dialog.show()
    }
}