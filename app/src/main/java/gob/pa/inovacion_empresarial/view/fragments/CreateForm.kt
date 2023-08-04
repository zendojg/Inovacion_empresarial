package gob.pa.inovacion_empresarial.view.fragments

import gob.pa.inovacion_empresarial.model.*

class CreateForm {
    fun create(): ModelForm {
        with (Mob) {
            return ModelForm(
                ncontrol = formComp?.ncontrol,
                obs = formComp?.obs, //---------
                cond = formComp?.cond,
                act = formComp?.act,
                rev = formComp?.rev,
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

    //fun rechargeCap1() = Mob.formComp?.cap1

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