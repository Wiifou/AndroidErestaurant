package fr.isen.mael.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun callButtonEntree(view: View?){
        val mytoast = Toast.makeText(this.applicationContext,"@entrées",Toast.LENGTH_SHORT)
        mytoast.show()
        startActivity(
            Intent(applicationContext, CategoriesActivity::class.java).putExtra("title","Entrées")
        )
    }
    fun callButtonPlat(view: View?){
        val mytoast = Toast.makeText(this.applicationContext,"@plats",Toast.LENGTH_SHORT)
        mytoast.show()
        startActivity(
            Intent(applicationContext, CategoriesActivity::class.java).putExtra("title","Plats")
        )
    }
    fun callButtonDessert(view: View?){
        val mytoast = Toast.makeText(this.applicationContext,"@desserts",Toast.LENGTH_SHORT)
        mytoast.show()
        startActivity(
            Intent(applicationContext, CategoriesActivity::class.java).putExtra("title","Desserts")
        )
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this.applicationContext,"@Quit_main",Toast.LENGTH_SHORT)
    }


}