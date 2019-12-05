package com.digital.appuidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.digital.appadapter.AppAdapter
import com.digital.appui.dialog.AppDialogConfig
import com.digital.appui.dialog.AppDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //test
        mainTV.text = """hello, my name is Mohammed Bahashwan From Yemen Work in Mukalla - Clickapps.this only to test multi lines in textview to get specific char in specific line get it's index .. stuff like this happen here.:)
            
        """.trimIndent()
        findViewById<View>(android.R.id.content).post {
            println("mainTV.layout.lineCount: ${mainTV.layout.lineCount}")
            println("mainTV.layout.getLineVisibleEnd(0): ${mainTV.layout.getLineVisibleEnd(0)}")
            println("mainTV.layout.getLineVisibleEnd(1): ${mainTV.layout.getLineVisibleEnd(1)}")
            println("mainTV.layout.getLineVisibleEnd(2): ${mainTV.layout.getLineVisibleEnd(2)}")
            println("mainTV.layout.getLineVisibleEnd(3): ${mainTV.layout.getLineVisibleEnd(3)}")
            println("mainTV.layout.getLineEnd(0): ${mainTV.layout.getLineEnd(0)}")
            println("mainTV.layout.getLineEnd(1): ${mainTV.layout.getLineEnd(1)}")
            println("mainTV.layout.getLineEnd(2): ${mainTV.layout.getLineEnd(2)}")
            println("mainTV.layout.getLineEnd(3): ${mainTV.layout.getLineEnd(3)}")
            println(
                "mainTV.text.char mainTV.layout.getLineVisibleEnd(0): ${mainTV.text.get(
                    mainTV.layout.getLineVisibleEnd(
                        0
                    )
                )}"
            )
            println(
                "mainTV.text.char mainTV.layout.getLineVisibleEnd(1): ${mainTV.text.get(
                    mainTV.layout.getLineVisibleEnd(
                        1
                    )
                )}"
            )
            println(
                "mainTV.text.char mainTV.layout.getLineVisibleEnd(2): ${mainTV.text.get(
                    mainTV.layout.getLineVisibleEnd(
                        2
                    )
                )}"
            )
            println(
                "mainTV.text.char mainTV.layout.getLineVisibleEnd(3): ${mainTV.text.get(
                    mainTV.layout.getLineVisibleEnd(
                        3
                    )
                )}"
            )
            println(
                "mainTV.text.char mainTV.layout.getLineEnd(0): ${mainTV.text.get(
                    mainTV.layout.getLineEnd(
                        0
                    )
                )}"
            )
            println(
                "mainTV.text.char mainTV.layout.getLineEnd(1): ${mainTV.text.get(
                    mainTV.layout.getLineEnd(
                        1
                    )
                )}"
            )
            println(
                "mainTV.text.char mainTV.layout.getLineEnd(2): ${mainTV.text.get(
                    mainTV.layout.getLineEnd(
                        2
                    )
                )}"
            )
            println(
                "mainTV.text.char mainTV.layout.getLineEnd(3): ${mainTV.text.get(
                    mainTV.layout.getLineEnd(
                        3
                    )
                )}"
            )
        }
        //test appDialog
//        TryAppDialog()

        et1.setValidation {
            if(text.isEmpty())
                return@setValidation false to "kindly enter phone number."
            else if(!text.toString().startsWith("05"))
                return@setValidation false to "phone number must start with 05."
            else if(text.toString().length != 10)
                return@setValidation false to "phone number must be 10 numbers."
            else return@setValidation true to null
        }

        et1.setOnValidationCB { isValid, tag ->

            if(!isValid && tag is String)
                Toast.makeText(this,tag,Toast.LENGTH_LONG).show()
        }

        TryAppDialog()

    }

    private fun TryAppDialog() {
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

                }
                .onClickListener { view, dialog ->

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

            AppDialog(R.layout.dialog_list_layout, AppDialogConfig())
                .onPrepareView { view, clickListener, clickAdapterListener ->

                    get<RecyclerView>(R.id.dialogRecycler).adapter =
                        AppAdapter<String>(R.layout.dialog_list_item) {
                            get<TextView>(R.id.tvItem1).text = "pos: $it"
                            get<TextView>(R.id.tvItem1).setOnClickListener { labelView ->
                                clickAdapterListener(labelView, adapterPosition)
                            }
                        }.also {
                            it.list = listOf("one", "two", "three", "four", "five", "six", "seven")
                        }
                    get<TextView>(R.id.tvOne)
                    get<TextView>(R.id.tvOne).text = "tvOne"
                    get<TextView>(R.id.tvOne, true).text = "tvOneAfter"
                    get<TextView>(R.id.tvOne).setOnClickListener(clickListener)
                    get<TextView>(R.id.tvTwo).setOnClickListener(clickListener)

                }
                .onClickListener { view, dialog ->

                    println("clickedView")
                    when (view.id) {
                        R.id.tvOne -> {
                        }
                        R.id.tvTwo -> {
                        }
                        R.id.tvThree -> {
                        }
                    }
                }
                .onAdapterItemClickListener { view, position ->
                    println("clicked: $position")
                }
                .show(supportFragmentManager, "AppDialog")
        }
        mainTV.setOnClickListener { et1.setText("") }
    }



}
