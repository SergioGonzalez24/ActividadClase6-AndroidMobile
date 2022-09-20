package mx.itesm.smgv.desplegandoinformacion.model

import retrofit2.Call
import retrofit2.http.GET

interface ServicioCovidAPI {
    @GET("v3/covid-19/countries")
    fun descargarDatosPaises(): Call<List<Pais>> //Utilizar retrofit 2
}
