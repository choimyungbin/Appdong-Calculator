package com.example.calculator.util


class CalcUtil {
    fun getResult(expr: String): Double{
        val calcUnits: List<String> = expr.split(" ") as MutableList<String>
        val handleUnits = ArrayList<String>()
        val result= ArrayList<String>()
        var i = 0
        while(i < calcUnits.size){
            if(calcUnits[i] == "*"){
                handleUnits.set(
                    handleUnits.size-1,
                    (handleUnits[handleUnits.size-1].toDouble() * calcUnits[i+1].toDouble()).toString()
                )
                i++
            } else if (calcUnits[i] == "/"){
                handleUnits.set(
                    handleUnits.size-1,
                    (handleUnits[handleUnits.size-1].toDouble() / calcUnits[i+1].toDouble()).toString()
                )
                i++
            }else{  //숫자, +, -
                handleUnits.add(calcUnits[i])
            }
            i++
        }

        i = 0
        while(i < handleUnits.size){
            if(handleUnits[i]=="+"){
                result.set(
                    result.size-1,
                    (result[result.size-1].toDouble() + handleUnits[i+1].toDouble()).toString()
                )
                i++
            } else if(handleUnits[i]=="-"){
                result.set(
                    result.size-1,
                    (result[result.size-1].toDouble() - handleUnits[i+1].toDouble()).toString()
                )
                i++
            } else{
                result.add(handleUnits[i])
            }
            i++
        }
        return result[0].toDouble()
    }
}


