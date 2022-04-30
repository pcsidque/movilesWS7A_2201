package com.example.app_sem06_2_sharedpref

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.SharedPreferences
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = SharedPreferences(this)

        val btSave = findViewById<Button>(R.id.btSave)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val btRetrieve = findViewById<Button>(R.id.btRetrieve)
        val tvRetrieve = findViewById<TextView>(R.id.tvRetrieve)

        btSave.setOnClickListener {
            val name = etName.text.toString()
            sharedPreferences.save("name", name)

            Toast.makeText(this, "Data almacenada", Toast.LENGTH_LONG).show()
        }

        btRetrieve.setOnClickListener {
            tvRetrieve.setText(sharedPreferences.getValue("name"))
        }
    }
}