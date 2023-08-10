package gob.pa.inovacion_empresarial.view.fragments

import com.google.gson.Gson
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.aString
import gob.pa.inovacion_empresarial.model.*
import gob.pa.inovacion_empresarial.service.room.DBform

class CreateForm {
    fun create(): ModelForm {
        with (Mob) {
            return ModelForm(
                ncontrol = formComp?.ncontrol,
                obs = formComp?.obs, //---------
                cond = formComp?.cond,
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
    fun createSaved(): DBform {
        val form = CreateForm().create()
        val formString = Gson().toJson(form)
        with (Mob) {
            return DBform(
                idNControl = form.ncontrol ?: "0",
                idUser = authData?.user ?: "E_recovery",
                ruc = form.cap2?.v07ructxt ?: "0-0-0",
                localName = form.cap2?.v05nameLtxt,
                condition = form.cond,
                serverDate = form.dateMod,
                saveDate = Functions.myDate().aString(DATEFORMAT),
                saveIncon = inconsistencias.toString(),
                lastpageForm = indiceFormulario.toString(),
                saveForm = formString
            )
        }
    }


    //fun rechargeCap1() = Mob.formComp?.cap1
    //fun rechargeCap2() = Mob.formComp?.cap2

    fun resetForm() {
       with (Mob) {
            p56stat = null
            seccON = null

            mainWindow = 1
            mainPrevWindow = 0
            indiceFormulario = 0
            obsEncuesta = ""
            obsModulo = ""
            obsTittle = ""

            //--  FORMULARIO
           sendForm = false
           seecap01 = true
           seecap02o1 = true
           seecap02o2 = true
           seecap03 = true
           seecap04 = true
           seecap05o1 = true
           seecap05o2 = true
           seecap0601 = true
           seecap06o2 = true
           seecap06o3 = true
           seecap06o4 = true
           seecap07o1 = true
           seecap07o2 = true
           seecap07o3 = true
           seecap08o1 = true
           seecap08o2 = true
           seecap09o1 = true
           seecap09o2 = true
           seecap10 = true

           seesecc1 = true
           seesecc2 = true
           seesecc3 = true
           seesecc4 = true

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