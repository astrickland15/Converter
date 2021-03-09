package com.example.converter

class Volume {
    fun litresToGallons(num: Double): Double {
        return num * 0.2641720524
    }
    fun gallonsToLitres(num: Double): Double {
        return num * 3.785411784
    }
    fun fluidOunceToMillilitres(num: Double): Double {
        return num * 29.5735
    }
    fun millilitreToFluidOunces(num:Double): Double {
        return num * 0.0351951
    }
}