package fr.isen.mael.androiderestaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonArray
import fr.isen.mael.androiderestaurant.adapter.ViewPagerAdaptater
import fr.isen.mael.androiderestaurant.models.Commande
import fr.isen.mael.androiderestaurant.models.Plats

import java.io.FileNotFoundException

class CategoriesActivity: AppCompatActivity() {
    var PlatsDescription: TextView? = null
    var NomPlats: TextView? = null
    var Prix: TextView? = null
    var PlatsImage: ViewPager? = null
    var key: String? = ""
    var imageUrl: String? = ""
    var nb_quantity:Int = 1
    var Quantity : TextView? = null
    lateinit var order : Commande
    var fileName: String = "Order.json"


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.menu_item -> {
                startActivity(
                        Intent(applicationContext, PanierActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        NomPlats = findViewById<View>(R.id.nom_plat) as TextView
        Prix = findViewById<View>(R.id.prix) as TextView
        PlatsDescription = findViewById<View>(R.id.desc_plat) as TextView
        PlatsImage = findViewById<View>(R.id.img_plat) as ViewPager
        Quantity = findViewById<View>(R.id.quantite) as TextView

        val mBundle = intent.extras
        if (mBundle != null) {
            mBundle.getSerializable("Desc").let { serializedItem ->
                val data = serializedItem as Plats

                val ingredient = data.ingredients
                val pr: StringBuilder = StringBuilder("")
                for (content in ingredient) {
                    pr.append(" " + content.name_fr)
                    pr.append("\n")
                    PlatsDescription!!.text = pr.toString()


                    val phot = data.images
                    // Initializing the ViewPagerAdapter
                    val mViewPagerAdapter = ViewPagerAdaptater(applicationContext, phot)
                    PlatsImage!!.adapter = mViewPagerAdapter

                    order = Commande(data, 0, nb_quantity, data.prices[0].price)
                }


                key = mBundle.getString("keyValue")
                imageUrl = mBundle.getString("Image")
                NomPlats!!.text = mBundle.getString("RecipeName")
                Prix!!.text = mBundle.getString("price")


            }
        }

        fun devise(str: String): String {
            val pr: StringBuilder = java.lang.StringBuilder()
            pr.append(str)
            pr.append(" €")
            return pr.toString()
        }

        fun quantityUp(view: View?) {
            nb_quantity += 1
            Quantity!!.text = nb_quantity.toString()

            Prix!!.text = devise((order.price * nb_quantity).toString())
            order.quantity = nb_quantity

        }

        fun quantityDown(view: View?) {
            if (nb_quantity > 1) {
                nb_quantity -= 1
                Prix!!.text = devise((order.price * nb_quantity).toString())

            } else {
                Prix!!.text = devise(order.price.toString())

            }
            order.quantity = nb_quantity
            Quantity!!.text = nb_quantity.toString()

        }





        fun SauvegardeCmd(order: Commande) {

            try {
                applicationContext.openFileInput(fileName).use { inputStream ->
                    inputStream.bufferedReader().use {
                        val actual = Gson().fromJson(it.readText(), Array<Commande>::class.java).toMutableList()

                        this.order.idOrder += 1
                        order.idOrder += 1

                        actual.add(order)

                        val newOne = Gson().toJson(actual)
                        applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
                            outputStream.write(newOne.toString().toByteArray())

                        }
                    }
                }
            } catch (e: FileNotFoundException) {
                val orders = JsonArray()
                val parsed = Gson().toJsonTree(order)
                orders.add(Gson().toJsonTree(parsed))

                applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                    it.write(orders.toString().toByteArray())
                }
            }
        }

        fun btnOrder(view: View?) {
            SauvegardeCmd(this.order)

            val message: StringBuilder = java.lang.StringBuilder()
            message.append(order.quantity)
            message.append(" x ")
            message.append(order.item.name_fr)

            val snackbar: Snackbar = Snackbar
                    .make(view!!, message.toString(), Snackbar.LENGTH_LONG)
            snackbar.show()
        }


    }
}