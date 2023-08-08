package gob.pa.inovacion_empresarial.service.room


import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName


//------------------------------------------------------------------------------ Formulario Completo
@Entity(tableName = "DBform_table",primaryKeys = [
    "ncontrol",
    "idUser"])
data class DBform (
    @ColumnInfo(name = "ncontrol")                  val idNControl: String,
    @ColumnInfo(name = "idUser")                    var idUser: String,
    @ColumnInfo(name = "ruc")                       val ruc: String,
    @ColumnInfo(name = "nombreLocal")               var localName: String?,
    @ColumnInfo(name = "condicion")                 val condition: String?,
    @ColumnInfo(name = "fecheDelServidor")          val serverDate: String?,
    @ColumnInfo(name = "fechaDeGuardado")           val saveDate: String?,
    @ColumnInfo(name = "inconsistencia")            val saveIncon: String?,
    @ColumnInfo(name = "ultimaPagina")              val lastpageForm: String?,
    @ColumnInfo(name = "formulario")                var saveForm: String?
)

@Entity(tableName = "DBviews_table",primaryKeys = [
    "ncontrol",
    "idUser"])
data class DBviews (
    @ColumnInfo(name = "ncontrol")      var idNControl: String,
    @ColumnInfo(name = "idUser")        var idUser: String
)

@Entity(tableName = "DBprovincia_table",primaryKeys = [
    "ProvinciaId"])
data class DBprovincia (
    @SerializedName("provinciaId")      @ColumnInfo(name = "ProvinciaId")       var idProvP: String,
    @SerializedName("provNombre")       @ColumnInfo(name = "ProvNombre")        var provincia: String
)

@Entity(tableName = "DBdistritos_table", primaryKeys = [
    "ProvinciaId",
    "DistritoId"])
data class DBdistritos (
    @SerializedName("provinciaId")      @ColumnInfo(name = "ProvinciaId")       var idProvD: String,
    @SerializedName("distritoId")       @ColumnInfo(name = "DistritoId")        var idDistD: String,
    @SerializedName("distNombre")       @ColumnInfo(name = "DistNombre")        var distrito: String
)

@Entity(tableName = "DBcorregimiento_table", primaryKeys = [
    "ProvinciaId",
    "DistritoId",
    "CorregimientoId"])
data class DBcorregimiento (
    @SerializedName("provinciaId")      @ColumnInfo(name = "ProvinciaId")       var idProvC: String,
    @SerializedName("distritoId")       @ColumnInfo(name = "DistritoId")        var idDistC: String,
    @SerializedName("corregimientoId")  @ColumnInfo(name = "CorregimientoId")   var idCorreC: String,
    @SerializedName("corregNombre")     @ColumnInfo(name = "CorreNombre")       var corregimiento: String
)

@Entity(tableName = "DBlugarP_table", primaryKeys = [
    "provinciaId",
    "distritoId",
    "corregimientoId",
    "lugarPobladoId"])
data class DBlugarP (
    @SerializedName("provinciaId")      @ColumnInfo(name = "provinciaId")       val idProvC: String,
    @SerializedName("distritoId")       @ColumnInfo(name = "distritoId")        val idDistC: String,
    @SerializedName("corregimientoId")  @ColumnInfo(name = "corregimientoId")   val idCorreC: String,
    @SerializedName("lugarPobladoId")   @ColumnInfo(name = "lugarPobladoId")    val idLugarP: String,
    @SerializedName("lugarPobNombre")   @ColumnInfo(name = "lugarPobNombre")    val lugarP: String,
)

