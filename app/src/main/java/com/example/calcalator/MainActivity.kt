package com.example.calcalator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentInput: String = ""
    private var currentOperator: String? = ""
    private var firstOperand: Double? = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.num)


        val numberButtons = arrayOf(
            R.id.n1, R.id.n2, R.id.n3, R.id.n4,
            R.id.n5, R.id.n6, R.id.n7, R.id.n8,
            R.id.n9, R.id.n0
        )

        for (buttonId in numberButtons) {
            findViewById<Button>(buttonId).setOnClickListener {
                onNumberButtonClick(it)
            }
        }

        findViewById<Button>(R.id.del).setOnClickListener {
            onDeleteButtonClick()
        }

        findViewById<Button>(R.id.clear).setOnClickListener {
            clearAll()
        }

        val operatorButtons = arrayOf(
            R.id.divide, R.id.multiply, R.id.minus, R.id.plus, R.id.percent
        )

        for (buttonId in operatorButtons) {
            findViewById<Button>(buttonId).setOnClickListener {
                onOperatorButtonClick(it)
            }
        }

        findViewById<Button>(R.id.equalto).setOnClickListener {
            onEqualsButtonClick()
        }
        Log.e("cs", "Result: $currentInput")
        Log.e("cc", "Result: $currentOperator")
        Log.e("we", "Result: $resultTextView")
        Log.e("rd", "Result: $firstOperand")
    }

    private fun clearAll() {
        currentInput = "0"
        currentOperator = null
        firstOperand = null
        updateResult()
    }


    private fun onNumberButtonClick(view: View) {
        val buttonText = (view as Button).text.toString()

        if (currentInput == "0") {
            currentInput = buttonText
        } else {
            currentInput += buttonText
        }
        updateResult()
    }

    private fun onDeleteButtonClick() {
        if (currentInput.length > 1) {
            currentInput = currentInput.substring(0, currentInput.length - 1)
        } else {
            currentInput = "0"
        }
        updateResult()
    }

    private fun onOperatorButtonClick(view: View) {
        currentOperator = (view as Button).text.toString()
        firstOperand = currentInput.toDouble()
        currentInput = "0"
    }

    private fun onEqualsButtonClick() {
        if (currentOperator != null && firstOperand != null) {
            val secondOperand = currentInput.toDouble()
            val result = when (currentOperator) {
                "+" -> firstOperand!! + secondOperand
                "-" -> firstOperand!! - secondOperand
                "x" -> firstOperand!! * secondOperand
                "/" -> firstOperand!! / secondOperand
                "%" -> firstOperand!! % secondOperand
                else -> throw IllegalArgumentException("Invalid operator")
            }
            currentInput = result.toString()
            currentOperator = null
            firstOperand = null

            updateResult()
        }
    }

    private fun updateResult() {
        resultTextView.text = currentInput
    }
}