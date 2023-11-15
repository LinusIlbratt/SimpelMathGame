package com.example.mathapp

import QuestionAnswer
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), NumberPadDisplay {

    private lateinit var mathQuestionView : TextView
    private lateinit var additionQuestion : Button
    private lateinit var subtractionQuestion : Button
    private lateinit var multiplicationQuestion : Button
    private lateinit var divisionQuestion : Button

    private var correctAnswer = 0
    private var answeredQuestions = 0
    private var totalQuestions = 5
    private var selectedMathOperation: String? = null

    private val questionsAndAnswers = mutableListOf<QuestionAnswer>()

    override fun onNumberPressed(number: String) {
        val numDisplay = findViewById<TextView>(R.id.numDisplay)
        numDisplay.text = "${numDisplay.text}$number"
    }

    override fun onDeletePressed() {
        val numDisplay = findViewById<TextView>(R.id.numDisplay)
        val text = numDisplay.text.toString()
        if (text.isNotEmpty()) {
            numDisplay.text = text.substring(0, text.length - 1)
        }
    }

    override fun clearDisplay() {
        val numDisplay = findViewById<TextView>(R.id.numDisplay)
        numDisplay.text = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mathQuestionView = findViewById(R.id.mathQuestionView)
        additionQuestion = findViewById(R.id.btn_addition)
        subtractionQuestion = findViewById(R.id.btn_subtraction)
        multiplicationQuestion = findViewById(R.id.btn_multiplication)
        divisionQuestion = findViewById(R.id.btn_division)

        // Adding the NumberPadFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.numberPadContainer, NumberPadFragment())
            .commit()

        additionQuestion.setOnClickListener{
            selectedMathOperation = "addition"
            answeredQuestions = 0
            setNewQuestion()
        }

        subtractionQuestion.setOnClickListener{
            selectedMathOperation = "subtraction"
            answeredQuestions = 0
            setNewQuestion()
        }

        multiplicationQuestion.setOnClickListener{
            selectedMathOperation = "multiplication"
            answeredQuestions = 0
            setNewQuestion()
        }

        divisionQuestion.setOnClickListener {
            selectedMathOperation = "division"
            answeredQuestions = 0
            setNewQuestion()
        }


    }

    override fun onResume() {
        super.onResume()
        clearDisplay()
        questionsAndAnswers.clear()
        answeredQuestions = 0
    }

    fun handleAnswer() {
        val userAnswerText = findViewById<TextView>(R.id.numDisplay).text.toString()
        val userAnswer = userAnswerText.toIntOrNull() ?: 0
        val questionText = mathQuestionView.text.toString()
        val isAnswerCorrect = checkAnswer()

        val questionAnswer = QuestionAnswer(
            question = questionText,
            userAnswer = userAnswer,
            correctAnswer = correctAnswer,
            isCorrect = isAnswerCorrect
        )
        questionsAndAnswers.add(questionAnswer)

        answeredQuestions++

        if (answeredQuestions >= totalQuestions) {
            val intent = Intent(this, AnswerActivity::class.java)
            intent.putParcelableArrayListExtra("questionsAndAnswersList", ArrayList(questionsAndAnswers))
            startActivity(intent)
        } else {
            setNewQuestion()
            clearDisplay()
        }
    }

    fun checkAnswer() : Boolean {
        // Get text from TextView that shows the users answer
        val numDisplay = findViewById<TextView>(R.id.numDisplay)
        val answerText = numDisplay.text.toString()
        val answer = answerText.toIntOrNull()

        return answer == correctAnswer

    }

    fun setNewAdditionQuestion() {
        val firstNumber = (1..10).random()
        val secondNumber = (1..10).random()

        correctAnswer = firstNumber + secondNumber
        mathQuestionView.text = "$firstNumber + $secondNumber"
    }

    fun setNewSubtractionQuestion() {
        var firstNumber = (1..10).random()
        var secondNumber = (1..10).random()

        while (firstNumber <= secondNumber) {
            firstNumber = (1..10).random()
            secondNumber = (1..10).random()
        }

        correctAnswer = firstNumber - secondNumber
        mathQuestionView.text = "$firstNumber \u2212 $secondNumber"
    }

    fun setNewMultiplicationQuestion() {
        val firstNumber = (1..10).random()
        val secondNumber = (1..10).random()

        correctAnswer = firstNumber * secondNumber
        mathQuestionView.text = "$firstNumber \u00D7 $secondNumber"
    }

    fun setNewDivisionQuestion() {
        val divisionSymbol = Html.fromHtml("&#247;", Html.FROM_HTML_MODE_LEGACY)
        var firstNumber = (1..10).random()
        var secondNumber = (1..10).random()

        while (firstNumber % secondNumber != 0) {
            firstNumber = (1..10).random()
            secondNumber = (1..10).random()
        }

        correctAnswer = firstNumber / secondNumber
        mathQuestionView.text = "$firstNumber $divisionSymbol $secondNumber"
    }

    fun setNewQuestion() {
        when (selectedMathOperation) {
            "addition" -> setNewAdditionQuestion()
            "subtraction" -> setNewSubtractionQuestion()
            "multiplication" -> setNewMultiplicationQuestion()
            "division" -> setNewDivisionQuestion()
            else -> {}
        }
    }
}