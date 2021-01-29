package fr.isen.mael.androiderestaurant.models

import java.io.Serializable

data class Data(
    val name_fr: String,
    val name_en: String,
    var items: List<Item>
) : Serializable
