package gob.pa.inovacion_empresarial.function

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.function.Functions.aString
import gob.pa.inovacion_empresarial.model.Mob
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

object CreateBackUp {
    //-- Genera un archivo con todos los formularios, llamado EIE+fecha de texto plano
    suspend fun saved(ctx: Context): Boolean = withContext(Dispatchers.IO) {
        try {
            val sharedPreferences = ctx.getSharedPreferences(
                ctx.getString(R.string.sharedPreferencesKey),
                Context.MODE_PRIVATE
            )
            val sharedIds = sharedPreferences.all.map { it.key }
            val lista: ArrayList<String> = ArrayList()
            for (i in sharedIds) {
                val data: String? = try {
                    AppCache.getAny(ctx, i).toString()
                } catch (e: ClassCastException) {
                    ""
                }
                if (i.contains("UserAPP") ||
                    i.contains("RememberCHECK") ||
                    i.contains("tokenData")
                ) {
                    lista.add("")
                } else lista.add("$i*${data ?: ""}")
            }

            val name = "EIE_${Functions.myDate().aString(Mob.DATEFORMATFORDOC)}.txt"
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, name)
                put(MediaStore.MediaColumns.MIME_TYPE, "text/plain")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS)
            }
            val contentResolver = ctx.contentResolver
            val uri = contentResolver.insert(MediaStore.Files.getContentUri("external"), contentValues)
            uri?.let { documentUri ->
                contentResolver.openOutputStream(documentUri)?.use { outputStream ->
                    lista.forEach { item ->
                        outputStream.write(item.toByteArray())
                        outputStream.write("\n".toByteArray())
                    }
                    return@withContext true // Escritura satisfactoria
                }
            }
        } catch (e: IOException) { e.printStackTrace() }
        return@withContext false // Escritura fallida
    }
}