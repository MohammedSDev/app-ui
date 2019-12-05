package com.digital.appui.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import com.digital.appui.R
import com.digital.appui.prepareAppDrawable
import com.digital.appui.prepareCustomFontType
import java.lang.Exception

/**
 * Created by Gg on 1/27/2019.
 */
class AppTextView : TextView {


    var mAttributeSet: AttributeSet? = null

    constructor(context: Context) : super(context) {
        setup(null)
    }

    constructor(context: Context, attributeSet: AttributeSet) :
            super(context, attributeSet) {
        mAttributeSet = attributeSet
        setup(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defaultAttrSet: Int) :
            super(context, attributeSet, defaultAttrSet) {
        mAttributeSet = attributeSet
        setup(attributeSet)
    }


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
        prepareCustomFontType(this,a)
        prepareAppDrawable(this,a)
    }

}