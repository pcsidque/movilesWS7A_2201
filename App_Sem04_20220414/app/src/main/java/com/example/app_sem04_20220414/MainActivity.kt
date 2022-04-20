package com.example.app_sem04_20220414

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var questions: ArrayList<Question>
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadQuestions()
        setupViews()
    }

    private fun loadQuestions() {
        questions = ArrayList()
        var question = Question("¿Es Lima capital de Venezuela...?", false)
        questions.add(question)

        questions.add(Question("¿Es Lima capital de Colombia...?", false))
        questions.add(Question("¿Es Lima capital de Brasil...?", false))
        questions.add(Question("¿Es Santiago capital de Chile...?", true))
        questions.add(Question("¿Es Bs As capital de Colombia...?", false))
        questions.add(Question("¿Es Medellin capital de Colombia...?", false))
        questions.add(Question("¿Es Bogotá capital de Colombia...?", true))
        questions.add(Question("¿Es La Paz capital de Bolivia...?", true))
    }

    private fun setupViews() {
        val btYes = findViewById<Button>(R.id.btYes)
        val btNo = findViewById<Button>(R.id.btNo)
        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        val btNext = findViewById<Button>(R.id.btNext)

        tvQuestion.text = questions[position].sentence

        btYes.setOnClickListener {
            if (questions[position].answer == true)
            {
                Toast.makeText(this, "Correcto", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "Incorrecto", Toast.LENGTH_LONG).show()
            }
        }

        btNo.setOnClickListener {
            if (!questions[position].answer == true)
            {
                Toast.makeText(this, "Correcto", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "Incorrecto", Toast.LENGTH_LONG).show()
            }
        }

        btNext.setOnClickListener {
            position++
            tvQuestion.text = questions[position].sentence
        }
    }
}