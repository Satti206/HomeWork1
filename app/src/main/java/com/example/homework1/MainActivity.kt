package com.example.homework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.EditText
import android.view.View
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    lateinit var text1: EditText
    lateinit var text2: EditText
    lateinit var text3: EditText
    lateinit var text4: EditText
    lateinit var resultTextEdit: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text1 = findViewById(R.id.text1)
        text2 = findViewById(R.id.text2)
        text3 = findViewById(R.id.text3)
        text4 = findViewById(R.id.text4)
        resultTextEdit = findViewById(R.id.resultTextEdit)

        val button = findViewById<View>(R.id.button)
        button.setOnClickListener {
            calculateAndDisplayResult()
        }
    }

    private fun calculateAndDisplayResult() {
        val loanAmountText = text1.text.toString()
        val annualInterestRateText = text2.text.toString()
        val loanTermMonthsText = text3.text.toString()
        val monthlyIncomeText = text4.text.toString()

        val loanAmount = loanAmountText.toDouble()
        val annualInterestRate = annualInterestRateText.toDouble()
        val loanTermMonths = loanTermMonthsText.toInt()
        val monthlyIncome = monthlyIncomeText.toDouble()

        val monthlyPayment = calculateMonthlyPayment(loanAmount, annualInterestRate, loanTermMonths)

        val result = if (monthlyPayment <= monthlyIncome) {
            "$monthlyPayment"
        } else {
            "Ежемесячный платеж превышает ежемесячный доход. Невозможно выплатить ипотеку."
        }

        resultTextEdit.text = result
    }

    private fun calculateMonthlyPayment(loanAmount: Double, annualInterestRate: Double, loanTermMonths: Int): Double {
        val monthlyInterestRate = annualInterestRate / 12 / 100
        val denominator = (1 - (1 + monthlyInterestRate).pow(-loanTermMonths.toDouble()))
        return (loanAmount * monthlyInterestRate) / denominator
    }
}
