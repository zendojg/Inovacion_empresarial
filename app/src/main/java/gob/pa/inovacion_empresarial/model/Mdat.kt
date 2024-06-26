package gob.pa.inovacion_empresarial.model

import android.text.TextWatcher
import android.widget.EditText
import android.widget.Spinner
import com.google.gson.annotations.SerializedName

//-- Texwatcher reset
data class ModelTexWatchers(
    val edittext: EditText,
    val watcher: TextWatcher
)
data class ModelSpinLister(
    val spinner: Spinner,
    var indice: Int
)

//-- LOGIN
data class ModelLog(
    @SerializedName("username")                 val user: String,
    @SerializedName("password")                 val pass: String,
    @SerializedName("rol")                      val rol: String)
data class ModelAuthTokenInfo(
    @SerializedName("username")                 val name: String?,
    @SerializedName("tokenString")              val tokenS: String?,
    @SerializedName("expireAt")                 val expire: String?)
data class ModelAuthResult(
    @SerializedName("accessToken")              val token: String?,
    @SerializedName("refreshToken")             val infotoken: ModelAuthTokenInfo?)
data class ModelAuth(
    @SerializedName("result")                   val result: ModelAuthResult?,
    @SerializedName("infoMessage")              val infoMsg: String?,
    @SerializedName("name")                     val name: String?,
    @SerializedName("user")                     val user: String?,
    @SerializedName("rol")                      val rol: String?,)

data class ModelAuthResp(
    val code: Int?,
    val msg: String?,
    val body: ModelAuth?)

data class ModelResp(
    val code: Int?,
    val msg: String?)

data class ModelCap1(
    @SerializedName("id")                       val id: String?,//-------------
    @SerializedName("numControl")               val ncontrol: String?,//------
    @SerializedName("prov")                     val v01provtxt: String?,
    @SerializedName("dist")                     val v02disttxt: String?,
    @SerializedName("correg")                   val v03corretxt: String?,
    @SerializedName("lugarPob")                 val v04lugartxt: String?)
data class ModelCap2(
    @SerializedName("id")                       val id: String?,//-------------
    @SerializedName("numControl")               val ncontrol: String?,//------
    @SerializedName("nombComerc")               val v05nameLtxt: String?,
    @SerializedName("razSocial")                val v06razontxt: String?,
    @SerializedName("ruc")                      val v07ructxt: String?,
    @SerializedName("dv")                       val v07dvtxt: String?,
    @SerializedName("dirEmp")                   val v08dirtxt: String?,
    @SerializedName("otraRefDirEmp")            val v08dirreftxt: String?,
    @SerializedName("telfEmp1")                 val v09tel1txt: String?,
    @SerializedName("telfEmp2")                 val v09tel2txt: String?,
    @SerializedName("telfEmpCel")               val v10celtxt: String?,
    @SerializedName("corrEmp")                  val v11emailtxt: String?,
    @SerializedName("pagWeb")                   val v12webtxt: String?,
    @SerializedName("numClave")                 val v13nclavetxt: String?,
    @SerializedName("cantLocales")              val v14nlNum: String?,
    @SerializedName("cantLocalesPma")           val v15nlNumPma: String?,
    @SerializedName("cantLocalesProv")          val v15nlNumProv: String?,
    @SerializedName("nombInfor")                val v16infonametxt: String?,
    @SerializedName("cargo")                    val v17cargotxt: String?,
    @SerializedName("dirInfor")                 val v18dirtxt: String?,
    @SerializedName("otraRefDirInfor")          val v18dirreftxt: String?,
    @SerializedName("telfInfor1")               val v19tel1txt: String?,
    @SerializedName("telfInfor2")               val v19tel2txt: String?,
    @SerializedName("telfInforCel")             val v20celtxt: String?,
    @SerializedName("correoInfor")              val v21emailtxt: String?)
data class ModelCap3(
    @SerializedName("id")                       val id: String?,//-------------
    @SerializedName("numControl")               val ncontrol: String?,//------
    @SerializedName("anio")                     val v22yearNum: String?,
    @SerializedName("natJur")                   val v23natNum: String?,
    @SerializedName("natJurEspecif")            val v23natdesctxt: String?,
    @SerializedName("esEmpOrgFormal")           val v24check: Boolean?,
    @SerializedName("tipoOrgFormal")            val v25typeNum: String?,
    @SerializedName("tipoOrgFormalEspecif")     val v25typetxt: String?,
    @SerializedName("nombCompleto")             val v26nametxt: String?,
    @SerializedName("nombPaisOrig")             val v27countrytxt: String?)
