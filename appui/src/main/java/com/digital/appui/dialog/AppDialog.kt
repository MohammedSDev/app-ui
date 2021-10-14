package com.digital.appui.dialog

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import com.digital.appui.*

/**
 * easy & customized app dialog
 * you can pass you
 * */
open class AppDialog(@LayoutRes private var layoutRes: Int) : DialogFragment(),
    View.OnClickListener {

    protected var config: AppDialogConfig =
        AppDialogConfig()
    private var onClickListener: OnDialogViewClick? = null
    private var handlerOnAdapterClickListener: OnDialogAdapterItemClick =
        object : OnDialogAdapterItemClick {
            override fun invoke(view: View, position: Int) {
                onAdapterClickListener?.invoke(view, position)
                if (config.dismissOnAdapterItemClick) dismiss()
            }

        }
    private var onAdapterClickListener: OnDialogAdapterItemClick? = null
    private var prepareView: OnPrepareDialogView? = null
    private var prepareAdapterView: OnPrepareDialogAdapterView? = null
    private var viewsConfig: Boolean = false

    constructor() : this(-1)
    constructor(@LayoutRes layoutRes: Int, config: AppDialogConfig) : this(layoutRes) {
        this.config = config
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (layoutRes == -1) {
//            layoutRes = savedInstanceState?.getInt("layout_res", -1) ?: -1
//            val serializable = savedInstanceState?.getSerializable("config")
//            if (serializable is AppDialogConfig)
//                config = serializable
//        }
        onCreate()
        configObserves()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).also {
            val config = config ?: return@also
            it.setOnShowListener {
                println("setOnShowListener ")
                val d = it as Dialog
                val lp = WindowManager.LayoutParams()
                lp.copyFrom(d.window!!.attributes)
                lp.width = ((context?.resources?.displayMetrics?.widthPixels
                    ?: 0) * config.dialogWidthSizePercent).toInt()
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT
                if (lp.width <= 0)
                    lp.width = WindowManager.LayoutParams.WRAP_CONTENT
                //set dialog bottom
                lp.gravity = config.gravity
                d.window?.attributes = lp
                if (config.windowsBgColor != -1)
                    dialog?.window?.setBackgroundDrawable(ColorDrawable(config.windowsBgColor))//for corner radius

            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("layout_res", layoutRes)
        outState.putSerializable("config", config)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("view is $view")
        if (layoutRes == -1) {
            dismiss()
            return null
        }
        return inflater.inflate(layoutRes, container, false).also {
            viewsConfig = false
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        prepareView?.invoke(this, view, this) ?: prepareAdapterView?.invoke(
            this,
            view,
            this,
            handlerOnAdapterClickListener
        )
        if (!viewsConfig) {
            configUi()
            viewsConfig = true
        }

    }

    override fun onClick(p0: View) {
        onClickListener?.invoke(p0, this)
        if (config?.dismissOnClick) dismiss()
    }

    fun onPrepareView(config: AppDialogConfig = this.config, pre: OnPrepareDialogView): AppDialog {
        prepareView = pre
        this.config = config
        return this
    }

    fun onPrepareView(
        config: AppDialogConfig = this.config,
        pre: OnPrepareDialogAdapterView
    ): AppDialog {
        prepareAdapterView = pre
        this.config = config
        return this
    }

    open fun onCreate() {
    }

    open fun configUi() {
    }

    open fun configObserves() {
    }


    fun onClickListener(onClick: OnDialogViewClick): AppDialog {
        onClickListener = onClick
        return this
    }

    fun onAdapterItemClickListener(onClick: OnDialogAdapterItemClick): AppDialog {
        onAdapterClickListener = onClick
        return this
    }


    //------------Cached functions-----------------------
    /**
     * @param id : view id to lock for.
     * @param forceFind :true to always go search in the view,false to search
     * in cache first then go throw the view.
     * */
    fun <T : View> get(id: Int, forceFind: Boolean = false): T {
        return customFindCachedViewById(id, forceFind) as T
    }

    private var customFindViewCache: HashMap<Int, View>? = null

    private fun customFindCachedViewById(var1: Int, forceFind: Boolean): View? {

        if (this.customFindViewCache == null) {
            this.customFindViewCache = HashMap()
        }

        var var2: View? = null
        if (!forceFind)
            var2 = customFindViewCache?.get(var1)
        if (var2 == null) {
            val var10000 = view

            var2 = var10000?.findViewById(var1)
            if (var2 != null)
                customFindViewCache?.put(var1, var2)
        }

        return var2
    }

    fun clearFindViewByIdCache() {
        customFindViewCache?.clear()
    }


}