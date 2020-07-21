package com.digital.appui.utils

import android.text.InputFilter
import android.text.Spanned

class AppNoEmojiInputFilter : InputFilter {


    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        return if (source?.find {
                Character.getType(it) == Character.SURROGATE.toInt()
                        || Character.getType(it) == Character.OTHER_SYMBOL.toInt()
            } != null) {
            source.filterNot {
                Character.getType(it) == Character.SURROGATE.toInt()
                        || Character.getType(it) == Character.OTHER_SYMBOL.toInt()
            }
            ""
        } else null

    }
}