data class ModelCap4(
    @SerializedName("id")                       val id: String?,//-------------
    @SerializedName("numControl")               val ncontrol: String?,//------
    @SerializedName("principActEconom")         val v28acttxt: String?,
    @SerializedName("principCINU")              val v28cinutxt: String?,
    @SerializedName("secundActEconom1")         val v29act1txt: String?,
    @SerializedName("secundCINU1")              val v29cinu1txt: String?,
    @SerializedName("secundActEconom2")         val v29act2txt: String?,
    @SerializedName("secundCINU2")              val v29cinu2txt: String?,
    @SerializedName("secundActEconom3")         val v29act3txt: String?,
    @SerializedName("secundCINU3")              val v29cinu3txt: String?,
    @SerializedName("secundActEconom4")         val v29act4txt: String?,
    @SerializedName("secundCINU4")              val v29cinu4txt: String?)
data class ModelCap5(
    @SerializedName("id")                                       val id: String?,
    @SerializedName("numControl")                               val ncontrol: String?,
    @SerializedName("ventNetasNac2021")                         val v30txt21a: String?,
    @SerializedName("ventNetasExpor2021")                       val v30txt21b: String?,
    @SerializedName("ventNetasTotal2021")                       val v30txt21T: String?,
    @SerializedName("ventNetasNac2022")                         val v30txt22a: String?,
    @SerializedName("ventNetasExpor2022")                       val v30txt22b: String?,
    @SerializedName("ventNetasTotal2022")                       val v30txt22T: String?,
    @SerializedName("ingBrutMenosCientoCinc2021")               val v31check21a: Boolean?,
    @SerializedName("ingBrutMenosCientoCinc2022")               val v31check22a: Boolean?,
    @SerializedName("ingBrutCientoCincAMillon2021")             val v31check21b: Boolean?,
    @SerializedName("ingBrutCientoCincAMillon2022")             val v31check22b: Boolean?,
    @SerializedName("ingBrutMillonUnoADosMilloQuin2021")        val v31check21c: Boolean?,
    @SerializedName("ingBrutMillonUnoADosMilloQuin2022")        val v31check22c: Boolean?,
    @SerializedName("ingBrutDosMilloQuinUnoAQuinceMillon2021")  val v31check21d: Boolean?,
    @SerializedName("ingBrutDosMilloQuinUnoAQuinceMillon2022")  val v31check22d: Boolean?,
    @SerializedName("ingBrutDieciSeisMilloAmas2021")            val v31check21e: Boolean?,
    @SerializedName("ingBrutDieciSeisMilloAmas2022")            val v31check22e: Boolean?,
    @SerializedName("prodBienServic2021")                       val v32check21: Boolean?,
    @SerializedName("prodBienServic2022")                       val v32check22: Boolean?,
    @SerializedName("descUnoProdBienServic2021")                val v33txt1s21: String?,
    @SerializedName("descUnoProdBienServic2022")                val v33txt1s22: String?,
    @SerializedName("descDosProdBienServic2021")                val v33txt2s21: String?,
    @SerializedName("descDosProdBienServic2022")                val v33txt2s22: String?,
    @SerializedName("descTresProdBienServic2021")               val v33txt3s21: String?,
    @SerializedName("descTresProdBienServic2022")               val v33txt3s22: String?,
    @SerializedName("destinVentLocal2021")                      val v34check1o21: Boolean?,
    @SerializedName("destinVentLocal2022")                      val v34check1o22: Boolean?,
    @SerializedName("destinVentNac2021")                        val v34check2o21: Boolean?,
    @SerializedName("destinVentNac2022")                        val v34check2o22: Boolean?,
    @SerializedName("destinVentExt2021")                        val v34check3o21: Boolean?,
    @SerializedName("destinVentExt2022")                        val v34check3o22: Boolean?,
    @SerializedName("descOtro")                                 val v35txtOtro: String?,
    @SerializedName("homNacPrim")                               val v35txthomNaca: String?,
    @SerializedName("homExtPrim")                               val v35txthomExta: String?,
    @SerializedName("homNacSec")                                val v35txthomNacb: String?,
    @SerializedName("homExtSec")                                val v35txthomExtb: String?,
    @SerializedName("homNacTec")                                val v35txthomNacc: String?,
    @SerializedName("homExtTec")                                val v35txthomExtc: String?,
    @SerializedName("homNacLic")                                val v35txthomNacd: String?,
    @SerializedName("homExtLic")                                val v35txthomExtd: String?,
    @SerializedName("homNacEspec")                              val v35txthomNace: String?,
    @SerializedName("homExtEspec")                              val v35txthomExte: String?,
    @SerializedName("homNacMaest")                              val v35txthomNacf: String?,
    @SerializedName("homExtMaest")                              val v35txthomExtf: String?,
    @SerializedName("homNacDoct")                               val v35txthomNacg: String?,
    @SerializedName("homExtDoct")                               val v35txthomExtg: String?,
    @SerializedName("homNacOtro")                               val v35txthomNach: String?,
    @SerializedName("homExtOtro")                               val v35txthomExth: String?,
    @SerializedName("homNacTotal")                              val v35txthomNacT: String?,
    @SerializedName("homExtTotal")                              val v35txthomExtT: String?,
    @SerializedName("mujNacPrim")                               val v35txtmujNaca: String?,
    @SerializedName("mujExtPrim")                               val v35txtmujExta: String?,
    @SerializedName("mujNacSec")                                val v35txtmujNacb: String?,
    @SerializedName("mujExtSec")                                val v35txtmujExtb: String?,
    @SerializedName("mujNacTec")                                val v35txtmujNacc: String?,
    @SerializedName("mujExtTec")                                val v35txtmujExtc: String?,
    @SerializedName("mujNacLic")                                val v35txtmujNacd: String?,
    @SerializedName("mujExtLic")                                val v35txtmujExtd: String?,
    @SerializedName("mujNacEspec")                              val v35txtmujNace: String?,
    @SerializedName("mujExtEspec")                              val v35txtmujExte: String?,
    @SerializedName("mujNacMaest")                              val v35txtmujNacf: String?,
    @SerializedName("mujExtMaest")                              val v35txtmujExtf: String?,
    @SerializedName("mujNacDoct")                               val v35txtmujNacg: String?,
    @SerializedName("mujExtDoct")                               val v35txtmujExtg: String?,
    @SerializedName("mujNacOtro")                               val v35txtmujNach: String?,
    @SerializedName("mujExtOtro")                               val v35txtmujExth: String?,
    @SerializedName("mujNacTotal")                              val v35txtmujNacT: String?,
    @SerializedName("mujExtTotal")                              val v35txtmujExtT: String?,
    @SerializedName("emplNac2021")                              val v36txtempNac21: String?,
    @SerializedName("emplNac2022")                              val v36txtempNac22: String?,
    @SerializedName("emplExt2021")                              val v36txtempExt21: String?,
    @SerializedName("emplExt2022")                              val v36txtempExt22: String?,
    @SerializedName("emplTotal2021")                            val v36txtempT21: String?,
    @SerializedName("emplTotal2022")                            val v36txtempT22: String?,
    @SerializedName("tienePersLabInnov2022")                    val v37check: Boolean?,
    @SerializedName("tienePersLabInnovInforYSist")              val v38check1: Boolean?,
    @SerializedName("cantPersLabInnovInforYSist")               val v38txt1: String?,
    @SerializedName("tienePersLabInnovInvYDes")                 val v38check2: Boolean?,
    @SerializedName("cantPersLabInnovInvYDes")                  val v38txt2: String?,
    @SerializedName("tienePersLabInnovIngYDisInd")              val v38check3: Boolean?,
    @SerializedName("cantPersLabInnovIngYDisInd")               val v38txt3: String?,
    @SerializedName("descPersLabInnov")                         val v38txt4desc: String?,
    @SerializedName("tienePersLabInnovOtro")                    val v38check4: Boolean?,
    @SerializedName("cantPersLabInnovOtro")                     val v38txt4: String?)
