package fr.isen.mael.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.mael.androiderestaurant.models.Item

class CategoriesAdaptater(private val meal : List<Item>) : RecyclerView.Adapter<CategoriesAdaptater.CategoriesHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        return CategoriesHolder(LayoutInflater.from(parent.context).inflate(R.layout.categories_layout, parent,false))


    }

    override fun getItemCount(): Int = meal.size

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.title.text = meal[position].nom
    }

    class CategoriesHolder(view : View) : RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.layout)
    }


}