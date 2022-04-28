package com.example.app_sem06_1_networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btJoke = findViewById<Button>(R.id.btJoke)

        btJoke.setOnClickListener {
            loadJoke()
        }
    }

    private fun loadJoke() {
        val tvJoke = findViewById<TextView>(R.id.tvJoke)
        //tvJoke.text = "Broma!!!"

        //creo una instancia de RETROFIT
        val retrofit = Retrofit.Builder()
            .baseUrl("https://geek-jokes.sameerkumar.website/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //creo una instancia de la interfaz
        val jokeService: JokeService
        jokeService = retrofit.create(JokeService::class.java)

        //creo una variable y le asigno al servicio
        val request = jokeService.getJoke("json")

        request.enqueue(object : Callback<Joke>{
            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                if (response.isSuccessful){
                    tvJoke.text = response.body()!!.joke
                }
            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}