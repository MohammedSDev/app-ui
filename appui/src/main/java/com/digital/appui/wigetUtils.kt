package com.digital.appui

import android.content.res.TypedArray
import android.widget.TextView

/**
 * prepare textView with custom type face.
 * */
fun TextView.prepareCustomFontType(a:TypedArray){
    val typeFaceStr = a.getString(R.styleable.AppTextView_typeface)
    if(typeFaceStr.isNotEmptyText())
        setCustomTypeFace(typeFaceStr)
}

/**
 * prepare textView with app drawable. e.g: {app:drawableStart,app:drawableTop}.
 * useful for older android versions..once you use
 *         vectorDrawables.useSupportLibrary = true
 *         AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

 * */
fun TextView.prepareAppDrawable(a:TypedArray){
    val drStart = a.getResourceId(R.styleable.AppTextView_drawableStart,0)
    val drEnd = a.getResourceId(R.styleable.AppTextView_drawableEnd,0)
    val drTop = a.getResourceId(R.styleable.AppTextView_drawableTop,0)
    val drBottom = a.getResourceId(R.styleable.AppTextView_drawableBottom,0)
    setCompoundDrawablesRelativeWithIntrinsicBounds(drStart,drTop,drEnd,drBottom)

}
