package mx.itesm.smgv.desplegandoinformacion.model

import com.google.gson.annotations.SerializedName

/**
 *  @author Sergio Gonzalez
 */
data class Pais(

    @SerializedName("country")
    val nombre:String,
    @SerializedName("cases")
    val casos:Int,
    @SerializedName("countryInfo")
    val info: Map<String, String> = mapOf(), // Diccionarios son mapas
    val idImagen: Int=0,
    val urlImagen:String=""
)

