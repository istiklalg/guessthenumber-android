package com.istiklal.guessthenumber

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.istiklal.guessthenumber.view.GameSelectionActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var currentPlayer:String? = null
    lateinit var shrdPref : SharedPreferences
    var maxScore : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shrdPref = this.getSharedPreferences("com.istiklal.guessthenumber", Context.MODE_PRIVATE)

        currentPlayer = shrdPref.getString("player", null)
        maxScore = shrdPref.getLong("maxScore", 0)
    }

    override fun onStart() {
        super.onStart()
        if (currentPlayer.isNullOrEmpty()){
            playerName.visibility = View.INVISIBLE
            playerNameInput.visibility = View.VISIBLE
            Toast.makeText(this, getString(R.string.empty_payer_name_warning), Toast.LENGTH_SHORT).show()
        }else{
            playerNameInput.visibility = View.INVISIBLE
            playerName.visibility = View.VISIBLE
            playerName.text = "$currentPlayer"
            // Toast.makeText(this, "${getString(R.string.best_wishes_1)} $currentPlayer", Toast.LENGTH_SHORT).show()
        }
    }

    fun gameSelectionScreen(view: View){
        val intent = Intent(applicationContext, GameSelectionActivity::class.java)
        if (currentPlayer.isNullOrEmpty()){
            currentPlayer = playerNameInput.text.toString()
            if (currentPlayer.isNullOrEmpty()){
                Toast.makeText(this, getString(R.string.invalid_payer_name_warning), Toast.LENGTH_SHORT).show()
            }else{
//                Toast.makeText(this, "Got the name $currentPlayer, now start to play", Toast.LENGTH_SHORT).show()
                intent.putExtra("currentPlayer", currentPlayer)
                startActivity(intent)
            }
        }else{
//            Toast.makeText(this, "Got the name $currentPlayer, now start to play", Toast.LENGTH_SHORT).show()
            intent.putExtra("currentPlayer", currentPlayer)
            startActivity(intent)
        }
    }

    fun changeUserName(view: View){
        // reset max score ?
        currentPlayer = null
        shrdPref.edit().putString("player", null).apply()
        this.onStart()
    }

    fun resetMaxScore(view:View){
        maxScore = 0
        shrdPref.edit().putLong("maxScore", 0).apply()
        this.onStart()
    }
}