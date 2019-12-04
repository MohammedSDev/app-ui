package com.digital.appui.dialog

import android.view.Gravity

class AppDialogConfig(
    /**
     * auto dismiss after on click
     * */
    var dismissOnClick : Boolean = true
    /**
     *auto dismiss after on Adapter item click
     * */
    ,var dismissOnAdapterItemClick : Boolean = true
    /**
     * dialog window background color
     * */
    ,var windowsBgColor: Int = -1
    /**
     * dialog width size percent agents screen with
     * default:0.92f
     * */
    ,var dialogWidthSizePercent: Float = 0.92f
    /**
     * dialog gravity
     * */
    ,var gravity:Int = Gravity.CENTER
){
    operator fun invoke(function: AppDialogConfig.() -> Unit) {

    }
}