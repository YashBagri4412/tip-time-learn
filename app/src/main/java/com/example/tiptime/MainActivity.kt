package com.example.tiptime

import java.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tipResult.text = ""
        binding.calculateButton.setOnClickListener{
            calculateTip()
        }
    }
    private fun displayTip(tip : Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun calculateTip() {
//        Input from the textField
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost == null || cost == 0.00){
            displayTip(0.00)
            return
        }
//        Radio button selection
        val percentageTip = when(binding.tipSelector.checkedRadioButtonId){
            R.id.twenty_percent_option -> 0.20
            R.id.eighteen_percent_option -> 0.18
            else -> 0.15
        }
//        Tip calculation
        var tip = cost * percentageTip

//        Switch to check round-off
        val roundUp = binding.roundTipSwitch.isChecked

        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }

        displayTip(tip)
    }
}