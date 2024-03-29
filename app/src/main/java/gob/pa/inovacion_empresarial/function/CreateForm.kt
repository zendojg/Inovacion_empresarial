package gob.pa.inovacion_empresarial.function

import android.content.Context
import com.google.gson.Gson
import gob.pa.inovacion_empresarial.function.Functions.aString
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelForm
import gob.pa.inovacion_empresarial.service.room.DBform
import gob.pa.inovacion_empresarial.service.room.RoomView

object  CreateForm {
    fun create(): ModelForm {  //---- Rellena con nueva información y devuelve un formulario
        Mob.apply {
            return ModelForm(
                ncontrol = formComp?.ncontrol,
                obs = obsEncuesta, //---------
                cond = condicionID,
                act = formComp?.act,
                rev = formComp?.rev,
                tieneIncon = formComp?.tieneIncon,
                dateCreate = formComp?.dateCreate,
                dateMod = formComp?.dateMod,
                dateModSup = formComp?.dateModSup,
                modSup = formComp?.modSup,
                creator = formComp?.creator,
                mod = formComp?.mod,
                condicion = condicion,
                cap1 = cap1,
                cap2 = cap2,
                cap3 = cap3,
                cap4 = cap4,
                cap5 = cap5,
                cap6 = cap6,
                cap7 = cap7,
                cap8 = cap8,
                cap9 = cap9,
                capx = capx,
                capMod = capMod //-----------
            )
        }
    }

    fun createObDB(): DBform { //---- Crea un archivo DB con los datos del formulario
        val form = create()
        val formString = Gson().toJson(form)
        val idrandom = "RECOVERY-${(100000..999999).random()}"
        Mob.apply {
            return DBform(
                idNControl = form.ncontrol ?: idrandom,
                idUser = authData?.user ?: "E_recovery",
                ruc = form.cap2?.v07ructxt ?: "0-0-0",
                localName = form.cap2?.v05nameLtxt,
                condition = form.cond,
                serverDate = form.dateMod,
                saveDate = Functions.myDate().aString(DATEFORMAT),
                lastpageForm = indiceFormulario.toString(),
                saveForm = formString
            )
        }
    }

    suspend fun createSaved(dvm: DVModel, ctx: Context) = RoomView(dvm, ctx).saveForm(createObDB())
    //fun rechargeCap1() = Mob.formComp?.cap1
    //fun rechargeCap2() = Mob.formComp?.cap2

    fun createLoad(form: ModelForm?) { //-- Crea la carga de trabajo para todos los cap
        Mob.apply {
            formComp = form
            cap1 = form?.cap1
            cap2 = form?.cap2
            cap3 = form?.cap3
            cap4 = form?.cap4
            cap5 = form?.cap5
            cap6 = form?.cap6
            cap7 = form?.cap7
            cap8 = form?.cap8
            cap9 = form?.cap9
            capx = form?.capx
            capMod = form?.capMod
            condicion = form?.condicion
            obsEncuesta = form?.obs ?: ""
            obsModulo = form?.capMod?.observaciones ?: ""
            condicionID = form?.cond
        }
    }

    fun resetLoad() { //---- Reinicia las variables y objetos
        Mob.apply {
            indiceFormulario = 0
            obsEncuesta = ""
            obsModulo = ""
            condicionID = null

            //--  FORMULARIO
            infoCap = mutableListOf()
            for (capitulo in MENU_P00 until OBSE_P24) {
                val internalInfo = Mob.InternalInfo(
                    indexCap = capitulo,
                    capView = false,
                    incons = false
                )
                infoCap.add(internalInfo)
            }
            viewIncon = false
            inconsArray = null

            cap1 = null
            cap2 = null
            cap3 = null
            cap4 = null
            cap5 = null
            cap6 = null
            cap7 = null
            cap8 = null
            cap9 = null
            capx = null
            capMod = null
            condicion = null
            formComp = ModelForm(
                ncontrol = null,
                obs = null,
                cond = null,
                act = null,
                rev = null,
                tieneIncon = null,
                dateCreate = null,
                dateMod = null,
                dateModSup = null,
                modSup = null,
                creator = null,
                mod = null,
                condicion = null,
                cap1 = null,
                cap2 = null,
                cap3 = null,
                cap4 = null,
                cap5 = null,
                cap6 = null,
                cap7 = null,
                cap8 = null,
                cap9 = null,
                capx = null,
                capMod = null
            )
        }
    }

}