data class ModelCap6(
    @SerializedName("id")                                                   val id: Int?,
    @SerializedName("numControl")                                           val ncontrol: Int?,
    @SerializedName("empIntrodBienNuevos2021")                              val v39check21o1: Boolean?,
    @SerializedName("empIntrodBienNuevos2022")                              val v39check22o1: Boolean?,
    @SerializedName("empIntrodServicNuevos2021")                            val v39check21o2: Boolean?,
    @SerializedName("empIntrodServicNuevos2022")                            val v39check22o2: Boolean?,
    @SerializedName("esInnovProdNuevaParaMercad")                           val v40check1: Boolean?,
    @SerializedName("esInnovProdNuevaParaEmp")                              val v40check2: Boolean?,
    @SerializedName("descDesarrInnovacProd")                                val v41check: String?,
    @SerializedName("empIntrodNuevoMejoMetManufac2021")                     val v42check21o1: Boolean?,
    @SerializedName("empIntrodNuevoMejoMetLogist2021")                      val v42check21o2: Boolean?,
    @SerializedName("empIntrodNuevoMejoActSoport2021")                      val v42check21o3: Boolean?,
    @SerializedName("empIntrodNuevoMejoMetManufac2022")                     val v42check22o1: Boolean?,
    @SerializedName("empIntrodNuevoMejoMetLogist2022")                      val v42check22o2: Boolean?,
    @SerializedName("empIntrodNuevoMejoActSoport2022")                      val v42check22o3: Boolean?,
    @SerializedName("esInnovProcesNuevaParaMercad")                         val v43check1: Boolean?,
    @SerializedName("esInnovProcesNuevaParaEmp")                            val v43check2: Boolean?,
    @SerializedName("descDesarrInnovacProces")                              val v44check: String?,
    @SerializedName("gradoImportEnAmpliacionBienesYServ")                   val v45txtGrado1: String?,
    @SerializedName("gradoImportEnIngresNuevosMercad")                      val v45txtGrado2: String?,
    @SerializedName("gradoImportEnMejCalidBienesYServc")                    val v45txtGrado3: String?,
    @SerializedName("gradoImportEnAumentCapacidYFlexi")                     val v45txtGrado4: String?,
    @SerializedName("gradoImportEnReducCostosPorUnidadProd")                val v45txtGrado5: String?,
    @SerializedName("gradoImportEnReducImpactMedioAmbYMejoSanid")           val v45txtGrado6: String?,
    @SerializedName("empIntrodNuevPractNegocOrganiz2021")                   val v46check21o1: Boolean?,
    @SerializedName("empIntrodNuevoMetodOrganizRespYTomaDesic2021")         val v46check21o2: Boolean?,
    @SerializedName("empIntrodNuevoMetodOrganizRelacExt2021")               val v46check21o3: Boolean?,
    @SerializedName("empIntrodNuevPractNegocOrganiz2022")                   val v46check22o1: Boolean?,
    @SerializedName("empIntrodNuevoMetodOrganizRespYTomaDesic2022")         val v46check22o2: Boolean?,
    @SerializedName("empIntrodNuevoMetodOrganizRelacExt2022")               val v46check22o3: Boolean?,
    @SerializedName("gradoImportOrgEnReducTiempNecesClientYProve")          val v47txtGrado1: String?,
    @SerializedName("gradoImportOrgEnMejHabilidDesaNuevProdYProc")          val v47txtGrado2: String?,
    @SerializedName("gradoImportOrgEnMejCalidPorUnidProd")                  val v47txtGrado3: String?,
    @SerializedName("gradoImportOrgEnReducCostPorUnidProd")                 val v47txtGrado4: String?,
    @SerializedName("gradoImportOrgEnMejComunicYParticDeInformac")          val v47txtGrado5: String?,
    @SerializedName("empIntrodCambEnDiseEnvYEmbalajComercializ2021")        val v48check21o1: Boolean?,
    @SerializedName("empIntrodNuevTecPromoDelProdComercializ2021")          val v48check21o2: Boolean?,
    @SerializedName("empIntrodNuevCanalDeDistribComercializ2021")           val v48check21o3: Boolean?,
    @SerializedName("empIntrodNuevTarifDeBienesYServicComercializ2021")     val v48check21o4: Boolean?,
    @SerializedName("empIntrodCambEnDiseEnvYEmbalajComercializ2022")        val v48check22o1: Boolean?,
    @SerializedName("empIntrodNuevTecPromoDelProdComercializ2022")          val v48check22o2: Boolean?,
    @SerializedName("empIntrodNuevCanalDeDistribComercializ2022")           val v48check22o3: Boolean?,
    @SerializedName("empIntrodNuevTarifDeBienesYServicComercializ2022")     val v48check22o4: Boolean?,
    @SerializedName("gradoImportComercializEnIncreParticipMercad")          val v49txtGrado1: String?,
    @SerializedName("gradoImportComercializEnIntrodProdParaNuevoSegMercad") val v49txtGrado2: String?,
    @SerializedName("gradoImportComercializEnIntrodProdParaMercadGeogNuevo")val v49txtGrado3: String?)
