package fr.isen.mael.androiderestaurant

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CategoriesActivity: AppCompatActivity(){
    var categories :String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categories_layout)
        this.categories = intent.getStringExtra("title");
        var helloTextView = findViewById<TextView>(R.id.layout);
        helloTextView.text = this.categories;
    }
}