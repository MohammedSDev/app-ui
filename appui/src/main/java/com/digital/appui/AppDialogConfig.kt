package com.digital.appui

import android.view.Gravity

class AppDialogConfig(
    var autoDismiss : Boolean = true
    ,var autoAdapterDismiss : Boolean = true
    ,var windowsBgColor: Int = -1
    ,var dialogWidthSizePercent: Float = 0.92f
    ,var gravity:Int = Gravity.CENTER
){
    operator fun invoke(function: AppDialogConfig.() -> Unit) {

    }
}