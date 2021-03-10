package com.example.converter

import kotlin.math.pow

class Number {

    fun decimalToBinary(n: Long): String {

        val result = mutableListOf<Long>()
        var quotient = 1L //start as 1 as 0 means the conversion is complete and the while loop will end
        //rename n to num as the function parameter is immutable, lol kotlin
        var num = n
        while (quotient > 0) {
            quotient = num / 2
            // get remainder
            val remainder: Long = num % 2
            // add remainder to beginning of list
            result.add(0, remainder)
            // continue calculating on the quotient
            num = quotient
        }
        //convert answer into a string
        return result.joinToString(separator = "")
    }

    fun binaryToDecimal(num: String): String {
        val num = num
        var total = 0L
        var power: Int = num.toString().length - 1
        var calculation: Long
        for (n in num.indices) {
            // convert character to int because lol kotlin
            val charToNumber = Character.getNumericValue(num[n])
            calculation = (charToNumber * (2.00.pow(power).toLong()))
            total += calculation
            power -= 1
        }
        return total.toString()
    }

    fun decimalToHex(n: Long): String {
        val result = mutableListOf<String>()
        var num = n
        var quotient = 1L
        while (quotient > 0) {
            quotient = num / 16
            val remainder: Long = num % 16
            result.add(0, hexNumber(remainder.toInt()))
            num = quotient
        }
        return result.joinToString("")
    }

    private fun hexNumber(n: Int): String {
        val result: String
        when (n) {
            10 -> {
                result = "a"
            }
            11 -> {
                result = "b"
            }
            12 -> {
                result = "c"
            }
            13 -> {
                result = "d"
            }
            14 -> {
                result = "e"
            }
            15 -> {
                result = "f"
            }
            else -> {
                result = n.toString()
            }
        }
        return result
    }

    fun hexToDecimal(input: String): String {
        var total = 0L
        var power: Int = input.length - 1
        var calculation: Long

        for (n in input.indices) {
            var charToNumber = Character.getNumericValue(input[n])
            calculation = (charToNumber * (16.00.pow(power).toLong()))
            total += calculation
            power -= 1
        }
        return total.toString()
    }
}









