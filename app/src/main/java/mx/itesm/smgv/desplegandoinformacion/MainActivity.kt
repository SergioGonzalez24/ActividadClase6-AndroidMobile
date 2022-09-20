package mx.itesm.smgv.desplegandoinformacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.smgv.desplegandoinformacion.databinding.ActivityMainBinding
import mx.itesm.smgv.desplegandoinformacion.model.Pais
import mx.itesm.smgv.desplegandoinformacion.view.AdaptadorPais
import mx.itesm.smgv.desplegandoinformacion.viewmodel.ListaPaisesVM

class MainActivity : AppCompatActivity() {
    
    // binding
    private lateinit var binding: ActivityMainBinding

    // Viewmodel
    private val viewmodel: ListaPaisesVM by viewModels()

    // Fuente de datos del RecyclerView
    var adaptadorPais: AdaptadorPais? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarRV()
        configurarObservables()
    }

    private fun configurarObservables() {
        viewmodel.listaPaises.observe(this) { lista ->
            val arrPaises = lista.toTypedArray()
            adaptadorPais?.arrPaises = arrPaises
            adaptadorPais?.notifyDataSetChanged() // Recargue todo
        }
    }

    override fun onStart() {
        super.onStart()
        viewmodel.descargarDatosPaises()
    }

    private fun configurarRV() {
        val arrPaises = arrayOf( Pais("México", 5300),
            Pais("Argentina", 25234) )
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        binding.rvPaises.layoutManager = layout

        adaptadorPais = AdaptadorPais(this, arrPaises)
        binding.rvPaises.adapter = adaptadorPais

        val divisor = DividerItemDecoration(this, layout.orientation)
        binding.rvPaises.addItemDecoration(divisor)
    }
}

