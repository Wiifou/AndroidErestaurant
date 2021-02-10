package fr.isen.mael.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.mael.androiderestaurant.adapter.PlatsAdaptater
import fr.isen.mael.androiderestaurant.models.Item
import fr.isen.mael.androiderestaurant.models.Plats
import org.json.JSONObject


class PlatsListActivity: AppCompatActivity(){

    private var title:TextView? = null
    private val url = "http://test.api.catering.bluecodegames.com/menu"
    private var CAT:Int = 0
    private lateinit var Recycler:Item

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item -> {
                startActivity(
                        Intent(applicationContext, PanierActivity::class.java)
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val bundle = intent.extras
        title = findViewById<View>(R.id.title) as TextView

        if (bundle != null) {
            title!!.text = bundle.getString("title")
            this.CAT = bundle.getInt("cat")
        }

        volleyGet()


    }

    private fun volleyGet() {

        val parameter = JSONObject()
        parameter.put("id_shop", "1")
        val requestQueue = Volley.newRequestQueue(this)
        val parser = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()

        val jsonObjectRequest =
                JsonObjectRequest(Request.Method.POST, url , parameter, Response.Listener<JSONObject> { response ->
                    val result = parser.fromJson(response["data"].toString(), Array<Item>::class.java)
                    this.Recycler = result[this.CAT];
                    //System.out.println(this.CAT)

                    val platsRecycler = findViewById<RecyclerView>(R.id.RecyclerView)
                    platsRecycler.adapter = PlatsAdaptater(Recycler.items, applicationContext)
                    platsRecycler.layoutManager = LinearLayoutManager(this)

                    platsRecycler.isVisible = true
                },
                        Response.ErrorListener { error -> System.err.println( "Error: ${error.message}")
                            Toast.makeText(applicationContext,"Error: ${error.message}",Toast.LENGTH_LONG ).show()
                        })

        requestQueue.add(jsonObjectRequest)

    }

}