data class ModelCap7(
    @SerializedName("id")                                                   val id: Int?,
    @SerializedName("numControl")                                           val ncontrol: Int?,
    @SerializedName("tienePersonalOfiLabInvYDesarr")                        val v50check: Boolean?,
    @SerializedName("empRealizoInvYDesEnEmpresa2021")                       val v51check21o1: Boolean?,
    @SerializedName("empRealizoInvYDesEnFueraDeEmpresa2021")                val v51check21o2: Boolean?,
    @SerializedName("porceInvertidoInvYDesEnEmpresa2021")                   val v51num21o1: String?,
    @SerializedName("porceInvertidoInvYDesFueraDeEmpresa2021")              val v51num21o2: String?,
    @SerializedName("empRealizoInvYDesEnEmpresa2022")                       val v51check22o1: Boolean?,
    @SerializedName("empRealizoInvYDesEnFueraDeEmpresa2022")                val v51check22o2: Boolean?,
    @SerializedName("porceInvertidoInvYDesEnEmpresa2022")                   val v51num22o1: String?,
    @SerializedName("porceInvertidoInvYDesFueraDeEmpresa2022")              val v51num22o2: String?,
    @SerializedName("fuentesInformaEnIniciativaDelDepartDeInvestYDesa")     val v52txt01: String?,
    @SerializedName("fuentesInformaEnDeptoDeIngYProd")                      val v52txt02: String?,
    @SerializedName("fuentesInformaEnDeptoDeDiseno")                        val v52txt03: String?,
    @SerializedName("fuentesInformaEnDeptoDeComerc")                        val v52txt04: String?,
    @SerializedName("fuentesInformaEnEmpGerentInternos")                    val v52txt05: String?,
    @SerializedName("fuentesInformaEnEmpGerentExternos")                    val v52txt06: String?,
    @SerializedName("fuentesInformaEnPlanEstrateg")                         val v52txt07: String?,
    @SerializedName("fuentesInformaEnClient")                               val v52txt08: String?,
    @SerializedName("fuentesInformaEnCompetid")                             val v52txt09: String?,
    @SerializedName("fuentesInformaEnConsult")                              val v52txt10: String?,
    @SerializedName("fuentesInformaEnUniversOCentroDeInvest")               val v52txt11: String?,
    @SerializedName("fuentesInformaEnPatentes")                             val v52txt12: String?,
    @SerializedName("fuentesInformaEnFeriasYExposic")                       val v52txt13: String?,
    @SerializedName("fuentesInformaEnEntidadesDeGob")                       val v52txt14: String?,
    @SerializedName("fuentesInformaDescripOtra")                            val v52txt15desc: String?,
    @SerializedName("fuentesInformaOtraResp")                               val v52txt15: String?,
    @SerializedName("gastoAdquisicMaquYEquip2021")                          val v53num21a: String?,
    @SerializedName("gastoAdquisicDeConocExternParaInnovac2021")            val v53num21b: String?,
    @SerializedName("gastoCapacitParaInnovac2021")                          val v53num21c: String?,
    @SerializedName("gastoIntrodDeInnovacAlMercad2021")                     val v53num21d: String?,
    @SerializedName("gastoDisenoParaInnovac2021")                           val v53num21e: String?,
    @SerializedName("gastoInstalacYPuestAPuntoNuevEquip2021")               val v53num21f: String?,
    @SerializedName("gastoOtraActividades2021")                             val v53num21g: String?,
    @SerializedName("totalGastoActInnovac2021")                             val v53num1T21: String?,
    @SerializedName("gastoAdquisicMaquYEquip2022")                          val v53num22a: String?,
    @SerializedName("gastoAdquisicDeConocExternParaInnovac2022")            val v53num22b: String?,
    @SerializedName("gastoCapacitParaInnovac2022")                          val v53num22c: String?,
    @SerializedName("gastoIntrodDeInnovacAlMercad2022")                     val v53num22d: String?,
    @SerializedName("gastoDisenoParaInnovac2022")                           val v53num22e: String?,
    @SerializedName("gastoInstalacYPuestAPuntoNuevEquip2022")               val v53num22f: String?,
    @SerializedName("gastoOtraActividades2022")                             val v53num22g: String?,
    @SerializedName("totalGastoActInnovac2022")                             val v53num1T22: String?,
    @SerializedName("gastoDescripOtraActividad")                            val v53txtgdesc: String?,
    @SerializedName("razonInnovNoRentable")                                 val v54txt01: String?,
    @SerializedName("razonMercadNoRequiereYValoraNuevProd")                 val v54txt02: String?,
    @SerializedName("razonNoEsNecesarioInnov")                              val v54txt03: String?,
    @SerializedName("razonCarencdeRecursFinancPublico")                     val v54txt04: String?,
    @SerializedName("razonCarencdeRecursFinancPrivado")                     val v54txt05: String?,
    @SerializedName("razonCarencdePersonalCapacitad")                       val v54txt06: String?,
    @SerializedName("razonTamanInadecDelMercad")                            val v54txt07: String?,
    @SerializedName("razonDesconocDeApoyGuberna")                           val v54txt08: String?,
    @SerializedName("razonDificultEnAccedApoyGuberna")                      val v54txt09: String?,
    @SerializedName("razonDificultBurocratCostoSistProteccAPropIntelect")   val v54txt10: String?,
    @SerializedName("razonDificultAccesCostoFinancdeInnovac")               val v54txt11: String?,
    @SerializedName("razonInsuficientIncentPorActivEconomDesarrPorEmpre")   val v54txt12: String?,
    @SerializedName("razonInsuficientIncentPorBajaReceptivEnDemand")        val v54txt13: String?,
    @SerializedName("razonInsuficientIncentPorPolitPublicasDefici")         val v54txt14: String?,
    @SerializedName("razonActInnovacOtra")                                  val v54txt15: String?,
    @SerializedName("razonDescripActInnovacOtra")                           val v54txt15desc: String?,
    @SerializedName("factCostoPorFaltFondPropio")                           val v55txt1a: String?,
    @SerializedName("factCostoPorFaltFinancExterno")                        val v55txt1b: String?,
    @SerializedName("factCostoPorInnovacAlto")                              val v55txt1c: String?,
    @SerializedName("factConocPorFaltaPersonCalif")                         val v55txt2a: String?,
    @SerializedName("factConocPorFaltaInformacEnTecnolog")                  val v55txt2b: String?,
    @SerializedName("factConocPorFaltaInformacEnMercad")                    val v55txt2c: String?,
    @SerializedName("factConocPorDificEnEncontrCooperac")                   val v55txt2d: String?,
    @SerializedName("factMercadDominadPorEmpresEstablecida")                val v55txt3a: String?,
    @SerializedName("factMercadEnIncertidAlaDemandPorBienYServic")          val v55txt3b: String?,
    @SerializedName("factNoNecesariPorInnovacPrevia")                       val v55txt4a: String?,
    @SerializedName("factNoNecesariPorFaltaDeDemandEnInnovac")              val v55txt4b: String?,
    @SerializedName("factPorDificultRegulatoria")                           val v55txt4c: String?)
