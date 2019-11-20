package com.digital.appuidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digital.appadapter.AppAdapter
import com.digital.appui.AppDialogConfig
import com.digital.appui.dialog.AppDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mainTV.setOnClickListener {
            //            AppDialog(R.layout.dialog_layout, AppDialogConfig())
//                .onPrepareView(AppDialogConfig()) { view, clickListener -> }
            AppDialog(R.layout.dialog_layout)
                .onPrepareView { view, clickListener ->
                    get<TextView>(R.id.tvOne)
                    get<TextView>(R.id.tvOne).text = "tvOne"
                    get<TextView>(R.id.tvOne, true).text = "tvOneAfter"
                    get<TextView>(R.id.tvOne).setOnClickListener(clickListener)
                    get<TextView>(R.id.tvTwo).setOnClickListener(clickListener)

                }.onClickListener { view, dialog ->

                    when (view.id) {
                        R.id.tvOne -> {
                        }
                        R.id.tvTwo -> {
                        }
                        R.id.tvThree -> {
                        }
                    }
                }
                .show(supportFragmentManager, "AppDialog")
        }
        mainTV.setOnClickListener {
            //            AppDialog(R.layout.dialog_layout, AppDialogConfig())
//                .onPrepareView(AppDialogConfig()) { view, clickListener -> }

            AppDialog(R.layout.dialog_list_layout,AppDialogConfig())
                .onPrepareView { view, clickListener,clickAdapterListener ->

                    get<RecyclerView>(R.id.dialogRecycler).adapter = AppAdapter<String>(R.layout.dialog_list_item) {
                        get<TextView>(R.id.tvItem1).text = "pos: $it"
                        get<TextView>(R.id.tvItem1).setOnClickListener {labelView->
                            clickAdapterListener(labelView,adapterPosition)
                        }
                    }.also { it.list = listOf("one","two","three","four","five","six","seven") }
                    get<TextView>(R.id.tvOne)
                    get<TextView>(R.id.tvOne).text = "tvOne"
                    get<TextView>(R.id.tvOne, true).text = "tvOneAfter"
                    get<TextView>(R.id.tvOne).setOnClickListener(clickListener)
                    get<TextView>(R.id.tvTwo).setOnClickListener(clickListener)

                }.onClickListener { view, dialog ->

                    println("clickedView")
                    when (view.id) {
                        R.id.tvOne -> {
                        }
                        R.id.tvTwo -> {
                        }
                        R.id.tvThree -> {
                        }
                    }
                }.onAdapterItemClickListener { view, position ->
                    println("clicked: $position")
                }
                .show(supportFragmentManager, "AppDialog")
        }

    }
}
