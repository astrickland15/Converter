package com.example.converter

class Distance {

    fun feetToMetres(num: Double): Double {
        return (num *  0.3048)
    }

    fun metresToFeet(num: Double): Double {
        return num * 3.280839895
    }

    fun milesToKilometres(num: Double): Double {
        return num * 1.609344
    }

    fun kilometresToMiles(num: Double): Double {
        return num  * 0.6213711922
    }

    fun centimetresToMillimetres(num: Double): Double {
        return num * 10
    }

    fun millimetresToCentimetres(num: Double): Double {
        return num * 0.1
    }

    fun yardsToMetres(num: Double): Double {
        return num * 0.9144
    }

    fun metresToYards(num: Double): Double {
        return num * 1.0936132983
    }

    fun inchesToCentimetres(num: Double): Double {
        return num * 2.54
    }

    fun centimetresToInches(num: Double): Double {
        return num * 0.3937007874
    }
}
