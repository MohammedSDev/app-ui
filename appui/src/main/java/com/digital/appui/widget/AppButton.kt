package com.digital.appui.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.Button
import com.digital.appui.R
import com.digital.appui.prepareCustomFontType
//import com.google.android.material.button.MaterialButton
import java.lang.Exception

class AppButton : Button {

    var  mAttributeSet: AttributeSet? = null
    constructor(context: Context):super(context){
        setup(null)
    }
    constructor(context: Context, attributeSet: AttributeSet):
            super(context,attributeSet){
        mAttributeSet = attributeSet
        setup(attributeSet)
    }
    constructor(context: Context, attributeSet: AttributeSet, defaultAttrSet: Int):
            super(context,attributeSet,defaultAttrSet){
        mAttributeSet = attributeSet
        setup(attributeSet)}


    private fun setup(attributeSet: AttributeSet?) {

        attributeSet?.let {
            val a = context.obtainStyledAttributes(attributeSet, R.styleable.AppTextView, 0, 0);
            try {
                prepareAttributes(a)

            } catch (e: Exception) {
                e.printStackTrace()
            }
            a.recycle()
        }
    }

    private fun prepareAttributes(a: TypedArray) {
        prepareCustomFontType(a)
    }


}