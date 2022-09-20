package mx.itesm.smgv.desplegandoinformacion.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.itesm.smgv.desplegandoinformacion.R
import mx.itesm.smgv.desplegandoinformacion.model.Pais

/**
 * @author Sergio Gonzalez
 * Fuente de datos para el recyclerview
 */

class AdaptadorPais(private val contexto: Context, var arrPaises: Array<Pais>) :
    RecyclerView.Adapter<AdaptadorPais.RenglonPais>() {

    // Se llama cada vez que se va a poblar un renglon
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RenglonPais {
        val vista = LayoutInflater.from(contexto).inflate(R.layout.renglon_pais, parent, false)
        return RenglonPais(vista)
    }

    // Para poblar un renglón (poner los datos en el renglón 'position')
    override fun onBindViewHolder(holder: RenglonPais, position: Int) {
        val pais = arrPaises[position]
        holder.set(pais)
    }

    // El número de renglones que tendrá en recyclerview
    override fun getItemCount(): Int {
        return arrPaises.size
    }

    class RenglonPais (var renglonPais: View) : RecyclerView.ViewHolder(renglonPais) {
        fun set(pais: Pais) {
            renglonPais.findViewById<TextView>(R.id.tvPais).text = pais.nombre
            renglonPais.findViewById<TextView>(R.id.tvCasos).text = "${pais.casos}"
            renglonPais.findViewById<ImageView>(R.id.imgBandera).setImageResource(R.drawable.banderamex)
            println(pais.info["flag"])
            val url = pais.info["flag"]
            val imgBandera = renglonPais.findViewById<ImageView>(R.id.imgBandera)
            Glide.with(renglonPais.context).load(url).into(imgBandera)
        }
    }
}