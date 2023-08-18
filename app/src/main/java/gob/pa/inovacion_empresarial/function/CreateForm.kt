package gob.pa.inovacion_empresarial.function

import com.google.gson.Gson
import gob.pa.inovacion_empresarial.function.Functions.aString
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelForm
import gob.pa.inovacion_empresarial.service.room.DBform

class CreateForm {
    fun create(): ModelForm {
        with(Mob) {
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
        val idrandom = "RECOVERY-${(10000..99999).random()}"
        with(Mob) {
            return DBform(
                idNControl = form.ncontrol ?: idrandom,
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
    fun createLoad(form: ModelForm?) {
        with(Mob) {
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
        }
    }
    fun resetLoad() {
        with(Mob) {
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

            cleanForm()
        }
    }

    fun cleanForm() {
        Mob.cap1 = null
        Mob.cap2 = null
        Mob.cap3 = null
        Mob.cap4 = null
        Mob.cap5 = null
        Mob.cap6 = null
        Mob.cap7 = null
        Mob.cap8 = null
        Mob.cap9 = null
        Mob.capx = null
        Mob.capMod = null
        Mob.condicion = null
        Mob.formComp = ModelForm(
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