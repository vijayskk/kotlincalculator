package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    var displayState : String = "0"
    var firstDig : Boolean = false
    var lastNum : Boolean = false
    var lastDot : Boolean = false
    var opPressed : Boolean = false
    var operator : String = ""
    var decimalIn : Boolean = false
    var gotAnswer : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateDisplay()
    }

    fun onDigit(view:View){
        // Toast.makeText(this, "Button works",Toast.LENGTH_SHORT).show()
        if(gotAnswer){
            clearDisplay(view)
        }
        if (!firstDig){
            displayState = ""
            firstDig = true
        }
        displayState += view.tag.toString()
        lastNum = true
        updateDisplay()
    }
    fun onSymbol(view:View){
        // Toast.makeText(this, "Button works",Toast.LENGTH_SHORT).show()

        if(!opPressed && firstDig){
            displayState += view.tag.toString()
            gotAnswer = false
            opPressed = true
            operator = view.tag.toString()
            lastDot = false
        }

        updateDisplay()
    }

    fun onDecimal(view:View){
        // Toast.makeText(this, "Button works",Toast.LENGTH_SHORT).show()
        if(gotAnswer){
            clearDisplay(view)
        }

        if (!firstDig){
            displayState = ""
            firstDig = true
        }
        if(lastNum && !lastDot){
            displayState += "."
            decimalIn = true
            lastNum = false
            lastDot = true
        }
        updateDisplay()
    }

    fun onEquals(view: View){
        if (firstDig){
            if(!opPressed){
                Toast.makeText(this, "No math symbol found.",Toast.LENGTH_SHORT).show()
            }else{
                Calculate(displayState,operator,decimalIn)
                opPressed = false
            }

        }
    }

    fun updateDisplay(){
        var display = findViewById<TextView>(R.id.display)
        display.text = displayState
    }
    fun clearDisplay(view:View){
        displayState = "0"
        decimalIn = false
        firstDig = false
        lastDot = false
        opPressed = false
        gotAnswer = false
        updateDisplay()
    }


    fun Calculate(mathText:String,symbol:String,haveDecimal : Boolean){
        val nums = mathText.split(operator)
        var s1 : String = nums[0]
        var s2 : String = nums[1]

        if(s1.length == 0){
            s1 = "0"
        }
        if(s2.length == 0){
            s2 = "0"
        }

        if(haveDecimal){
            val a = s1.toDouble()
            val b = s2.toDouble()
            displayState = doMathDouble(a,b,operator).toString()
        }else{
            val a = s1.toInt()
            val b = s2.toInt()
            displayState = doMathInt(a,b,operator).toString()
        }

        gotAnswer = true
        updateDisplay()
    }

    fun doMathInt(a : Int,b : Int,operator : String): Int {
        if(operator == "+"){
            return a + b
        }
        else if(operator == "-"){
            return a - b
        }
        else if(operator == "x"){
            return a * b
        }
        else if(operator == "/"){
            return a / b
        }
        else{
            return 0
        }
    }
    fun doMathDouble(a : Double,b : Double,operator : String): Double {
        if(operator == "+"){
            return a + b
        }
        else if(operator == "-"){
            return a - b
        }
        else if(operator == "x"){
            return a * b
        }
        else if(operator == "/"){
            return a / b
        }
        else{
            return 0.0
        }
    }
}