package com.example.mathapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AnswerActivity : AppCompatActivity() {

    lateinit var resultView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        resultView = findViewById(R.id.resultView)
        val button = findViewById<Button>(R.id.back_btn)

        button.setOnClickListener{
            finish()
        }

        val questionsAndAnswersList = intent.getStringArrayListExtra("questionsAndAnswersList")

        val displayText = StringBuilder()
        questionsAndAnswersList?.forEach { questionAnswer ->
            displayText.append(questionAnswer).append("\n\n")
        }

        resultView.text = displayText.toString()

    }
}