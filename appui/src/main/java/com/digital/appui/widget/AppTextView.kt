package com.digital.appui.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import com.digital.appui.R
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

                val typface = a.getString(R.styleable.AppTextView_typeface)
                val drStart = a.getResourceId(R.styleable.AppTextView_drawableStart, 0)
                val drEnd = a.getResourceId(R.styleable.AppTextView_drawableEnd, 0)
                //set type face
                Log.d("ui", "AppTextView typeface: $typeface ");
                if (typface != null && typface.isNotEmpty())
                    typeface = Typeface.createFromAsset(context.assets, typface)
                setCompoundDrawablesRelativeWithIntrinsicBounds(drStart, 0, drEnd, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            a.recycle()
        }
    }

}