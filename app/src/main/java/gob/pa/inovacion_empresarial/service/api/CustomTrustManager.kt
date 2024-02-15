package gob.pa.inovacion_empresarial.service.api

import javax.net.ssl.X509TrustManager

class CustomTrustManager : X509TrustManager { //--- Invalidar certificado x
    override fun checkClientTrusted(
        chain: Array<out java.security.cert.X509Certificate>?,
        authType: String?
    ) {/* No hacemos ninguna verificación específica del cliente*/ }
    override fun checkServerTrusted(
        chain: Array<out java.security.cert.X509Certificate>?,
        authType: String?
    ) { /* No verificamos el servidor, aceptamos todos los certificados*/ }

    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
        return emptyArray()
    }
}