data class ModelCap8(
    @SerializedName("id")                                                               val id: Int?,
    @SerializedName("numControl")                                                       val ncontrol: Int?,
    @SerializedName("conoceEmprExistDeInstituPubPrivYOrganInternEnApoyActivInnovac")    val v56check: Boolean?,
    @SerializedName("porSubsidioCredBenefAmpyme")                                       val v57num1a: String?,
    @SerializedName("ampymeMontoSolicit")                                               val v57monto1a: String?,
    @SerializedName("porSubsidioCredBenefSenacyt")                                      val v57num1b: String?,
    @SerializedName("senacytMontoSolicit")                                              val v57monto1b: String?,
    @SerializedName("porSubsidioCredBenefDescripOtraInstNac")                           val v57desc1c: String?,
    @SerializedName("porSubsidioCredBenefSolicitOtraInstNac")                           val v57num1c: String?,
    @SerializedName("porSubsidioCredBenefOtraInstNacMontoSolicit")                      val v57monto1c: String?,
    @SerializedName("porSubsidioCredBenefBID")                                          val v57num2a: String?,
    @SerializedName("bidMontoSolicit")                                                  val v57monto2a: String?,
    @SerializedName("porSubsidioCredBenefBancoMundial")                                 val v57num2b: String?,
    @SerializedName("bancoMundialMontoSolicit")                                         val v57monto2b: String?,
    @SerializedName("porSubsidioCredBenefDescripOtraInstInternac")                      val v57desc2c: String?,
    @SerializedName("porSubsidioCredBenefSolicitOtraInstInternac")                      val v57num2c: String?,
    @SerializedName("porSubsidioCredBenefOtraInstInternacMontoSolicit")                 val v57monto2c: String?,
    @SerializedName("porOrigDeEmpreReinversDeUtilidad")                                 val v58num1a: String?,
    @SerializedName("porOrigDeEmpreAportDeSocio")                                       val v58num1b: String?,
    @SerializedName("porOrigDeEmpreAportCasaMatriz")                                    val v58num1c: String?,
    @SerializedName("porOrigDeProveedor")                                               val v58num2a: String?,
    @SerializedName("porOrigDeCliente")                                                 val v58num2b: String?,
    @SerializedName("porOrigDeCompetidor")                                              val v58num2c: String?,
    @SerializedName("porOrigDeConsultLabComercInstituPrivad")                           val v58num2d: String?,
    @SerializedName("porOrigDeUniversidadEducacSup")                                    val v58num3a: String?,
    @SerializedName("porOrigDeInstitInvPublGobierno")                                   val v58num3b: String?,
    @SerializedName("porOrigDeFundacONG")                                               val v58num4a: String?,
    @SerializedName("porOrigDeBancaComerc")                                             val v58num4b: String?,
    @SerializedName("porOrigDeOrganisInternac")                                         val v58num4c: String?,
    @SerializedName("porOrigDescripOtraFuente")                                         val v58desc4d: String?,
    @SerializedName("porOrigOtraFuente")                                                val v58num4d: String?)
