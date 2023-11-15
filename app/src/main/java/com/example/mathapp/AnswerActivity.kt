package com.example.mathapp

import QuestionAnswer
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

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
            val questionTextView = createStyledTextView("${questionAnswer.question}")
            val userAnswerTextView = createStyledTextView("Your answer:\n ${questionAnswer.userAnswer}")
            val correctAnswerTextView = createStyledTextView("Correct answer:\n ${questionAnswer.correctAnswer}")

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
            typeface = ResourcesCompat.getFont(context, R.font.roboto_black)
            setTextColor(ContextCompat.getColor(context, R.color.white))
            setPadding(16, 5, 16, 5)
            gravity = Gravity.CENTER
        }

    }
}