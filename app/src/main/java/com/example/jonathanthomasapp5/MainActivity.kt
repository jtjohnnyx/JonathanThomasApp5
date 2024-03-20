package com.example.jonathanthomasapp5

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private var imageID: Int = R.drawable.defaultimage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("Prefs", Context.MODE_PRIVATE)

        // Retrieve data from SharedPreferences
        val (text, newImageID) = loadData()
        imageID = newImageID
        Log.d("MMAINLOAD", text)
        Log.d("MMAINLOAD", imageID.toString())

        val button = findViewById<Button>(R.id.button)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val editText = findViewById<EditText>(R.id.editText)

        editText.setText(text)
        imageView.setImageResource(imageID)

        button.setOnClickListener() {
            val images = arrayOf(
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
            )
            imageID = images.random()
            imageView.setImageResource(imageID)
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        val text = findViewById<EditText>(R.id.editText).text.toString()
        saveData(text, imageID)
        Log.d("MMAINSAVE", text)
        Log.d("MMAINSAVE", imageID.toString())
    }

    private fun loadData(): Pair<String, Int> {
        val text = sharedPreferences.getString("Text", "") ?: ""
        val imageID = sharedPreferences.getInt("ImageID", R.drawable.defaultimage)
        return Pair(text, imageID)
    }

    private fun saveData(text : String, imageID : Int) {
        val editor = sharedPreferences.edit()
        editor.putString("Text", text)
        editor.putInt("ImageID", imageID)
        editor.apply()
    }
}