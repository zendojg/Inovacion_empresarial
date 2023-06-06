package gob.pa.inovacion_empresarial.service.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FormDao {

    //---------------------------------------------------------------------------------------------- Provincias
    @Query("SELECT ProvNombre FROM dbprovincia_table WHERE ProvinciaId = :idProv")
    suspend fun getProvName(
        idProv: String):String

    @Query("SELECT ProvNombre FROM dbprovincia_table")
    suspend fun getProvArray():Array<String>

    @Query("SELECT ProvinciaId FROM dbprovincia_table WHERE ProvNombre = :prov")
    suspend fun getProvID(
        prov: String): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProv(
        prov:List<DBprovincia>)

    //---------------------------------------------------------------------------------------------- Distritos
    @Query("SELECT DistNombre FROM dbdistritos_table WHERE ProvinciaId = :idProv AND DistritoId = :idDist")
    suspend fun getDistName(
        idProv: String,
        idDist: String):String

    @Query("SELECT DistNombre FROM dbdistritos_table WHERE ProvinciaId = :idProv")
    suspend fun getDistArrayByID(
        idProv: String):Array<String>

    @Query("SELECT DistritoId FROM dbdistritos_table WHERE DistNombre = :dist AND ProvinciaId = :idProv")
    suspend fun getDistID(
        dist: String,
        idProv: String): String


    @Query("SELECT DistNombre FROM dbdistritos_table LIMIT 20")//-----Verificacion
    suspend fun getDistVer():Array<String>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDist(
        dist:List<DBdistritos>)

    //---------------------------------------------------------------------------------------------- Corregimientos
    @Query("SELECT CorreNombre FROM dbcorregimiento_table WHERE  ProvinciaId = :idProv AND  DistritoId = :idDist AND CorregimientoId = :idCorre")
    suspend fun getCorreName(
        idProv: String,
        idDist: String,
        idCorre: String):String

    @Query("SELECT CorreNombre FROM dbcorregimiento_table WHERE ProvinciaId = :idProv AND DistritoId = :idDist")
    suspend fun getCorreArrayByID(
        idProv: String,
        idDist: String):Array<String>

    @Query("SELECT CorregimientoId FROM dbcorregimiento_table WHERE CorreNombre = :corre AND ProvinciaId = :idProv AND  DistritoId = :idDist")
    suspend fun getCorreID(
        corre: String,
        idProv: String,
        idDist: String):String

    @Query("SELECT CorreNombre FROM dbcorregimiento_table LIMIT 20")//-----Verificacion
    suspend fun getCorrVer():Array<String>
    @Insert(onConflict = OnConflictStrategy.REPLACE)//----------------------Insert
    suspend fun insertCorre(
        dist: List<DBcorregimiento>)


    //---------------------------------------------------------------------------------------------- Lugar Poblado
    @Query("SELECT lugarPobNombre FROM DBlugarP_table WHERE lugarPobladoId = :idLugar AND corregimientoId = :corre AND  distritoId = :idDist AND provinciaId = :idProv")
    suspend fun getLugarPName(
        idLugar: String,
        corre: String,
        idDist: String,
        idProv: String):String

    @Query("SELECT lugarPobNombre FROM DBlugarP_table WHERE  corregimientoId = :corre AND  distritoId = :idDist AND provinciaId = :idProv")
    suspend fun getLugarPArrayByID(
        corre: String,
        idDist: String,
        idProv: String):Array<String>

    @Query("SELECT lugarPobladoId FROM DBlugarP_table WHERE lugarPobNombre = :name AND corregimientoId = :corre AND  distritoId = :idDist AND provinciaId = :idProv")
    suspend fun getLugarPID(
        name: String,
        corre: String,
        idDist: String,
        idProv: String):String

    @Query("SELECT lugarPobNombre FROM DBlugarP_table LIMIT 20")//-----Verificacion
    suspend fun getLugarPVer():Array<String>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLugarP(
        lugarP: List<DBlugarP>)

    //---------------------------------------------------------------------------------------------- Formulario
    @Query("SELECT * FROM DBform_table")
    suspend fun getForms(): List<DBform>

    @Query("SELECT * FROM DBform_table WHERE idUser = :id")
    suspend fun getFormsbyID(
        id: String
    ): List<DBform>

    @Query("SELECT * FROM DBform_table WHERE ruc = :ruc AND nombreLocal = :name")
    suspend fun selectForm(
        ruc: String,
        name: String,
    ): DBform

    @Query("DELETE FROM DBform_table WHERE ruc = :ruc AND nombreLocal = :name")
    suspend fun deleteForm(
        ruc: String,
        name: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForm(form: DBform)


}





