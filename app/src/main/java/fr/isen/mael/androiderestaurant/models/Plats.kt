package fr.isen.mael.androiderestaurant.models

import java.io.Serializable

data class Plats(
        val prices: List<Price>,
        val ingredients: List<Ingredient>,
        val name_fr: String,
        val name_en: String,
        val categ_name_fr: String,
        val categ_name_en: String,
        val id: Long,
        val id_category: Long,
        val images: List<String>
) : Serializable
