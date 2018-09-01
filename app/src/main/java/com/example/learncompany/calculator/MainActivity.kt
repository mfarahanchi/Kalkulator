package com.example.learncompany.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
//import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

enum class CalculatorMode {
    None, Plus, Min
}


class MainActivity : AppCompatActivity() {
    var labelString = ""
    var currentMode = CalculatorMode.None
    var lastButtonWasMode = false
    var savedNumber = 0
    var lastButtonWasEqual = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupCalculator()
    }

    fun setupCalculator() {
        val buttons = arrayOf(button0,button1,button2,button3,button4,button5,button6,button7,button8,button9)
        for(i in buttons.indices) {
            buttons[i].setOnClickListener { didPressNumber(i) }
        }
        buttonClear.setOnClickListener { didPressClear() }
        buttonEqual.setOnClickListener { didPressEqual() }
        buttonPlus.setOnClickListener { changeMode(CalculatorMode.Plus) }
        buttonMin.setOnClickListener { changeMode(CalculatorMode.Min) }
    }

    fun didPressEqual() {
        if(lastButtonWasEqual) {
            return
        }
        lastButtonWasMode = true
        val labelInt = labelString.toInt()
        var result = 0
        when(currentMode) {
            CalculatorMode.Plus -> result = savedNumber + labelInt
            CalculatorMode.Min -> result = savedNumber - labelInt
        }
        labelString = result.toString()
        updateText()
    }

    fun didPressClear() {
        labelString = ""
        textView.setText("0")
        changeMode(CalculatorMode.None)
    }

    fun didPressNumber(number: Int) {
        if(labelString.length > 4) {
            textView.setText("Error")
            return
        }
        if(lastButtonWasMode) {
            labelString = ""
            lastButtonWasMode = false
        }

        labelString = "$labelString$number"
        updateText()
    }

    fun changeMode(mode: CalculatorMode) {

        when(mode) {
            CalculatorMode.None -> {
                currentMode = mode
                return
            }
            CalculatorMode.Min -> {
                currentMode = mode
            }
            CalculatorMode.Plus -> {
                currentMode = mode
            }
        }

        savedNumber = labelString.toInt()
        lastButtonWasMode = true
    }

    fun updateText() {
        val labelInt = labelString.toInt()
        labelString = labelInt.toString()
        textView.setText(labelString)
    }


}
