package com.digital.appui

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.widget.TextView
import java.lang.Exception

/**
 * prepare textView with custom type face.
 * */
fun prepareCustomFontType(textView:TextView,a:TypedArray){
    val typeFaceStr = a.getString(R.styleable.AppTextView_typeface)
    if(typeFaceStr.isNotEmpty())
        setCustomTypeFace(textView,typeFaceStr)
}

/**
 * prepare textView with app drawable. e.g: {app:drawableStart,app:drawableTop}.
 * useful for older android versions..once you use
 *         vectorDrawables.useSupportLibrary = true
 *         AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

 * */
fun prepareAppDrawable(textView:TextView,a:TypedArray){
    val drStart = a.getResourceId(R.styleable.AppTextView_drawableStart,0)
    val drEnd = a.getResourceId(R.styleable.AppTextView_drawableEnd,0)
    val drTop = a.getResourceId(R.styleable.AppTextView_drawableTop,0)
    val drBottom = a.getResourceId(R.styleable.AppTextView_drawableBottom,0)
    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drStart,drTop,drEnd,drBottom)

}


fun setCustomTypeFace(textView:TextView,fontTypeName: String) {
    getTypeFace(textView.context, fontTypeName)?.let { fontFace ->
        textView.typeface = fontFace
    }
}


/**
 * get typeface for font inside assets folder.
 * */
fun getTypeFace(context: Context?, fontTypeName: String): Typeface? {
    context?.let {
        try {
            return Typeface.createFromAsset(it.assets, fontTypeName)
        } catch (ex: Exception) {
            return null
        }
    }

    return null
}