data class ModelCap9(
    @SerializedName("id")                                       val id: Int?,
    @SerializedName("numControl")                               val ncontrol: Int?,
    @SerializedName("obtuvoEnPaisOExterior")                    val v59check: Boolean?,
    @SerializedName("cantEnPaisOExterior")                      val v59num: String?,
    //@SerializedName("paisDeAprobacion")                         val v60num: String?,

    @SerializedName("paisDeAprobacionPanama")                   val v60check1: Boolean?,
    @SerializedName("paisDeAprobacionCentro")                   val v60check2: Boolean?,
    @SerializedName("paisDeAprobacionUSA")                      val v60check3: Boolean?,
    @SerializedName("paisDeAprobacionUnionEuropea")             val v60check4: Boolean?,
    @SerializedName("paisDeAprobacionJapon")                    val v60check5: Boolean?,
    @SerializedName("paisDeAprobacionOtro")                     val v60check6: Boolean?,

    @SerializedName("paisDeAprobacionOtroDescrip")              val v60txtotro: String?,

    @SerializedName("porMarca")                                 val v61check1: Boolean?,
    @SerializedName("porPatente")                               val v61check2: Boolean?,
    @SerializedName("porModeloUtilidad")                        val v61check3: Boolean?,
    @SerializedName("porDiseIndustrial")                        val v61check4: Boolean?,
    @SerializedName("porDerechDeAutor")                         val v61check5: Boolean?,
    @SerializedName("porDenominacDeOrig")                       val v61check6: Boolean?,
    @SerializedName("porClausulDeConfidencialEmpleado")         val v61check7: Boolean?,
    @SerializedName("porClausulDeConfidencialProveedCliente")   val v61check8: Boolean?,
    @SerializedName("tieneCertifiPorNormaCalidad")              val v62check: Boolean?,
    @SerializedName("certifiISO9001")                           val v63num1: String?,
    @SerializedName("certifiISO14001")                          val v63num2: String?,
    @SerializedName("certifiISO_IEC17025")                      val v63num3: String?,
    @SerializedName("certifiISO_IEC20000")                      val v63num4: String?,
    @SerializedName("certifiOtra")                              val v63num5: String?,
    @SerializedName("certifiDescripOtra")                       val v63des5: String?,
    @SerializedName("tieneProdCertificado")                     val v64check: Boolean?,
    @SerializedName("normaCalidadUno")                          val v65txt1: String?,
    @SerializedName("normaCalidadUnoAnio")                      val v65num1: String?,
    @SerializedName("normaCalidadDos")                          val v65txt2: String?,
    @SerializedName("normaCalidadDosAnio")                      val v65num2: String?)
