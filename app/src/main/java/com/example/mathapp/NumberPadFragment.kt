package com.example.mathapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

interface NumberPadDisplay {
    fun onNumberPressed(number: String)
    fun onDeletePressed()
    fun clearDisplay()
}

class NumberPadFragment : Fragment() {

    private var numberPadDisplay: NumberPadDisplay? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NumberPadDisplay) {
            numberPadDisplay = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_number_pad, container, false)

        val numberButtonListener = View.OnClickListener { button ->
            val number = (button as Button).text.toString()
            Log.d("NumberPadFragment", "Knapp tryckt: $number")
            numberPadDisplay?.onNumberPressed(number)
        }


        view.findViewById<Button>(R.id.num0).setOnClickListener(numberButtonListener)
        view.findViewById<Button>(R.id.num1).setOnClickListener(numberButtonListener)
        view.findViewById<Button>(R.id.num2).setOnClickListener(numberButtonListener)
        view.findViewById<Button>(R.id.num3).setOnClickListener(numberButtonListener)
        view.findViewById<Button>(R.id.num4).setOnClickListener(numberButtonListener)
        view.findViewById<Button>(R.id.num5).setOnClickListener(numberButtonListener)
        view.findViewById<Button>(R.id.num6).setOnClickListener(numberButtonListener)
        view.findViewById<Button>(R.id.num7).setOnClickListener(numberButtonListener)
        view.findViewById<Button>(R.id.num8).setOnClickListener(numberButtonListener)
        view.findViewById<Button>(R.id.num9).setOnClickListener(numberButtonListener)

        view.findViewById<Button>(R.id.numDel).setOnClickListener {
            numberPadDisplay?.onDeletePressed()
        }

        view.findViewById<Button>(R.id.numSubmit).setOnClickListener {
            (activity as? MainActivity)?.handleAnswer()
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        numberPadDisplay = null
    }
}