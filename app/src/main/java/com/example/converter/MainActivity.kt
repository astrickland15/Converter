 package com.example.converter

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


 class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dist = findViewById<Button>(R.id.distance)
        val weight = findViewById<Button>(R.id.weight)
        val temp = findViewById<Button>(R.id.temperature)
        val numbers = findViewById<Button>(R.id.numbers)
        val volume = findViewById<Button>(R.id.volume)

        // open new page when clicking applicable button
        dist.setOnClickListener {
            // when clicking this button, spinner will display list of measurements
            var distList = SpinnerContent().distanceList
            populateSpinner(distList)
        }
        weight.setOnClickListener {
            var weightList = SpinnerContent().weightList
            populateSpinner(weightList)
        }
        temp.setOnClickListener {
            var tempList = SpinnerContent().temperatureList
            populateSpinner(tempList)
        }
        numbers.setOnClickListener {
            var numList = SpinnerContent().numbersList
            populateSpinner(numList)
        }
        volume.setOnClickListener {
            var volList = SpinnerContent().volumeList
            populateSpinner(volList)
        }
    }
    private fun populateSpinner(test: ArrayList<String>) {
        val intent = Intent(this, PageTwo::class.java)
        intent.putStringArrayListExtra("measurements", test)
        startActivity(intent)
    }
}

