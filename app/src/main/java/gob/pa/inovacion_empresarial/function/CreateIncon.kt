package gob.pa.inovacion_empresarial.function

import android.content.Context
import org.json.JSONArray
import java.io.IOException
import java.nio.charset.Charset

object CreateIncon {

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
}