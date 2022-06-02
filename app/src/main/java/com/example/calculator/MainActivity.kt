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
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateDisplay()
    }

    fun onDigit(view:View){
        // Toast.makeText(this, "Button works",Toast.LENGTH_SHORT).show()
        if (!firstDig){
            displayState = ""
            firstDig = true
        }
        displayState += view.tag.toString()
        updateDisplay()
    }
    fun onSymbol(view:View){
        // Toast.makeText(this, "Button works",Toast.LENGTH_SHORT).show()
        if (!firstDig){
            displayState = ""
            firstDig = true
        }
        displayState += view.tag.toString()
        updateDisplay()
    }
    fun updateDisplay(){
        var display = findViewById<TextView>(R.id.display)
        display.text = displayState
    }
    fun clearDisplay(view:View){
        displayState = "0"
        firstDig = false
        updateDisplay()
    }
}