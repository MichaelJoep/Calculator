package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbering

        tvOne.setOnClickListener { appendOnExpression("1", true) }
        tvTwo.setOnClickListener { appendOnExpression("2", true) }
        tvThree.setOnClickListener { appendOnExpression("3", true) }
        tvFour.setOnClickListener { appendOnExpression("4", true) }
        tvFive.setOnClickListener { appendOnExpression("5", true) }
        tvSix.setOnClickListener { appendOnExpression("6", true) }
        tvSeven.setOnClickListener { appendOnExpression("7", true) }
        tvEight.setOnClickListener { appendOnExpression("8", true) }
        tvNine.setOnClickListener { appendOnExpression("9", true) }
        tvZero.setOnClickListener { appendOnExpression("0", true) }
        tvDot.setOnClickListener { appendOnExpression(".", true) }


        //operators

        tvDivision.setOnClickListener { appendOnExpression("/", false) }
        tvMinus.setOnClickListener { appendOnExpression("-", false) }
        tvMultiply.setOnClickListener { appendOnExpression("*", false) }
        tvOpenBracket.setOnClickListener { appendOnExpression("(", false) }
        tvCloseBracket.setOnClickListener { appendOnExpression(")", false) }
        tvPlus.setOnClickListener { appendOnExpression("+", false) }

        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }

        tvDel.setOnClickListener {
            val expression = tvExpression.text.toString()
            if(expression.isNotEmpty()){
                tvExpression.text = expression.substring(0, expression.length-1)
            }
            tvResult.text = ""
        }

        tvEqual.setOnClickListener {

            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()


            } catch (e: Exception) {
                Log.d("Exception", "message: " + e.message)
            }

        }
    }

    fun appendOnExpression( expression: String,  clear: Boolean){

        if (tvResult.text.isNotEmpty()){
            tvExpression.text = ""
        }

    if(clear){
        tvResult.text = ""
        tvExpression.append(expression)
    }else{
        tvExpression.append(tvResult.text)
        tvExpression.append(expression)
        tvResult.text=""
    }
    }
}
