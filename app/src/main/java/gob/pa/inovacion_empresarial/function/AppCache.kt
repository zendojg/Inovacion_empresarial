package gob.pa.inovacion_empresarial.function

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.model.ModelAuth
import java.lang.reflect.Type


class AppCache {
    companion object {
        private var nameUSER = ""
        private var userAPP = "E001-0001"
        private var passAPP = "PassAPP"
        private var ruc = "0000-0000-0000"
        private var remeAPP = false
        private var statForm = true
        private var localID = "-1"
        private var localNAME = "LOCAL DESCONOCIDO"
        private var formSAVE = "formulario"
        private const val LOGINDATA = "tokenData"


        //-----------------------------------------------------------------------------------------// Nombre del Encuestador
        fun nameSAVE(ctx: Context, Valor: String) {
            val vPrefe: SharedPreferences = ctx.getSharedPreferences(ctx.getString(R.string.sharedPreferencesKey), Context.MODE_PRIVATE)
            val vEditor: SharedPreferences.Editor = vPrefe.edit()
            vEditor.putString(nameUSER, Valor)
            vEditor.apply()
        }

        fun nameGET(ctx: Context): String {
            return ctx.getSharedPreferences(
                ctx.getString(R.string.sharedPreferencesKey),
                Context.MODE_PRIVATE
            ).getString(nameUSER, "").toString()
        }
        //-----------------------------------------------------------------------------------------// User del Encuestador
        fun userSAVE(ctx: Context, Valor: String) {
            val vPrefe: SharedPreferences = ctx.getSharedPreferences(
                ctx.getString(R.string.sharedPreferencesKey),
                Context.MODE_PRIVATE
            )
            val vEditor: SharedPreferences.Editor = vPrefe.edit()
            vEditor.putString(userAPP, Valor)
            vEditor.apply()
        }

        fun userGET(ctx: Context): String {
            return ctx.getSharedPreferences(
                ctx.getString(R.string.sharedPreferencesKey),
                Context.MODE_PRIVATE
            ).getString(userAPP, "").toString()
        }
        //-----------------------------------------------------------------------------------------// Password
        fun passSAVE(ctx: Context, Valor: String) {
            val vPrefe: SharedPreferences = ctx.getSharedPreferences(
                ctx.getString(R.string.sharedPreferencesKey),
                Context.MODE_PRIVATE
            )
            val vEditor: SharedPreferences.Editor = vPrefe.edit()
            vEditor.putString(passAPP, Valor)
            vEditor.apply()
        }

        fun passGET(ctx: Context): String {
            return ctx.getSharedPreferences(
                ctx.getString(R.string.sharedPreferencesKey),
                Context.MODE_PRIVATE
            ).getString(passAPP, "").toString()
        }
        //-----------------------------------------------------------------------------------------// check de recordarme
        fun remSAVE(ctx: Context, Valor: Boolean) {
            val vPrefe: SharedPreferences = ctx.getSharedPreferences(ctx.getString(R.string.sharedPreferencesKey), Context.MODE_PRIVATE)
            val vEditor: SharedPreferences.Editor = vPrefe.edit()
            vEditor.putBoolean(remeAPP.toString(), Valor)
            vEditor.apply()
        }

        fun remGET(ctx: Context): Boolean {
            return ctx.getSharedPreferences(ctx.getString(R.string.sharedPreferencesKey), Context.MODE_PRIVATE).getBoolean(
                remeAPP.toString(), false)
        }
        //-----------------------------------------------------------------------------------------// RUC de busqueda
        fun rucSAVE(ctx: Context, Valor: String) {
            val vPrefe: SharedPreferences = ctx.getSharedPreferences(
                ctx.getString(R.string.sharedPreferencesKey),
                Context.MODE_PRIVATE
            )
            val vEditor: SharedPreferences.Editor = vPrefe.edit()
            vEditor.putString(ruc, Valor)
            vEditor.apply()
        }

        fun rucGET(ctx: Context): String {
            return ctx.getSharedPreferences(
                ctx.getString(R.string.sharedPreferencesKey),
                Context.MODE_PRIVATE
            ).getString(ruc, "").toString()
        }
        //-----------------------------------------------------------------------------------------// Data Login
        fun loginSAVE(ctx: Context, Valor: ModelAuth?) {
            val vPrefe: SharedPreferences = ctx.getSharedPreferences(
                ctx.getString(R.string.sharedPreferencesKey), Context.MODE_PRIVATE)
            //val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx)
            val editor: SharedPreferences.Editor = vPrefe.edit()
            val gson = Gson()
            val json = gson.toJson(Valor)
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
            //val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx)
            vPrefe.edit().remove(LOGINDATA).apply()
        }





    }



}