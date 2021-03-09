package com.example.converter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PageTwo : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_two)

        // display back button and action bar title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Select a Conversion"

        // get applicable measurements depending upon button pressed on page one
        val applicableMeasurements = intent.getStringArrayListExtra("measurements")

        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = applicableMeasurements?.let {
            ArrayAdapter(
                this, android.R.layout.simple_spinner_item,
                it
            )
        }
        spinner.adapter = adapter

        var selectedItem = ""
        var answer: Double
        val input = findViewById<EditText>(R.id.input)
        var inputValue = ""

        // display applicable keyboard where necessary
        if (applicableMeasurements != null) {
            displayApplicableKeyboard(applicableMeasurements, input)
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedItem = parent?.getItemAtPosition(position).toString()
                // if converting from decimal, change keyboard to number
                if (spinner.selectedItem == "Decimal to Binary" ||
                    spinner.selectedItem == "Decimal to Hexadecimal") {
                    input.inputType = InputType.TYPE_CLASS_NUMBER
                } else if (spinner.selectedItem == "Hexadecimal to Decimal") {
                    input.inputType = InputType.TYPE_CLASS_TEXT
                } else if (spinner.selectedItem == "Binary to Decimal") {
                    input.inputType = InputType.TYPE_CLASS_NUMBER
                    input.keyListener = DigitsKeyListener.getInstance("01")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO()
            }
        }
        val convert = findViewById<Button>(R.id.convert)
        convert.setOnClickListener {
            val result = findViewById<TextView>(R.id.result)
            inputValue = input.text.toString()

            // display error message if invalid input
            if (invalidInput(inputValue) ||  isImpossiblyLargeNumber(inputValue, selectedItem) ||
                colderThanAbsoluteZero(inputValue, selectedItem))  {
                result.text = errorMessage(inputValue, selectedItem, result)
            }
            else
            {
                result.setTextColor(Color.parseColor("black"))

                // display result if not converting Numbers
                if (!convertingNumbers(selectedItem)) {
                    val amount = Conversion().getDouble(inputValue)
                    if (applicableMeasurements != null) {
                        answer = Conversion().getTheAnswer(amount, applicableMeasurements, selectedItem)
                        result.text = displayResult(selectedItem, inputFormat(inputValue),
                            formatAnswer(answer, selectedItem))
                    }
                }
                else if (selectedItem == "Decimal to Hexadecimal") {
                    result.text = displayResult(selectedItem, inputFormat(inputValue),
                        Number().decimalToHex(inputValue.toLong()))
                }
                else if (selectedItem == "Decimal to Binary") {
                    result.text = displayResult(selectedItem, inputFormat(inputValue),
                        Number().decimalToBinary(inputValue.toLong()))
                }
                else if (selectedItem == "Binary to Decimal" && validBinaryNumber(inputValue)) {
                    val binToDecAnswer = Number().binaryToDecimal(inputValue)
                    result.text = displayResult(selectedItem, inputValue,
                        formatAnswer(binToDecAnswer.toDouble(), selectedItem))
                }
                else if (selectedItem == "Hexadecimal to Decimal" && validHexNumber(inputValue)) {
                    val hexToDecAnswer = Number().hexToDecimal(inputValue)
                    result.text = displayResult(selectedItem, inputValue,
                        formatAnswer(hexToDecAnswer.toDouble(), selectedItem))
                }
                else {
                    result.setTextColor((Color.parseColor("red")))
                    if (selectedItem == "Hexadecimal to Decimal") {
                        result.text = "Invalid Hex Number"
                    }
                    else if (selectedItem == "Binary to Decimal") {
                        result.text = "Invalid Binary Number"
                    }
                }
            }
        }
    }
    // pressing back button returns you to previous activity (page)
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    // add clear button to actionbar
    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.actionbarmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    // handles click event for Clear button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val inputValue = findViewById<EditText>(R.id.input)
        val result = findViewById<TextView>(R.id.result)
        inputValue.text.clear()
        result.text = ""
        return super.onOptionsItemSelected(item)
    }
}

