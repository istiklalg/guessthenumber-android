package com.istiklal.guessthenumber.view
/** @author istiklal */

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.istiklal.guessthenumber.R
import com.istiklal.guessthenumber.adapter.GuessesAdapter
import com.istiklal.guessthenumber.model.Guess
import com.istiklal.guessthenumber.service.MachineNumberGenerator
import kotlinx.android.synthetic.main.activity_single_player_activity.*
import kotlinx.android.synthetic.main.activity_single_player_activity.view.*


class SinglePlayerActivity : AppCompatActivity() {

    var currentPlayer : String? = null
    var maxScore : Long = 0
    var currentGameScore : Long = 1000
    private var machinesNumber : ArrayList<Int>? = null
    private var guessList : ArrayList<Guess> = ArrayList()
    private lateinit var guessResultsRecyclerViewAdapter : GuessesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_player_activity)

        val mainIntent = intent
        currentPlayer = mainIntent.getStringExtra("currentPlayer")
        maxScore = mainIntent.getLongExtra("maxScore", 0)
        //scoreText.text = maxScore.toString()
        scoreText.text = currentGameScore.toString()
        if (machinesNumber.isNullOrEmpty()) {
            machinesNumber = MachineNumberGenerator.pickNumber()
            Log.i("istiklal [${this.javaClass.name}]", "machines number was null, new picked number is : $machinesNumber")
        } else {
            Log.i("istiklal [${this.javaClass.name}]", "machines number is already exist : $machinesNumber")
        }
        currentGameScore = 1000

        // initialize the machinesNumber variable
        // we will write a class for random number generation of machine
        // machinesNumber = GenerateNumber()
    }

    override fun onStart() {
        super.onStart()

        // Toast.makeText(this, "$machinesNumber :)", Toast.LENGTH_SHORT).show()
        // currentGameScore = 1000
        makeGuessButton.isEnabled = false
        numberInput1.requestFocus()

        // Text change listener for 1. digit input
        numberInput1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
            override fun afterTextChanged(s: Editable) {
                if (s.isNotBlank()) numberInput2.requestFocus()
                //else makeGuessButton.isEnabled = false
            }
        })

        // Delete listener for 2. digit input
        numberInput2.setOnKeyListener { v, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_DEL && numberInput2.text.toString() == "") {
                numberInput1.requestFocus()
                return@setOnKeyListener true
            }
            false
        }
        // Text change listener for 2. digit input
        numberInput2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
            override fun afterTextChanged(s: Editable) {
                if (s.isNotBlank()) numberInput3.requestFocus()
                //else makeGuessButton.isEnabled = false
            }
        })

        // delete listener for 3. digit input
        numberInput3.setOnKeyListener { v, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_DEL && numberInput3.text.toString() == "") {
                numberInput2.requestFocus()
                return@setOnKeyListener true
            }
            false
        }
        // Text change listener for 3. digit input
        numberInput3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
            override fun afterTextChanged(s: Editable) {
                if (s.isNotBlank()) numberInput4.requestFocus()
                //else makeGuessButton.isEnabled = false
            }
        })

        // Delete listener for 4. digit input
        numberInput4.setOnKeyListener { v, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_DEL && numberInput4.text.toString() == "") {
                numberInput3.requestFocus()
                return@setOnKeyListener true
            }
            false
        }
        // Text change listener for 4. digit input
        numberInput4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
            override fun afterTextChanged(s: Editable) {
                if (s.isNotBlank()) makeGuessButton.isEnabled = true
            }
        })

        val layoutManager = LinearLayoutManager(this)
        guessResultsRecycler.layoutManager = layoutManager
        guessResultsRecyclerViewAdapter = GuessesAdapter(guessList)
        guessResultsRecycler.adapter = guessResultsRecyclerViewAdapter
        Toast.makeText(this, "${getString(R.string.max_score_notify)} $maxScore", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun makeNewGuess(view : View) {
        var digit1 : Int? = null
        var digit2 : Int? = null
        var digit3 : Int? = null
        var digit4 : Int? = null
        val userGuess: ArrayList<Int>
        val userGuessSet: Set<Int>


        try {
            digit1 = numberInput1.text.toString().toInt()
            digit2 = numberInput2.text.toString().toInt()
            digit3 = numberInput3.text.toString().toInt()
            digit4 = numberInput4.text.toString().toInt()
        } catch (e: NumberFormatException) {
            Log.w("istiklal [${this.javaClass.name}]", "there are missing digits !! ")

        }

        if(digit1 == null|| digit2 == null || digit3 == null || digit4 == null) {
            Toast.makeText(this, R.string.missing_digit_warning, Toast.LENGTH_SHORT).show()
        } else if(digit1 == 0) {
            Toast.makeText(this, R.string.start_with_zero_warning, Toast.LENGTH_SHORT).show()
        } else {
            userGuess = arrayListOf(digit1, digit2, digit3, digit4)
            userGuessSet = mutableSetOf(digit1, digit2, digit3, digit4)
            if (userGuess.size != userGuessSet.size){
                Toast.makeText(this, R.string.repeating_number_warning, Toast.LENGTH_SHORT).show()
            } else {
                // userGuess will be send to guess operations over Guess object...
                Log.d("istiklal [${this.javaClass.name}]", "Given Number : ${userGuess.joinToString("")}")
                machinesNumber?.let {
                    val userGuessAttempt = Guess(userGuess, it, guessList.size + 1)
                    currentGameScore -= userGuessAttempt.score
                    scoreText.text = currentGameScore.toString()
                    guessList.add(userGuessAttempt)
                    guessResultsRecyclerViewAdapter.notifyDataSetChanged()  // to notify to recycler view data changed !!
                    guessResultsRecycler.scrollToPosition(guessList.size - 1)
                    emptyInputs()
                    // if (userGuessAttempt.inPlace == 4 && userGuessAttempt.notInPlace == 0) {
                    if (userGuessAttempt.isWinner()) {
                        // max score management
                        if (currentGameScore > maxScore) {
                            intent.putExtra("maxScore", currentGameScore)
                            maxScore = currentGameScore
                            Log.d("istiklal [${this.javaClass.name}]", "Our current max score : $maxScore")
                        }
                        val alertBuilder = AlertDialog.Builder(this)  // use activity context
                        alertBuilder.setTitle(R.string.game_winner_title)
                        if(currentGameScore == maxScore) alertBuilder.setMessage(getString(R.string.game_winner_new_max_text, currentGameScore.toString()))
                        else if(currentGameScore > 700) alertBuilder.setMessage(getString(R.string.game_winner_great_score_text, currentGameScore.toString()))
                        else alertBuilder.setMessage(getString(R.string.game_winner_text, currentGameScore.toString()))
                        alertBuilder.setCancelable(false)  // we protected it from being canceled by touching outside
                        alertBuilder.setPositiveButton(R.string.game_result_positive_button_text, DialogInterface.OnClickListener { dialog, which ->
                            finish()
                            startActivity(intent)
                        })
                        alertBuilder.setNegativeButton(R.string.game_result_negative_button_text, DialogInterface.OnClickListener { dialog, which ->
                            finish()
                        })
                        alertBuilder.show()
                    }
                }

            }

        }


    }

    fun getTheResults(view : View){

    }

    fun emptyInputs() {
        numberInput1.setText(""); numberInput2.setText(""); numberInput3.setText(""); numberInput4.setText(""); numberInput1.requestFocus()
    }

}