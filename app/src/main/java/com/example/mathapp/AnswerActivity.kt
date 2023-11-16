package com.example.mathapp

import QuestionAnswer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class AnswerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val button = findViewById<Button>(R.id.back_btn)

        button.setOnClickListener {
            finish()
        }

        setDataObjects()
    }

    fun setDataObjects() {

        val question1 = findViewById<TextView>(R.id.q1View)
        val question2 = findViewById<TextView>(R.id.q2View)
        val question3 = findViewById<TextView>(R.id.q3View)
        val question4 = findViewById<TextView>(R.id.q4View)
        val question5 = findViewById<TextView>(R.id.q5View)
        val correctA1 = findViewById<TextView>(R.id.correctAnswer1)
        val correctA2 = findViewById<TextView>(R.id.correctAnswer2)
        val correctA3 = findViewById<TextView>(R.id.correctAnswer3)
        val correctA4 = findViewById<TextView>(R.id.correctAnswer4)
        val correctA5 = findViewById<TextView>(R.id.correctAnswer5)
        val userAnswer1 = findViewById<TextView>(R.id.userA1)
        val userAnswer2 = findViewById<TextView>(R.id.userA2)
        val userAnswer3 = findViewById<TextView>(R.id.userA3)
        val userAnswer4 = findViewById<TextView>(R.id.userA4)
        val userAnswer5 = findViewById<TextView>(R.id.userA5)

        val questionsAndAnswersList: ArrayList<QuestionAnswer>? =
            intent.getParcelableArrayListExtra("questionsAndAnswersList")

        if (questionsAndAnswersList != null && questionsAndAnswersList.size >= 5) {
            question1.text = questionsAndAnswersList[0].question
            question2.text = questionsAndAnswersList[1].question
            question3.text = questionsAndAnswersList[2].question
            question4.text = questionsAndAnswersList[3].question
            question5.text = questionsAndAnswersList[4].question
            userAnswer1.text = questionsAndAnswersList[0].userAnswer.toString()
            userAnswer2.text = questionsAndAnswersList[1].userAnswer.toString()
            userAnswer3.text = questionsAndAnswersList[2].userAnswer.toString()
            userAnswer4.text = questionsAndAnswersList[3].userAnswer.toString()
            userAnswer5.text = questionsAndAnswersList[4].userAnswer.toString()
            correctA1.text = questionsAndAnswersList[0].correctAnswer.toString()
            correctA2.text = questionsAndAnswersList[1].correctAnswer.toString()
            correctA3.text = questionsAndAnswersList[2].correctAnswer.toString()
            correctA4.text = questionsAndAnswersList[3].correctAnswer.toString()
            correctA5.text = questionsAndAnswersList[4].correctAnswer.toString()
        }

        if (questionsAndAnswersList != null) {
            questionsAndAnswersList.forEachIndexed { index, questionAnswer ->
                val userAnswerTextView = findUserAnswerTextView(index)

                if (questionAnswer.userAnswer == questionAnswer.correctAnswer) {
                    userAnswerTextView.setTextColor(ContextCompat.getColor(this, R.color.green))
                } else {
                    userAnswerTextView.setTextColor(ContextCompat.getColor(this, R.color.red))
                }
            }
        }
    }

    private fun findUserAnswerTextView(index: Int): TextView {
        return when (index) {
            0 -> findViewById(R.id.userA1)
            1 -> findViewById(R.id.userA2)
            2 -> findViewById(R.id.userA3)
            3 -> findViewById(R.id.userA4)
            4 -> findViewById(R.id.userA5)
            else -> throw IllegalArgumentException("Invalid index")
        }
    }
}