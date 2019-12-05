# app-ktx
![Release](https://jitpack.io/v/clickapps-android/app-ui.svg)




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
        implementation 'com.github.clickapps-android:app-ui:<last-build>'
}
```


# an Examples


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


# Enjoy using app-ktx library,report any bugs you found,request any update ,or even drop me an email gg.goo.mobile@gmail.com
