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

         if (proRoom.isEmpty()) {
             CoroutineScope(Dispatchers.IO).launch { roomDB.dbFormDao().insertProv(dvm.getProv()) }
             println("---------- DOWNLOAD PROVINCIAS")}
         if (distRoom.isEmpty()) {
             CoroutineScope(Dispatchers.IO).launch { roomDB.dbFormDao().insertDist(dvm.getDist()) }
             println("---------- DOWNLOAD DISTRITOS")}
         if (correRoom.isEmpty()) {
             CoroutineScope(Dispatchers.IO).launch { roomDB.dbFormDao().insertCorre(dvm.getCorre()) }
             println("---------- DOWNLOAD CORREGIMIENTOS")}
         if (lugarRoom.isEmpty()) {
             CoroutineScope(Dispatchers.IO).launch { roomDB.dbFormDao().insertLugarP(dvm.getLugarP()) }
             println("---------- DOWNLOAD LUGAR POBLADO")}
    }


    suspend fun getProv(): Array<String> {
        return roomDB.dbFormDao().getProvArray()
    }
    suspend fun getProvID(prov: String): String {
        return roomDB.dbFormDao().getProvID(prov)
    }
    suspend fun getProvName(idprov: String): String {
        return roomDB.dbFormDao().getProvName(idprov)
    }

    suspend fun getDist(idprov: String): Array<String> {
        return roomDB.dbFormDao().getDistArrayByID(idprov)
    }
    suspend fun getDistID(idprov: String, dist: String): String {
        return roomDB.dbFormDao().getDistID(idprov, dist)
    }
    suspend fun getDistName(idprov: String, iddist: String): String {
        return roomDB.dbFormDao().getDistName(idprov, iddist)
    }

    suspend fun getCorre(idprov: String, iddist: String): Array<String> {
        return roomDB.dbFormDao().getCorreArrayByID(idprov, iddist)
    }
    suspend fun getCorreID(idprov: String, iddist: String, corre: String): String {
        return roomDB.dbFormDao().getCorreID(idprov, iddist, corre)
    }
    suspend fun getCorreName(idprov: String, iddist: String, idcorre: String): String {
        return roomDB.dbFormDao().getCorreName(idprov, iddist, idcorre)
    }

    suspend fun getLugarP(idprov: String, iddist: String, idcorre: String): Array<String> {
        return roomDB.dbFormDao().getLugarPArrayByID(idprov,iddist,idcorre)
    }
    suspend fun getLPID(idprov: String, iddist: String, idcorre: String, lugar:String):
            String {
        return roomDB.dbFormDao().getLugarPID(idprov, iddist, idcorre, lugar)
    }
    suspend fun getLPName(idprov: String, iddist: String, idcorre: String, idlugar:String):
            String {
        return roomDB.dbFormDao().getLugarPName(idprov, iddist, idcorre, idlugar)
    }
}