data class ModelCap10(
    @SerializedName("id")                                       val id: Int?,
    @SerializedName("numControl")                               val ncontrol: Int?,
    @SerializedName("productos")                                val v66check1: Boolean?,
    @SerializedName("procesos")                                 val v66check2: Boolean?,
    @SerializedName("organizacional")                           val v66check3: Boolean?,
    @SerializedName("comercializacion")                         val v66check4: Boolean?)
data class ModelMod(
    @SerializedName("id")                                                       val id: Int?,
    @SerializedName("usoEmprInternetEnActivid")                                 val v1check: Boolean?,
    @SerializedName("usoConexFija")                                             val v2check1: Boolean?,
    @SerializedName("usoConexFijaDSL")                                          val v2check1a: Boolean?,
    @SerializedName("usoConexFijaRedHibrida")                                   val v2check1b: Boolean?,
    @SerializedName("usoConexFijaSatelital")                                    val v2check1c: Boolean?,
    @SerializedName("usoConexFijaOtro")                                         val v2check1d: Boolean?,
    @SerializedName("usoConexFijaDescripOtro")                                  val v2txtDesc1d: String?,
    @SerializedName("usoConexMovil")                                            val v2check2: Boolean?,
//    @SerializedName("usoConexMovilDescrip")                                     val v2txtnull: String?,
    @SerializedName("medidaSegPorAutenticacion")                                val v3check1: Boolean?,
    @SerializedName("medidaSegPorBackup")                                       val v3check2: Boolean?,
    @SerializedName("medidaSegPorCtrolAccesoARed")                              val v3check3: Boolean?,
    @SerializedName("medidaSegOtraTIC")                                         val v3check4: Boolean?,
    @SerializedName("realizoVentaPorMedioElectr")                               val v4check: Boolean?,
    @SerializedName("realizoVentaPorPagWeb")                                    val v4check1a: Boolean?,
    @SerializedName("procentVentaPorPagWeb")                                    val v4check1aPorcent: String?,
    @SerializedName("realizoVentaPorCorreoElectron")                            val v4check1b: Boolean?,
    @SerializedName("procentVentaPorCorreoElectron")                            val v4check1bPorcent: String?,
    @SerializedName("realizoVentaPorRedSocial")                                 val v4check1c: Boolean?,
    @SerializedName("procentVentaPorRedSocial")                                 val v4check1cPorcent: String?,
    @SerializedName("realizoVentaPorAppPropia")                                 val v4check1d: Boolean?,
    @SerializedName("procentVentaPorAppPropia")                                 val v4check1dPorcent: String?,
    @SerializedName("realizoVentaPorOtroMedioElectron")                         val v4check1e: Boolean?,
    @SerializedName("procentVentaPorOtroMedioElectron")                         val v4check1ePorcent: String?,
    @SerializedName("descripVentaPorOtroMedioElectron")                         val v4check1eOther: String?,
    @SerializedName("totalEnVentasBienesYServicPorMedioElectron")               val v5txt: String?,
    @SerializedName("porcentEnVentPorHogar")                                    val v6porcent1: String?,
    @SerializedName("porcentEnVentPorEmpr")                                     val v6porcent2: String?,
    @SerializedName("porcentEnVentPorGob")                                      val v6porcent3: String?,
    @SerializedName("porcentEnVentOtro")                                        val v6porcent4: String?,
    @SerializedName("realizoPedidoOReservBienOServcPorMedioElectron")           val v7check: Boolean?,
    @SerializedName("totalCostoOGastoPedidoOReservBienOServcPorMedioElectron")  val v8txt: String?,
    @SerializedName("realizoEmprCostoOGastoEnTIC")                              val v9check: Boolean?,
    @SerializedName("totalCostoOGastoEnTIC")                                    val v9txt: String?,
    @SerializedName("porcentEnCostoOGastoHardware")                             val v10porcent1: String?,
    @SerializedName("porcentEnCostoOGastoSoftware")                             val v10porcent2: String?,
    @SerializedName("porcentEnCostoOGastoConsultoriaTIC")                       val v10porcent3: String?,
    @SerializedName("porcentEnCostoOGastoOtro")                                 val v10porcent4: String?,
    @SerializedName("observaciones")                                            var observaciones: String?,
    @SerializedName("numControl")                                               val numControl: Int?)

