package com.digital.appui

import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan

class CustomTypeFaceSpan(val typeface: Typeface?) : MetricAffectingSpan(){
        override fun updateMeasureState(p0: TextPaint) {
            p0.typeface = typeface
        }

        override fun updateDrawState(p0: TextPaint?) {
            p0?.typeface = typeface
        }

    }
