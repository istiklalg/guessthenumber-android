package com.istiklal.guessthenumber.service
/** @author istiklal */
import android.util.Log

class MachineNumberGenerator {

//    val pickedNumber : ArrayList<Int>? = null

    companion object{
        fun pickNumber():ArrayList<Int> {
            val numbers= arrayListOf<Int>(0,1,2,3,4,5,6,7,8,9)
            val generated_number= arrayListOf<Int>()

            fun add_digit(digit:Int){
                generated_number.add(digit)
                numbers.remove(digit)
            }

            var first_digit = (1..9).random()
            add_digit(first_digit)

            for (i in (1..3)){
                var digit = numbers.random()
                add_digit(digit)
            }
//    println("Generated number : $generated_number")
            Log.i("istiklal [${this.javaClass.name}]", "$generated_number is picked by machine, you will be trying to guess that")
            return generated_number
        }
    }

}

//fun main(){
//    var machineNumber : ArrayList<Int> = MachineNumberGenerator.pickNumber()
//    println("picked number is : $machineNumber")
//}