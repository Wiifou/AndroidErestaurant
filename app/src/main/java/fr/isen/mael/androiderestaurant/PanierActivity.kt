package fr.isen.mael.androiderestaurant


        import android.os.Bundle
                import android.widget.TextView
                import android.widget.Toast
                import androidx.appcompat.app.AppCompatActivity
                import androidx.core.view.isVisible
                import androidx.recyclerview.widget.LinearLayoutManager
                import androidx.recyclerview.widget.RecyclerView
                import com.google.gson.Gson
                import fr.isen.mael.androiderestaurant.adapter.PanierAdaptater
                import fr.isen.mael.androiderestaurant.adapter.PlatsAdaptater
                import fr.isen.mael.androiderestaurant.models.Commande
                import fr.isen.mael.androiderestaurant.models.Item
                import java.io.FileNotFoundException

        class PanierActivity : AppCompatActivity(){
            var panier : MutableList<Commande>? = null

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_panier)
                read_file()
            }

            fun read_file(){
                try {
                    val parser = Gson()
                    applicationContext.openFileInput("Order.json").use {inputStream ->
                        inputStream.bufferedReader().use {
                            panier = parser.fromJson(it.readText(), Array<Commande>::class.java).toMutableList()
                        }
                    }
                    val platsRecycler = findViewById<RecyclerView>(R.id.RecyclerViewPanier)
                    platsRecycler.adapter = PanierAdaptater(panier!!, applicationContext)
                    platsRecycler.layoutManager = LinearLayoutManager(this)
                    platsRecycler.isVisible = true


                }catch (e : FileNotFoundException){
                    Toast.makeText(applicationContext,"No cart found", Toast.LENGTH_LONG).show()
                    val title = findViewById<TextView>(R.id.titre_panier)
                    title.text = "No cart found"
                }
            }
        }

