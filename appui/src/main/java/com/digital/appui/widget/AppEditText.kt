package com.digital.appui.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import com.digital.appui.R
import com.digital.appui.prepareAppDrawable
import com.digital.appui.prepareCustomFontType
import java.lang.Exception

/**
 * Created by Gg on 1/27/2019.
 */
class AppEditText : AppCompatEditText {

    private var validationCB: ((isValid: Boolean, tag: Any?) -> Unit)? = null
    private var validation: (EditText.() -> Pair<Boolean, Any?>)? = null
    private var originText:String? = null
    private var  mAttributeSet:AttributeSet? = null


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
        prepareCustomFontType(this,a)
        prepareAppDrawable(this,a)
    }



    // --------------------

    /**
     * set custom validation
     * */
    fun setValidation(block: EditText.() -> Pair<Boolean, Any?>) {
        this.validation = block
    }

    /**
     * return validation result or `true` if validation null.
     * */
    fun isValid(): Boolean {
        val r = validation?.invoke(this)
        validationCB?.invoke(r?.first?:true,r?.second)
        return r?.first ?: true
    }

    /**
     * handle validate call back
     * */
    fun setOnValidationCB(block:(isValid:Boolean, tag:Any?)->Unit){
        this.validationCB = block
    }


    /**
     * return true if text has changed by user input.
     * */
    fun isUserChangeText() = !originText.equals(text?.toString())

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)
        updateTextChanged()
    }

    /**
     * update the text has changed official.
     * */
    fun updateTextChanged(){
        originText = text?.toString()
    }


    fun setBgColoe(color:Int){
        background.mutate().setColorFilter(color,PorterDuff.Mode.SRC_ATOP)
    }

}