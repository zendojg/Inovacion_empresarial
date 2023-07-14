package gob.pa.inovacion_empresarial.service.room

import android.content.Context
import gob.pa.inovacion_empresarial.model.DVModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomView(dvmModel: DVModel, context: Context) {
    var ctx: Context
    var dvm: DVModel
    init {
        this.ctx = context
        this.dvm = dvmModel
    }
    private val roomDB = FormDB.buildDB(ctx)

     suspend fun viewRoom() {
        val proRoom = roomDB.dbFormDao().getProvArray()
        val distRoom = roomDB.dbFormDao().getDistVer()
        val correRoom = roomDB.dbFormDao().getCorrVer()
        val lugarRoom = roomDB.dbFormDao().getDistVer()

         if (proRoom.isEmpty()) { dvmProv()
             println("---------- DOWNLOAD PROVINCIAS")}
         if (distRoom.isEmpty()) { dvmDist()
             println("---------- DOWNLOAD DISTRITOS")}
         if (correRoom.isEmpty()) { dvmCorre()
             println("---------- DOWNLOAD CORREGIMIENTOS")}
         if (lugarRoom.isEmpty()) { dvmLugarP()
             println("---------- DOWNLOAD LUGAR POBLADO")}
    }

    private fun dvmProv() {
        CoroutineScope(Dispatchers.IO).launch { roomDB.dbFormDao().insertProv(dvm.getProv()) }
    }
    private fun dvmDist() {
        CoroutineScope(Dispatchers.IO).launch { roomDB.dbFormDao().insertDist(dvm.getDist()) }
    }
    private fun dvmCorre() {
        CoroutineScope(Dispatchers.IO).launch { roomDB.dbFormDao().insertCorre(dvm.getCorre()) }
    }
    private fun dvmLugarP() {
        CoroutineScope(Dispatchers.IO).launch { roomDB.dbFormDao().insertLugarP(dvm.getLugarP()) }
    }
}