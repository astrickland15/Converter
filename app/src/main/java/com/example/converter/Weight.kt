package com.example.converter

class Weight {

    fun gramsToOunces(num: Double): Double {
        return num * 0.03527396195
    }

    fun ouncesToGrams(num: Double): Double {
        return num * 28.34952
    }

    fun kilogramsToPounds(num: Double): Double {
        return num * 2.2046226218
    }

    fun poundsToKilograms(num: Double): Double {
        return num * 0.45359237
    }

    fun poundsToStones(num: Double): Double {
        return num * 0.071428571429
    }

    fun stonesToPounds(num: Double): Double {
        return num * 14
    }

    fun tonsToPounds(num: Double): Double {
        return num * 2204.6226218
    }

    fun poundsToTons(num: Double): Double {
        return num * 0.00045359237
    }
}