fun displayResult(sItem: String, inpValue: String, answer: String): String {
    val word1 = sItem.substringBefore(delimiter = " ")
    val word2 = sItem.substringAfterLast(delimiter = " ")

    // add "degrees" to temperature conversion result
    if (sItem == "Celsius to Fahrenheit" || sItem == "Fahrenheit to Celsius") {
        return "$inpValue $word1 is $answer Degrees $word2"
    }
    return "$inpValue $word1 is $answer $word2"
}

fun inputFormat(num: String): String {
    //if input number is an integer, just return it with comma separator and no decimal places
    return if (!num.contains('.')) {
        String.format("%,d", num.toLong())
    }
    else {
        // if number has a decimal place add comma separator if applicable
        val beforeDecPoint = num.substringBefore('.')
        val commaBeforeDecPoint = String.format("%,d", beforeDecPoint.toLong())
        val afterDecPoint = num.substringAfter('.')
        // concatenate into properly formatted number
        val num = "$commaBeforeDecPoint.$afterDecPoint"
        removeTrailingZeroes(num)
    }
}

fun formatAnswer(num: Double, sItem: String): String {
    return if (sItem == "Decimal to Binary") {// don't add comma separator and prevent e notation
        String.format("%.0f", num)
    }
    else {
        val num = String.format("%,.4f", num)
        removeTrailingZeroes(num)
    }
}

fun invalidInput(text: String): Boolean {
    if (text == "" || text.startsWith('.') || text.endsWith('.')) {
        return true
    }
    return false
}

fun removeTrailingZeroes(result: String): String {
    var amount = result.trimEnd('0')
    // if last character is . remove it
    if (amount.endsWith('.')) {
        amount = amount.substring(0, amount.length - 1)
    }
    return amount
}

fun displayApplicableKeyboard(aList: ArrayList<String>, inputField: EditText) {
    // for temperature conversions, enable keyboard with - and .
    if (aList[0] == "Celsius to Fahrenheit") {
        inputField.inputType =
            InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED or
                    InputType.TYPE_NUMBER_FLAG_DECIMAL
    }
    // set default keyboard for number conversions to number as Decimal to Binary is listed first
    else if (aList[0] == "Decimal to Binary") {
        inputField.inputType = InputType.TYPE_CLASS_NUMBER
    }
}

fun colderThanAbsoluteZero(temp: String, sItem: String): Boolean {
    if (sItem == "Celsius to Fahrenheit" || sItem == "Fahrenheit to Celsius") {
        if ((temp.toDouble() < -273.15 && sItem == "Celsius to Fahrenheit")
            or (temp.toDouble() < -459.67 && sItem == "Fahrenheit to Celsius")) {
            return true
        }
    }
    return false
}

fun validHexNumber(hex: String): Boolean {
    val regex = "^[0-9a-fA-F]+$".toRegex()
    if (regex.matches(hex)) {
        return true
    }
    return false
}
fun errorMessage(inpValue: String, sItem: String, result: TextView): String {
    result.setTextColor((Color.parseColor("red")))
    return if (colderThanAbsoluteZero(inpValue, sItem)) {
        "Please Enter a Temperature Higher Than Absolute Zero."
    }
    else {
        "Please Enter a Valid Value."
    }
}

fun isImpossiblyLargeNumber(inpValue: String, sItem: String): Boolean {
    if (sItem == "Hexadecimal to Decimal" || sItem == "Binary to Decimal") {
        return false
    }
    else {
        try {
            // if input is float, analyze the number before dec point
            val beforeDecimal: String = if (inpValue.contains('.')) {
                inpValue.substringBefore('.')
            } else {
                inpValue
            }
            beforeDecimal.toLong()
        }
        catch (e: NumberFormatException) {
            return true
        }
    }
    return false
}

fun convertingNumbers(sItem: String): Boolean {
    if (sItem == "Hexadecimal to Decimal" || sItem == "Binary to Decimal" ||
        sItem == "Decimal to Hexadecimal" || sItem == "Decimal to Binary") {
        return true
    }
    return false
}

fun validBinaryNumber(binValue: String): Boolean {
    val regex = "^[0-1]+$".toRegex()
    if (regex.matches(binValue)) {
        return true
    }
    return false
}




