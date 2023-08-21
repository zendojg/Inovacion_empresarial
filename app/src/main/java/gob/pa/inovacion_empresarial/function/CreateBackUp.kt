package gob.pa.inovacion_empresarial.function

import android.content.Context
import android.os.Environment
import gob.pa.inovacion_empresarial.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.lang.ClassCastException

class CreateBackUp {

    suspend fun saved(ctx: Context) = withContext(Dispatchers.IO)  {
        val sharedPreferences = ctx.getSharedPreferences(
            ctx.getString(R.string.sharedPreferencesKey), Context.MODE_PRIVATE)
        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        val sharedIds = sharedPreferences.all.map { it.key }
//        val sharedValues = sharedPreferences.all.map { it.value }
//        if (keys) {
//            val fileDir = File(dir, "EIE_registro.txt")
//            FileOutputStream(fileDir).use {
//                it.write(sharedIds.joinToString("\n").toByteArray())
//            }
//            val fileValues = File(dir, "EIE_data.txt")
//            FileOutputStream(fileValues).use {
//                it.write(sharedValues.joinToString("\n").toByteArray())
//            }
//        }
        val lista: ArrayList<String> = ArrayList()
        for (i in sharedIds) {
            val data: String? = try { AppCache.getAny(ctx,i).toString() }
            catch (e: ClassCastException) { "" }
            if (i.contains("UserAPP") ||
                i.contains("RememberCHECK") ||
                i.contains("tokenData")) {
                lista.add("")
            } else
                lista.add("$i*${data ?: ""}")
        }
        val fileBackUp = File(dir, "EIE_backup.txt")
        FileOutputStream(fileBackUp).use {
            it.write(lista.joinToString("\n").toByteArray())
        }
    }
}