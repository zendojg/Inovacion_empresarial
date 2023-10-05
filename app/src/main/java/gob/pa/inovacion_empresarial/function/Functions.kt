package gob.pa.inovacion_empresarial.function

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.model.Mob
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.Date as fecha


object Functions {

//    private const val ARROWSIZE = 10
//    private const val ARROWPOS = 0.5f
    private const val CORNER = 4f
    private const val HEIGHTBALLON = 28
//    private const val HEIGHTMARK = 50

//    fun msgMark(msg: String, width: Int, ctx: Context, color: Int): Balloon {
//        return Balloon.Builder(ctx)
//            //.setLayout(R.layout.style_balloon)
//            .setArrowSize(ARROWSIZE)
//            .setArrowOrientation(ArrowOrientation.BOTTOM)
//            .setArrowPosition(ARROWPOS)
//            .setWidth(width)
//            .setHeight(HEIGHTMARK)
//            .setCornerRadius(CORNER)
//            .setText(msg)
//            .setBackgroundColor(color)
//            .setBalloonAnimation(BalloonAnimation.ELASTIC)
//            .build()
//    }

    fun msgBallom(msg: String, width: Int, ctx: Context, color: Int): Balloon {
        return Balloon.Builder(ctx)
            //.setLayout(R.layout.style_balloon)
            .setArrowSize(0)
            .setArrowOrientation(ArrowOrientation.BOTTOM)
            .setWidth(width)
            .setHeight(HEIGHTBALLON)
            .setCornerRadius(CORNER)
            .setText(msg)
            .setBackgroundColor(color)
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
        val initYear = Mob.INITIAL_YEAR
        val aYears: Int = ((myDate().aString("yyyy")).toInt())
        val years = IntArray(aYears + 1 - initYear)
        for (i in years.indices) {
            if (i == 0) years[i] = initYear
            else years[i] = years[i - 1] + 1
        }
        return years
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

    fun List<Boolean?>.allTrue() = all { it == true }
    fun List<Boolean?>.allFalse() = all { it == false }
    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}
