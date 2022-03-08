/** @author istiklal */

import java.lang.Exception
import java.lang.NumberFormatException

fun main(args: Array<String>) {
    var current_number = generate_number()
    println("Called function and returned : $current_number")
    println()
    var your_guess = arrayListOf<Int>()
    var guess_result_list = arrayListOf<ArrayList<Int>>()


    do {
        your_guess = get_guess_from_user()
        var guess_result:ArrayList<Int> = compare_guess_and_number(current_number, your_guess)
        guess_result_list.add(guess_result)
        println("GUESS RESULT IS : your guess : ${guess_result[0]}, numbers in place : ${guess_result[1]}, numbers not in place : ${guess_result[2]}")
        println("Your past results ")
        guess_result_list.forEach {
            println("# ${guess_result_list.indexOf(it)+1} : $it")
        }
    }while (guess_result[1] < 4)

    println("CONGRATS YOU HİT THE NUMBER ${current_number[0]}${current_number[1]}${current_number[2]}${current_number[3]}")
}

fun generate_number():ArrayList<Int>{
    // this function added to MachineNumberGenerator ...
    /* Function to let machine generate a 4 digit number for which user will guess */

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
    println()
    print("Generated number : ")
    generated_number.forEach(){
        print("$it")
    }
    println()
    return generated_number
}

fun compare_guess_and_number(generated_number:ArrayList<Int>, guess:ArrayList<Int>):ArrayList<Int>{
    var commons = generated_number.intersect(guess)
    println("Common digits : ${commons}")
    var in_place:Int=0
    var not_in_place:Int=0
    var your_guess_integer = "${guess[0]}${guess[1]}${guess[2]}${guess[3]}".toInt()

    if (commons.isNotEmpty()){
        commons.forEach {
            if(generated_number.indexOf(it) == guess.indexOf(it)){
                in_place++
            }else{
                not_in_place++
            }
        }
    }
    return arrayListOf(your_guess_integer, in_place, not_in_place)
}

fun get_guess_from_user():ArrayList<Int>{
    var your_guess_string:String?=null
    var your_guess_integer:Int?=null
    var your_guess_list = arrayListOf<Int>()
    var your_guess_set = mutableSetOf<Int>()

    do {
        your_guess_list.clear()
        your_guess_set.clear()
        print("Write down your guess : ")
        your_guess_string = readLine()!!
        if (your_guess_string.startsWith("0")) {
            println("First digit can not be 0 !!")
            println()
            get_guess_from_user()
        }

        try {
            your_guess_integer = your_guess_string.toInt()
            your_guess_string.forEach {
                your_guess_list.add(it.toString().toInt())
                your_guess_set.add(it.toString().toInt())
//                print("now set : $your_guess_set")
//                print("now lst : $your_guess_list")
            }
        }catch (e:NumberFormatException){
            println("Your guess is containing chars that not number!!")
            println()
            your_guess_integer=null
        }catch (e:Exception){
            println("ERROR : $e")
            break
        }
        println()
//        println("number set ${your_guess_set.size} : $your_guess_set")
        if (your_guess_set.size < 4 && your_guess_list.size == 4){
            println("Your guess is containing same number more than one!!")
            println()
            your_guess_integer=null
        }
    }while (your_guess_integer == null)
    println("Your guess : $your_guess_integer")
    if (your_guess_list.isNotEmpty()){
        return your_guess_list
    }else{
        return get_guess_from_user()
    }

//    }while (your_guess.size < 4)


}