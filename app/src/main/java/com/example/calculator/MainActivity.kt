package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.util.CalcUtil

class MainActivity : AppCompatActivity() {
    private var activityMainBinding : ActivityMainBinding? = null
    private var liveExpr : MutableLiveData<String> = MutableLiveData("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding!!.root)

        liveExpr.observe (this){ data ->
            activityMainBinding!!.calcInput.setText(data)
        }

        val numberBtns : Array<Button> = arrayOf(
            activityMainBinding!!.btn0,
            activityMainBinding!!.btn1,
            activityMainBinding!!.btn2,
            activityMainBinding!!.btn3,
            activityMainBinding!!.btn4,
            activityMainBinding!!.btn5,
            activityMainBinding!!.btn6,
            activityMainBinding!!.btn7,
            activityMainBinding!!.btn8,
            activityMainBinding!!.btn9
        )
        for (btn in numberBtns){
            btn.setOnClickListener {
                liveExpr.value = liveExpr.value?.let { it1 -> addDigits(it1, btn.text[0]) }
            }
        }

        val operatorBtns: Array<Button> = arrayOf(
            activityMainBinding!!.btnPlus,
            activityMainBinding!!.btnMinus,
            activityMainBinding!!.btnMul,
            activityMainBinding!!.btnDiv
        )
        for (btn in operatorBtns){
            btn.setOnClickListener {
                liveExpr.value = liveExpr.value?.let { it1 -> addDigits(it1, btn.text[0]) }
            }
        }

        activityMainBinding!!.btnCancel.setOnClickListener {
            liveExpr.value = "0"
        }


        activityMainBinding!!.btnCalc.setOnClickListener {
            //코틀린은 static이 없어서 객체를 생성해야함
            val util = CalcUtil()
            liveExpr.value = liveExpr.value?.let { it1 -> util.getResult(it1).toString() }
        }
    }

    private fun addDigits(prevState : String, digit : Char): String {
        //0 혹은 공백이 있을 경우 지우고 출력
        return if((prevState == "0") or (prevState == "0.0") or (prevState == "")) "$digit"

        //숫자일 경우 붙여서 출력
        else if(isNumber(prevState.last()) and isNumber(digit)) "$prevState$digit"

        //연산자일 경우 띄워서 출력
        else "$prevState $digit"
    }

    private fun isNumber(digit: Char): Boolean{
    return (digit == '0') or
            (digit == '1') or
            (digit == '2') or
            (digit == '3') or
            (digit == '4') or
            (digit == '5') or
            (digit == '6') or
            (digit == '7') or
            (digit == '8') or
            (digit == '9')
    }
}