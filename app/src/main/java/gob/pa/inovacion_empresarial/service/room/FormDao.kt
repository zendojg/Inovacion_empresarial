package gob.pa.inovacion_empresarial.service.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FormDao {

    //---- Provincias ------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProv(
        prov: List<DBprovincia>
    )

    @Query("SELECT ProvNombre FROM dbprovincia_table")
    suspend fun getProvArray(): Array<String>

    @Query("SELECT ProvNombre FROM dbprovincia_table WHERE ProvinciaId = :idProv")
    suspend fun getProvName(
        idProv: String
    ): String

    @Query("SELECT ProvinciaId FROM dbprovincia_table WHERE ProvNombre = :prov")
    suspend fun getProvID(
        prov: String
    ): String

    //---- Distritos ------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDist(
        dist: List<DBdistritos>
    )

    @Query("SELECT DistNombre FROM dbdistritos_table LIMIT 20")//-----Verificacion
    suspend fun getDistVer(): Array<String>

    @Query("SELECT DistNombre FROM dbdistritos_table WHERE ProvinciaId = :idProv AND DistritoId = :idDist")
    suspend fun getDistName(
        idProv: String,
        idDist: String
    ): String

    @Query("SELECT DistNombre FROM dbdistritos_table WHERE ProvinciaId = :idProv")
    suspend fun getDistArrayByID(
        idProv: String
    ): Array<String>

    @Query("SELECT DistritoId FROM dbdistritos_table WHERE DistNombre = :dist AND ProvinciaId = :idProv")
    suspend fun getDistID(
        idProv: String,
        dist: String
    ): String


    //---- Corregimientos ------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCorre(dist: List<DBcorregimiento>)

    @Query("SELECT CorreNombre FROM dbcorregimiento_table LIMIT 20")
    suspend fun getCorrVer(): Array<String>

    @Query("SELECT CorreNombre FROM dbcorregimiento_table " +
                "WHERE  ProvinciaId = :idProv AND  DistritoId = :idDist AND CorregimientoId = :idCorre")
    suspend fun getCorreName(
        idProv: String,
        idDist: String,
        idCorre: String
    ): String

    @Query("SELECT CorreNombre FROM dbcorregimiento_table WHERE ProvinciaId = :idProv AND DistritoId = :idDist")
    suspend fun getCorreArrayByID(
        idProv: String,
        idDist: String
    ): Array<String>

    @Query("SELECT CorregimientoId FROM dbcorregimiento_table " +
                "WHERE CorreNombre = :corre AND ProvinciaId = :idProv AND  DistritoId = :idDist")
    suspend fun getCorreID(
        idProv: String,
        idDist: String,
        corre: String
    ): String


    //---- Lugar Poblado ------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLugarP(
        lugarP: List<DBlugarP>
    )

    @Query("SELECT lugarPobNombre FROM DBlugarP_table LIMIT 20")
    suspend fun getLugarPVer(): Array<String>

    @Query("SELECT lugarPobNombre FROM DBlugarP_table " +
                "WHERE lugarPobladoId = :idLugar AND corregimientoId = :idcorre " +
                "AND  distritoId = :idDist AND provinciaId = :idProv")
    suspend fun getLugarPName(
        idProv: String,
        idDist: String,
        idLugar: String,
        idcorre: String
    ): String

    @Query("SELECT lugarPobNombre FROM DBlugarP_table " +
                "WHERE  corregimientoId = :idCorre AND  distritoId = :idDist AND provinciaId = :idProv")
    suspend fun getLugarPArrayByID(
        idProv: String,
        idDist: String,
        idCorre: String
    ): Array<String>

    @Query("SELECT lugarPobladoId FROM DBlugarP_table " +
                "WHERE lugarPobNombre = :lugarname AND corregimientoId = :idcorre " +
                "AND  distritoId = :idDist AND provinciaId = :idProv")
    suspend fun getLugarPID(
        idProv: String,
        idDist: String,
        idcorre: String,
        lugarname: String
    ): String


    //---- Formulario ------
    @Query("SELECT * FROM DBform_table")
    suspend fun getAllForms(): List<DBform>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForm(form: DBform): Long

    @Query("SELECT * FROM DBform_table WHERE idUser = :id")
    suspend fun getFormsUser(
        id: String
    ): List<DBform>

    @Query("SELECT * FROM DBform_table WHERE idUser = :idUser AND ncontrol = :nControl")
    suspend fun getFormsbyID(
        nControl: String,
        idUser: String
    ): DBform

    @Query("DELETE FROM DBform_table WHERE ncontrol = :nControl AND idUser = :idUser")
    suspend fun deleteForm(
        nControl: String,
        idUser: String
    )


}





