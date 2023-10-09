package gob.pa.inovacion_empresarial.function

import android.content.Context
import gob.pa.inovacion_empresarial.model.Mob
import org.json.JSONArray
import java.io.IOException
import java.nio.charset.Charset

object CreateIncon {
    //--- Devuelve una inconsistencia de "inconsistencias.json"
    fun inconsistencia(ctx: Context, searchPAUTA: String): String? {
        var resultText: String? = null
        try {
            val jsonAsset = ctx.assets.open("inconsistencias.json")
            val size = jsonAsset.available()
            val buffer = ByteArray(size)
            jsonAsset.read(buffer)
            jsonAsset.close()
            val jsonContent = String(buffer, Charset.defaultCharset())
            val jsonArray = JSONArray(jsonContent)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                if (jsonObject.optString("PAUTA") == searchPAUTA) {
                    resultText = jsonObject.optString("TEXTO")
                    break
                }
            }
        } catch (e: IOException) { e.printStackTrace() }
        return resultText
    }
    fun reviewIncons(): List<Int> { //----- Devuelve los capitulos con inconsistencias
        //val inconsistencias = Mob.infoCap.all { !it.incons } //--- true = algún cap con incon
        val indice = Mob.infoCap
            .filter { it.incons }
            .map { it.indexCap }
        val valoresCorrespondientes = indice.mapNotNull { Mob.titleMapTxt[it] }
        println(valoresCorrespondientes)
        return Mob.infoCap
                .filter { it.incons }
                .map { it.indexCap }
    }
    fun reviewCaps(): List<Int> {  //----- Devuelve los capitulos que no sean han abierto
        //val viewCaps = Mob.infoCap.all { it.capView }        //--- true = todos los cap cargados
        return Mob.infoCap
                .filter { !it.capView }
                .map { it.indexCap }
    }
}