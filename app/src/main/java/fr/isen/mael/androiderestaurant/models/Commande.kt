package fr.isen.mael.androiderestaurant.models

import java.io.Serializable

data class Commande(
    val item : Plats,
    var idOrder : Int,
    var quantity : Int,
    var price : Double
): Serializable