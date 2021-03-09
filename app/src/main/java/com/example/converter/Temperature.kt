package com.example.converter

class Temperature {

    fun celsiusToFahrenheit(num: Double): Double {
        return (num * 9 / 5) + 32
    }

    fun fahrenheitToCelsius(num: Double): Double {
        return  (num - 32) * 5 / 9
    }
}