package fr.isen.mael.androiderestaurant


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import fr.isen.mael.androiderestaurant.models.Plats

class HomeActivity : AppCompatActivity() {

    var myFoodList: List<Plats>? = null

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
        setContentView(R.layout.activity_main)

    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this.applicationContext, "Destroy main", Toast.LENGTH_SHORT).show()
    }



    fun btnMenu(view: View?) {
        if (R.id.entree == view?.id){
            Toast.makeText(this.applicationContext, "Entrées", Toast.LENGTH_SHORT).show()
            changePage("Entrées",0)
        }else if (R.id.plat == view?.id){
            Toast.makeText(this.applicationContext, "Plats", Toast.LENGTH_SHORT).show()
            changePage("Plats",1)
        }else if (R.id.dessert == view?.id){
            Toast.makeText(this.applicationContext, "Dessert", Toast.LENGTH_SHORT).show()
            changePage("Dessert",2)
        }
    }


    fun changePage(string: String, category :Int) {
        startActivity(
            Intent(applicationContext, PlatsListActivity::class.java)
                .putExtra("title",string )
                .putExtra("cat", category)
        )
    }
   private fun countPastille(menuItem: MenuItem){
        val shared = this.getSharedPreferences(getString(R.string.Basket), Context.MODE_PRIVATE)
        var num :Int = 0
        if (shared.contains("qt")){
            num = shared.getInt("qt",0)
        }

        val countText = menuItem.actionView.findViewById<TextView>(R.id.numberCart)
        countText.text = num.toString()
    }


}