package mx.itesm.smgv.desplegandoinformacion.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.smgv.desplegandoinformacion.model.Pais
import mx.itesm.smgv.desplegandoinformacion.model.ServicioCovidAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListaPaisesVM : ViewModel() {
    // Modelo
    private val retrofit by lazy {  // El objeto retrofit para instanciar el objeto que se conecta a la red y accede a los servicios definidos
        Retrofit.Builder()
            .baseUrl("https://disease.sh/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Instancia que crea el objeto que realiza la descarga
    private val servicioCovidAPI by lazy {
        retrofit.create(ServicioCovidAPI::class.java)
    }

    // Livedata (observables)
    val listaPaises = MutableLiveData<List<Pais>>()

    fun descargarDatosPaises() {
        val call = servicioCovidAPI.descargarDatosPaises()    // Crea un objeto para descargar
        call.enqueue(object: Callback<List<Pais>> {           // DESCARGA ASÍNCRONA
            override fun onResponse(call: Call<List<Pais>>, response: Response<List<Pais>>) {
                if (response.isSuccessful) {
                    println("Lista de paises: ${response.body()}")
                    // Avisar a la vista (adaptador) que hay datos nuevos
                    listaPaises.value = response.body()
                } else {
                    println("Error en los datos: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Pais>>, t: Throwable) {
                println("Error en la descarga: ${t.localizedMessage}")
            }

        })
    }
}