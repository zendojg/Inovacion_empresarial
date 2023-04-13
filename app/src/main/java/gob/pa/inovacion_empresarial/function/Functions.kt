package gob.pa.inovacion_empresarial.function

import android.Manifest
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.Date as fecha

class Functions {
    companion object {

        fun changeStatusBarContrastStyle(window: Window, lightIcons: Boolean) {
            val decorView: View = window.decorView
            if (lightIcons) {
                // Draw light icons on a dark background color
                decorView.systemUiVisibility =
                    decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            } else {
                // Draw dark icons on a light background color
                decorView.systemUiVisibility =
                    decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }

        //------------------------------------------------------------------------------------------
        fun permissions(act: Activity) {
            ActivityCompat.requestPermissions(act,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),0)
            ActivityCompat.requestPermissions(act,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),0)
        }
        //------------------------------------------------------------------------------------------
        fun Fragment.hideKeyboard() {
            view?.let { activity?.hideKeyboard(it) }
        }
        fun Activity.hideKeyboard() {
            hideKeyboard(currentFocus ?: View(this))
        }
        private fun Context.hideKeyboard(view: View) {
            val inputMethodManager = getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
        //------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------
        fun myDate(): fecha {
            return Calendar.getInstance().time }
        fun fecha.aString(format: String, locale: Locale = Locale.getDefault()): String {
            val formatter = SimpleDateFormat(format, locale)
            return formatter.format(this)
        }
        fun yearArray(): IntArray {
            val aYears: Int  = ((myDate().aString("yyyy")).toInt())
            val initYear = 1850
            val years = IntArray(aYears +1 - initYear)
            for (i in years.indices) {
                if (i==0)  years[i] = initYear
                else       years[i] = years[i-1] + 1
            }
            return years
        }
        //------------------------------------------------------------------------------------------


        fun hayRed(context: Context):Boolean{
            val connectivityManager = context.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            connectivityManager.activeNetwork
            return  networkInfo != null && networkInfo.isConnected && networkInfo.isAvailable
        }

        fun ceroLeft(chain: String, cant: Int):String {
            var chainSize = chain.length
            var chainR = chain
            while (cant >= chainSize) {
                chainR = "0$chainR"
                chainSize = chainR.length
            }
            return chainR
        }

        fun replaceSign(valor:String):String{
            return try {
                if (valor.isBlank()) ""
                else{
                    var cadena = valor.replace(",","")
                    cadena = cadena.replace(" ","")
                    cadena = cadena.replace("%","")
                    cadena = cadena.replace("$","")
                    cadena
                } }
            catch (e:java.lang.Exception){
                ""
            }
        }

        fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
    }

    class DecimalDigitsInputFilter(digitsBeforeZero: Int, digitsAfterZero: Int): InputFilter {
        private var mPattern: Pattern = Pattern.compile(
            "[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," +
                    (digitsAfterZero - 1) + "})?)||(\\.)?")
        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dstart: Int,
            dend: Int): CharSequence? {
            val matcher: Matcher = mPattern.matcher(dest)
            return if (!matcher.matches()) "" else null
        }
    }

}