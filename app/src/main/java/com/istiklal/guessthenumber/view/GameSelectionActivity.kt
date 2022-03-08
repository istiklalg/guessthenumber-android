package com.istiklal.guessthenumber.view
/** @author istiklal */
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.istiklal.guessthenumber.R

class GameSelectionActivity : AppCompatActivity() {

    var currentPlayer : String? = null
    private lateinit var comingSoonAlertDialog : AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_selection)
        val mainIntent = intent
        currentPlayer = mainIntent.getStringExtra("currentPlayer")
        Toast.makeText(this, "${getString(R.string.best_wishes_1)} $currentPlayer", Toast.LENGTH_SHORT).show()

        comingSoonAlertDialog = AlertDialog.Builder(this)
        comingSoonAlertDialog.setTitle(R.string.coming_soon_title)
        comingSoonAlertDialog.setMessage(R.string.coming_soon_message)
        comingSoonAlertDialog.setPositiveButton(R.string.alert_positive_button_text, DialogInterface.OnClickListener { dialog, which ->
            Toast.makeText(this, R.string.coming_soon_toast, Toast.LENGTH_SHORT).show()
        })
//        alertBuilder.setNegativeButton("I'LL REMOVE", DialogInterface.OnClickListener { dialog, which ->
//            Toast.makeText(this, "We got the feedback ..", Toast.LENGTH_SHORT).show()
//        })

    }

    override fun onStart() {
        super.onStart()
        val mainIntent = intent
        currentPlayer = mainIntent.getStringExtra("currentPlayer")

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

    fun playSinglePlayer(view : View){
        val intent = Intent(applicationContext, SinglePlayerActivity::class.java)
        intent.putExtra("currentPlayer", currentPlayer)
        startActivity(intent)

    }

    fun playWithMachine(view : View){
        // val alertBuilder = AlertDialog.Builder(this)  // use activity context
//        comingSoonAlertDialog.setTitle(R.string.coming_soon_title)
//        comingSoonAlertDialog.setMessage(R.string.coming_soon_message)
//        comingSoonAlertDialog.setPositiveButton(R.string.alert_positive_button_text, DialogInterface.OnClickListener { dialog, which ->
//            Toast.makeText(this, R.string.coming_soon_toast, Toast.LENGTH_SHORT).show()
//        })
////        alertBuilder.setNegativeButton("I'LL REMOVE", DialogInterface.OnClickListener { dialog, which ->
////            Toast.makeText(this, "We got the feedback ..", Toast.LENGTH_SHORT).show()
////        })
        comingSoonAlertDialog.show()
    }

    fun playMultiPlayer(view : View){
        // val alertBuilder = AlertDialog.Builder(this)  // use activity context
//        comingSoonAlertDialog.setTitle(R.string.coming_soon_title)
//        comingSoonAlertDialog.setMessage(R.string.coming_soon_message)
//        comingSoonAlertDialog.setPositiveButton(R.string.alert_positive_button_text, DialogInterface.OnClickListener { dialog, which ->
//            Toast.makeText(this, R.string.coming_soon_toast, Toast.LENGTH_SHORT).show()
//        })
////        alertBuilder.setNegativeButton("I'LL REMOVE", DialogInterface.OnClickListener { dialog, which ->
////            Toast.makeText(this, "We got the feedback ..", Toast.LENGTH_SHORT).show()
////        })
        comingSoonAlertDialog.show()
    }
}