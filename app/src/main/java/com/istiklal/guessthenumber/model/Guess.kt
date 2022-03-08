package com.istiklal.guessthenumber.model
/** @author istiklal */
import android.util.Log

class Guess (digitList : ArrayList<Int>, numberToGuess : ArrayList<Int>, guessCount : Int) {

    /** @param digitList : Player guess
     *  @param numberToGuess : The number that current player trying to guess
     * */

    val number : Int = digitList.joinToString("").toInt()
    var inPlace : Int = 0
    var notInPlace : Int = 0
    var score : Int = 0

    init {
        val results = guessResults(numberToGuess, digitList)
        inPlace = results[0]
        notInPlace = results[1]
        //score = if(guessCount < 8) (inPlace*20 + notInPlace*10 + (inPlace+notInPlace-4)*5) else (inPlace*20 + notInPlace*10 + (inPlace+notInPlace-4)*5 - guessCount*5)
        // evaluating score !!!
        score = (inPlace*0 + notInPlace*10 + (4 - inPlace - notInPlace)*20) // It's negative score, it means every single guess attempt return a negative score and decrease your total score...
    }

    private fun guessResults(generated_number:ArrayList<Int>, guess:ArrayList<Int>):ArrayList<Int>{
        /* */
        val commons = generated_number.intersect(guess)
        Log.d("istiklal [${this.javaClass.name}]","Common digits : $commons")
        var countInPlace : Int=0
        var countNotInPlace : Int=0
//        var your_guess_integer = "${guess[0]}${guess[1]}${guess[2]}${guess[3]}".toInt()

        if (commons.isNotEmpty()){
            commons.forEach {
                if(generated_number.indexOf(it) == guess.indexOf(it)){
                    countInPlace++
                }else{
                    countNotInPlace++
                }
            }
        }
        return arrayListOf(countInPlace, countNotInPlace)
    }

    public fun isWinner() : Boolean {
        return inPlace == 4 && notInPlace == 0
    }

}