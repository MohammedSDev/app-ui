# app-ui
![Release](https://jitpack.io/v/mobile-android-libraries/app-ui.svg)




App-ui is simple & common weigth e.g{TextView,EditText} prepared for you.


# add dependence
in project level build.gradle

```gradle
allprojects {
  repositories {
                  google()
                  jcenter()
                    //...
                  maven { url 'https://jitpack.io' }
  }
}
```
in app level build.gradle
```gradle
dependencies {
        implementation 'com.github.mobile-android-libraries:app-ui:<last-build>'
}
```


# an Examples


# EditText Validations
```kotlin
//set your custom validation
phoneNumberET.setValidation {
    if(text.isEmpty())
        return@setValidation false to "kindly enter phone number."
    else if(!text.toString().startsWith("05"))
        return@setValidation false to "phone number must start with 05."
    else if(text.toString().length != 10)
        return@setValidation false to "phone number must be 10 numbers."
    else return@setValidation true to null
}
//set your callback
phoneNumberET.setOnValidationCB { isValid, tag ->

    if(!isValid && tag is String)
        Toast.makeText(this,tag,Toast.LENGTH_LONG).show()
}


...

submitBtn.setOnClickListener { 
    if(phoneNumberET.isValid()){
    //update DB phone number value
    } 
}

```


# dialog example

```kotlin
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

```


# RecyclerView inside a dialog

```kotlin
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
```


you may also want to check App-adapter library https://github.com/mobile-android-libraries/app-adapter



# Enjoy using app-ui library,report any bugs you found,request any update ,or even drop me an email gg.goo.mobile@gmail.com
