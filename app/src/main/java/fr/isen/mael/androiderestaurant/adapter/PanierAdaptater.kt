package fr.isen.mael.androiderestaurant.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.mael.androiderestaurant.R
import fr.isen.mael.androiderestaurant.models.Commande

class PanierAdaptater (
        private val data: MutableList<Commande>,
        private val context: Context
) : RecyclerView.Adapter<PanierAdaptater.ViewHolder>() {

    inner class ViewHolder(dataView: View) : RecyclerView.ViewHolder(dataView) {
        val titre: TextView = dataView.findViewById(R.id.plats_titre)
        val desc: TextView = dataView.findViewById(R.id.plats_desc)
        val prix: TextView = dataView.findViewById(R.id.plats_prix)
        val image: ImageView = dataView.findViewById(R.id.plats_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanierAdaptater.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val contactView = inflater.inflate(R.layout.data_layout, parent, false)

        return ViewHolder(contactView)
    }


    override fun onBindViewHolder(viewHolder: PanierAdaptater.ViewHolder, position: Int) {

        val data: Commande = data[position]

        val desc = viewHolder.desc
        desc.text = data.quantity.toString()

        val price = viewHolder.prix
        val pr: java.lang.StringBuilder = StringBuilder((data.price * data.quantity).toString())
        pr.append(" €")
        price.text = pr

        val textView = viewHolder.titre
        textView.text = data.item.name_fr

        val img = viewHolder.image
        val picasso = Picasso.get()
        picasso.load(data.item.images[0]).into(img)

    }

    override fun getItemCount(): Int {
        return data.size
    }
}
