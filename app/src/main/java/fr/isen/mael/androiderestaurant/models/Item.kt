package fr.isen.mael.androiderestaurant.models

import java.io.Serializable

data class Item(
        var items: List<Plats>,
        val name_fr: String,
        val name_en: String
):Serializable