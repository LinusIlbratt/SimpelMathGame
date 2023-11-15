package com.example.mathapp

import QuestionAnswer
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class AnswerActivity : AppCompatActivity() {

    lateinit var resultView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val button = findViewById<Button>(R.id.back_btn)

        button.setOnClickListener{
            finish()
        }

        val answerContainer = findViewById<LinearLayout>(R.id.answerContainer)
        val questionsAndAnswersList: ArrayList<QuestionAnswer>? = intent.getParcelableArrayListExtra("questionsAndAnswersList")

        questionsAndAnswersList?.forEach { questionAnswer ->
            // Skapa en TextView för varje del av QuestionAnswer
            val questionTextView = createStyledTextView("Fråga: ${questionAnswer.question}")
            val userAnswerTextView = createStyledTextView("Ditt Svar: ${questionAnswer.userAnswer}")
            val correctAnswerTextView = createStyledTextView("Korrekt Svar: ${questionAnswer.correctAnswer}")

            // Sätt textfärgen baserat på om svaret är rätt eller fel
            userAnswerTextView.setTextColor(if (questionAnswer.isCorrect) Color.GREEN else Color.RED)

            // Lägg till TextViews i LinearLayout
            answerContainer.addView(questionTextView)
            answerContainer.addView(userAnswerTextView)
            answerContainer.addView(correctAnswerTextView)
        }


    }

    private fun createStyledTextView(text: String): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = 20f
        }

    }
}