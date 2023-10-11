package gob.pa.inovacion_empresarial.service.room

import android.content.Context
import gob.pa.inovacion_empresarial.model.DVModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomView(dvmModel: DVModel, context: Context) {
    private var dvm: DVModel
    private var ctx: Context
    init {
        this.ctx = context
        this.dvm = dvmModel
    }

    private val roomDB = FormDB.buildDB(ctx)
    private val roomInstance = FormDB.getInstance(ctx)

    suspend fun deleteDataRoom() {
        roomDB.dbFormDao().deleteProv()
        roomDB.dbFormDao().deleteDist()
        roomDB.dbFormDao().deleteCorre()
        roomDB.dbFormDao().deleteLugarP()
    }
    suspend fun viewRoom(update: Boolean) {
        val proRoom = roomDB.dbFormDao().getProvArray()
        val distRoom = roomDB.dbFormDao().getDistVer()
        val correRoom = roomDB.dbFormDao().getCorrVer()
        val lugarRoom = roomDB.dbFormDao().getLugarPVer()

        if (proRoom.isEmpty() || update) roomDB.dbFormDao().insertProv(dvm.getProv())
        if (distRoom.isEmpty() || update) roomDB.dbFormDao().insertDist(dvm.getDist())
        if (correRoom.isEmpty() || update) roomDB.dbFormDao().insertCorre(dvm.getCorre())
        if (lugarRoom.isEmpty() || update) roomDB.dbFormDao().insertLugarP(dvm.getLugarP())
    }

    suspend fun instance() { roomInstance.dbFormDao() }
    suspend fun deleteForm(id: String, ncont: String) {
        roomDB.dbFormDao().deleteForm(ncont, id)
    }
    suspend fun getForm(id: String, ncont: String) = roomDB.dbFormDao().getFormsbyID(ncont, id)
    suspend fun getAllForm() = roomDB.dbFormDao().getAllForms()
    suspend fun getFormsUser(idUser: String) = roomDB.dbFormDao().getFormsUser(idUser)
    suspend fun saveForm(form: DBform) = roomDB.dbFormDao().insertForm(form)
    suspend fun getProv() = roomDB.dbFormDao().getProvArray()
    suspend fun getProvID(prov: String) = roomDB.dbFormDao().getProvID(prov)
    suspend fun getProvName(idprov: String) =roomDB.dbFormDao().getProvName(idprov)
    suspend fun getDist(idprov: String) = roomDB.dbFormDao().getDistArrayByID(idprov)
    suspend fun getDistID(idprov: String, dist: String) = roomDB.dbFormDao().getDistID(idprov, dist)
    suspend fun getDistName(idprov: String, iddist: String) =
        roomDB.dbFormDao().getDistName(idprov, iddist)
    suspend fun getCorre(idprov: String, iddist: String) =
        roomDB.dbFormDao().getCorreArrayByID(idprov, iddist)
    suspend fun getCorreID(idprov: String, iddist: String, corre: String) =
        roomDB.dbFormDao().getCorreID(idprov, iddist, corre)
    suspend fun getCorreName(idprov: String, iddist: String, idcorre: String) =
        roomDB.dbFormDao().getCorreName(idprov, iddist, idcorre)
    suspend fun getLugarP(idprov: String, iddist: String, idcorre: String) =
        roomDB.dbFormDao().getLugarPArrayByID(idprov, iddist, idcorre)
    suspend fun getLPID(idprov: String, iddist: String, idcorre: String, lugar: String) =
        roomDB.dbFormDao().getLugarPID(idprov, iddist, idcorre, lugar)
    suspend fun getLPName(idprov: String, iddist: String, idcorre: String, idlugar: String) =
        roomDB.dbFormDao().getLugarPName(idprov, iddist, idcorre, idlugar)

}