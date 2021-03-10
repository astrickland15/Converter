package com.example.converter

import android.widget.EditText

class Conversion {

    // convert edittext to Int because lol Kotlin
    fun EditText.getInt(): Int {
        return text.toString().toInt()
    }

    // convert edittext to Double because lol Kotlin
    fun getDouble(text: String): Double {
        return text.toDouble()
    }

 fun getTheAnswer(num: Double, aMeasures: ArrayList<String>, sItem: String): Double {
        var answer: Double = 0.00
        for (item in aMeasures) {
            when (sItem) {
                "Feet to Metres" -> {
                    answer = Distance().feetToMetres(num)
                }
                "Metres to Feet" -> {
                    answer = Distance().metresToFeet(num)
                }
                "Miles to Kilometres" -> {
                    answer = Distance().milesToKilometres(num)
                }
                "Kilometres to Miles" -> {
                    answer = Distance().kilometresToMiles(num)
                }
                "Centimetres to Millimetres" -> {
                    answer = Distance().centimetresToMillimetres(num)
                }
                "Millimetres to Centimetres" -> {
                    answer = Distance().millimetresToCentimetres(num)
                }
                "Yards to Metres" -> {
                    answer = Distance().yardsToMetres(num)
                }
                "Metres to Yards" -> {
                    answer = Distance().metresToYards(num)
                }
                "Inches to Centimetres" -> {
                    answer = Distance().inchesToCentimetres(num)
                }
                "Centimetres to Inches" -> {
                    answer = Distance().centimetresToInches(num)
                }
                "Grams to Ounces" -> {
                    answer = Weight().gramsToOunces(num)
                }
                "Ounces to Grams" -> {
                    answer = Weight().ouncesToGrams(num)
                }
                "Kilograms to Pounds" -> {
                    answer = Weight().kilogramsToPounds(num)
                }
                "Pounds to Kilograms" -> {
                    answer = Weight().poundsToKilograms(num)
                }
                "Pounds to Stones" -> {
                    answer = Weight().poundsToStones(num)
                }
                "Stones to Pounds" -> {
                    answer = Weight().stonesToPounds(num)
                }
                "Tons to Pounds" -> {
                    answer = Weight().tonsToPounds(num)
                }
                "Pounds to Tons" -> {
                    answer = Weight().poundsToTons(num)
                }
                "Celsius to Fahrenheit" -> {
                    answer = Temperature().celsiusToFahrenheit(num)
                }
                "Fahrenheit to Celsius" -> {
                    answer = Temperature().fahrenheitToCelsius(num)
                }
                "Decimal to Binary" -> {
                    answer = Number().decimalToBinary(num.toLong()).toDouble()
                }
                "Litres to Gallons" -> {
                    answer = Volume().litresToGallons(num)
                }
                "Gallons to Litres" -> {
                    answer = Volume().gallonsToLitres(num)
                }
                "Fluid Ounces to Millilitres" -> {
                    answer = Volume().fluidOunceToMillilitres(num)
                }
                "Millilitres to Fluid Ounces" -> {
                    answer = Volume().millilitreToFluidOunces(num)
                }
            }
        }
        return answer
    }
}

