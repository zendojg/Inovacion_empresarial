package gob.pa.inovacion_empresarial.function

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.model.ModelAuth
import gob.pa.inovacion_empresarial.model.ModelForm
import java.lang.reflect.Type


object AppCache {
    private var userAPP = "UserAPP"
    private var remeAPP = "RememberCHECK"
    private const val LOGINDATA = "tokenData"

    //-----// User del Encuestador
    fun userSAVE(ctx: Context, user: String) {
        val vPrefe: SharedPreferences = ctx.getSharedPreferences(
            ctx.getString(
                R.string.sharedPreferencesKey
            ), Context.MODE_PRIVATE)
        val vEditor: SharedPreferences.Editor = vPrefe.edit()
        vEditor.putString(userAPP, user)
        vEditor.apply()
    }
    fun userGET(ctx: Context): String = ctx.getSharedPreferences(
        ctx.getString(R.string.sharedPreferencesKey), Context.MODE_PRIVATE).getString(userAPP, "").toString()

    //-----// check de recordarme
    fun remSAVE(ctx: Context, remember: Boolean) {
        val vPrefe: SharedPreferences = ctx.getSharedPreferences(
            ctx.getString(R.string.sharedPreferencesKey), Context.MODE_PRIVATE)
        val vEditor: SharedPreferences.Editor = vPrefe.edit()
        vEditor.putBoolean(remeAPP, remember)
        vEditor.apply()
    }
    fun remGET(ctx: Context): Boolean = ctx.getSharedPreferences(
        ctx.getString(R.string.sharedPreferencesKey),
        Context.MODE_PRIVATE).getBoolean(remeAPP, false)

    //-----// Data Login
    fun loginSAVE(ctx: Context, login: ModelAuth?) {
        val vPrefe: SharedPreferences = ctx.getSharedPreferences(
            ctx.getString(R.string.sharedPreferencesKey), Context.MODE_PRIVATE)
        //val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        val editor: SharedPreferences.Editor = vPrefe.edit()
        val gson = Gson()
        val json = gson.toJson(login)
        editor.putString(LOGINDATA, json)
        editor.apply()
    }
    fun loginGET(ctx: Context): ModelAuth? {
        val vPrefe: SharedPreferences = ctx.getSharedPreferences(
            ctx.getString(R.string.sharedPreferencesKey), Context.MODE_PRIVATE)
        //val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        val gson = Gson()
        val json = vPrefe.getString(LOGINDATA, "")
        val type: Type = object : TypeToken<ModelAuth?>() {}.type
        return gson.fromJson(json, type)
    }
    fun loginCLEAR(ctx: Context) {
        val vPrefe: SharedPreferences = ctx.getSharedPreferences(
            ctx.getString(R.string.sharedPreferencesKey), Context.MODE_PRIVATE)
        vPrefe.edit().remove(LOGINDATA).apply()
    }

    //------------------------------
    //-----// Formulario Completo //
    fun formSAVE(ctx: Context, form: ModelForm, name: String) {
        val vPrefe: SharedPreferences = ctx.getSharedPreferences(
            ctx.getString(R.string.sharedPreferencesKey), Context.MODE_PRIVATE
        )
        //val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        val editor: SharedPreferences.Editor = vPrefe.edit()
        val gson = Gson()
        val json = gson.toJson(form)
        editor.putString(name, json)
        editor.apply()
    }

    //-----// All data
    fun getAny(ctx: Context, name: String): Any? {
        return ctx.getSharedPreferences(
            ctx.getString(R.string.sharedPreferencesKey), Context.MODE_PRIVATE).getString(name, "")
    }

}



