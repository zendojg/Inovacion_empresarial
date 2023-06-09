package gob.pa.inovacion_empresarial.function

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import gob.pa.inovacion_empresarial.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.Date as fecha


object Functions {
    fun msgMark(msg: String, width: Int, ctx: Context): Balloon {
        return Balloon.Builder(ctx)
            //.setLayout(R.layout.style_balloon)
            .setArrowSize(10)
            .setArrowOrientation(ArrowOrientation.BOTTOM)
            .setArrowPosition(0.5f)
            .setWidth(width)
            .setHeight(50)
            .setCornerRadius(4f)
            .setText(msg)
            .setBackgroundColor(ContextCompat.getColor(ctx, R.color.dark_red))
            .setBalloonAnimation(BalloonAnimation.ELASTIC)
            .build()
    }

    fun msgBallom(msg: String, width: Int, ctx: Context): Balloon {
        return Balloon.Builder(ctx)
            //.setLayout(R.layout.style_balloon)
            .setArrowSize(0)
            .setArrowOrientation(ArrowOrientation.BOTTOM)
            .setWidth(width)
            .setHeight(28)
            .setCornerRadius(4f)
            .setText(msg)
            .setBackgroundColor(ContextCompat.getColor(ctx, R.color.dark_red))
            .setBalloonAnimation(BalloonAnimation.ELASTIC)
            .build()
    }

    fun Fragment.hideKeyboard() { view?.let { activity?.hideKeyboard(it) } }
    fun Activity.hideKeyboard() { hideKeyboard(currentFocus ?: View(this)) }
    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun myDate(): fecha = Calendar.getInstance().time
    fun fecha.aString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun yearArray(): IntArray {
        val initYear = 1900
        val aYears: Int = ((myDate().aString("yyyy")).toInt())
        val years = IntArray(aYears + 1 - initYear)
        for (i in years.indices) {
            if (i == 0) years[i] = initYear
            else years[i] = years[i - 1] + 1
        }
        return years
    }


    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    true
                }
                else -> false
            }
        } else false
    }

    fun replaceSign(valor: String): String {
        return try {
            if (valor.isBlank()) ""
            else {
                var cadena = valor.replace(",", "")
                cadena = cadena.replace(" ", "")
                cadena = cadena.replace("%", "")
                cadena = cadena.replace("$", "")
                cadena
            }
        } catch (e: java.lang.Exception) {
            ""
        }
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}