data class ModelCondicion(
    @SerializedName("id")               val id: String?,
    @SerializedName("numControl")       val ncontrol: String?,
    @SerializedName("codCondicion")     val idcondi: String?,
    @SerializedName("razonSocial")      val newRazon: String?,
    @SerializedName("otraNombre")       val otra: String?,
    @SerializedName("descNumControl")   val newNcontrol: String?,
    @SerializedName("especifique")      val especifique: String?
)

data class ModelForm(
    @SerializedName("numControl")                           val ncontrol: String?,
    @SerializedName("observaciones")                        val obs: String?,
    @SerializedName("condicion")                            val cond: String?,
    @SerializedName("actualizado")                          val act: Boolean?,
    @SerializedName("revisado")                             val rev: Boolean?,
    @SerializedName("tieneInconsistencias")                 val tieneIncon: Boolean?,
    @SerializedName("fechaCreacion")                        val dateCreate: String?,
    @SerializedName("fechaModificacion")                    val dateMod: String?,
    @SerializedName("fechaModificacionSup")                 val dateModSup: String?,
    @SerializedName("modificadoPorSup")                     val modSup: String?,
    @SerializedName("creador")                              val creator: String?,
    @SerializedName("modificador")                          val mod: String?,

    @SerializedName("condicionEmpadronamiento")             val condicion: ModelCondicion?,
    @SerializedName("cap1Localizacion")                         val cap1: ModelCap1?,
    @SerializedName("cap2DatosEmpresaInformante")               val cap2: ModelCap2?,
    @SerializedName("cap3Organizacion")                         val cap3: ModelCap3?,
    @SerializedName("cap4ActividadesEconomicas")                val cap4: ModelCap4?,
    @SerializedName("cap5VentasExportacionesRH")                val cap5: ModelCap5?,
    @SerializedName("cap6Innovacion")                           val cap6: ModelCap6?,
    @SerializedName("cap7ActividadesInnovativas")               val cap7: ModelCap7?,
    @SerializedName("cap8FuenteFinanciamiento")                 val cap8: ModelCap8?,
    @SerializedName("cap9Patentes")                             val cap9: ModelCap9?,
    @SerializedName("cap10PerspectivasInnovacionesFuturas")     val capx: ModelCap10?,
    @SerializedName("comercioElectronico")                      val capMod: ModelMod?, )


data class ModelListSuper(
    @SerializedName("numControl") val ncontrol: Int?,
    @SerializedName("condicion") val condicionID: String?,
    @SerializedName("condicionNombre") val condicion: String?,
    @SerializedName("prov") val provID: String?,
    @SerializedName("provNombre") val prov: String?,
    @SerializedName("dist") val distID: String?,
    @SerializedName("distNombre") val dist: String?,
    @SerializedName("correg") val correID: String?,
    @SerializedName("corregNombre") val corre: String?,
    @SerializedName("nombComerc") val nameComer: String?,
    @SerializedName("razSocial") val razon: String?,
    @SerializedName("ruc") val ruc: String?,
    @SerializedName("fechaModificacion") val datemod: String?,
    @SerializedName("encuestador") val encuesyador: String?
)

data class ModelFormGet(
    val code: Int?,
    val resp: String?,
    val body: ModelForm?)


//data class SuperForms(
//
//)


data class ModelFormsGetSuper(
    val code: Int?,
    val resp: String?,
    val body: List<ModelListSuper>?)





data class ModelFormsGetUser(
    val code: Int?,
    val resp: String?,
    val body: List<ModelForm>?)

data class ModelFormSend(
    val code: String,
    val server: String?,
    val body: Any?)

data class ModelResponse(
    @SerializedName("numControl")       val ncontrol: Int?,
    @SerializedName("inconsistente")    val poseeIncon: String?,
    @SerializedName("inconsistencias")  val inconsistencias: Array<String>?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ModelResponse

        if (ncontrol != other.ncontrol) return false
        if (poseeIncon != other.poseeIncon) return false
        if (inconsistencias != null) {
            if (other.inconsistencias == null) return false
            if (!inconsistencias.contentEquals(other.inconsistencias)) return false
        } else if (other.inconsistencias != null) return false

        return true
    }
    override fun hashCode(): Int {
        var result = ncontrol ?: 0
        result = 31 * result + (poseeIncon?.hashCode() ?: 0)
        result = 31 * result + (inconsistencias?.contentHashCode() ?: 0)
        return result
    }
}
