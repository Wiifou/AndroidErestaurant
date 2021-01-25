package fr.isen.mael.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this.applicationContext,"@Quit_main",Toast.LENGTH_SHORT)
    }

    fun callButton(view: View?){
        val mytoast = Toast.makeText(this.applicationContext,"@entrées",Toast.LENGTH_SHORT)
        mytoast.show()
        startActivity(
            Intent(applicationContext, Entrees_Activity::class.java)
        )
    }
    fun callButton2(view: View?){
        val mytoast = Toast.makeText(this.applicationContext,"@plats",Toast.LENGTH_SHORT)
        mytoast.show()
        startActivity(
            Intent(applicationContext, Plats_Activity::class.java)
        )
    }
    fun callButton3(view: View?){
        val mytoast = Toast.makeText(this.applicationContext,"@desserts",Toast.LENGTH_SHORT)
        mytoast.show()
        startActivity(
            Intent(applicationContext, Desserts_Activity::class.java)
        )
    }


}