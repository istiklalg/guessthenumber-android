package com.istiklal.guessthenumber.adapter
/** @author istiklal */
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.istiklal.guessthenumber.R
import com.istiklal.guessthenumber.model.Guess
import kotlinx.android.synthetic.main.recycler_row_guess.view.*

class GuessesAdapter(var guessesList : ArrayList<Guess>) : RecyclerView.Adapter<GuessesAdapter.GuessHolder>() {


    class GuessHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuessHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rowView = inflater.inflate(R.layout.recycler_row_guess, parent, false)
        return GuessHolder(rowView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GuessHolder, position: Int) {
        holder.itemView.guess_counter_title.text = (position+1).toString()
        holder.itemView.guess_number_title.text = guessesList[position].number.toString()
        holder.itemView.guess_in_place_title.text = guessesList[position].inPlace.toString()
        holder.itemView.guess_not_in_place_title.text = guessesList[position].notInPlace.toString()
        holder.itemView.guess_score_title.text = if(guessesList[position].score == 0) "0" else "-" + guessesList[position].score.toString()
    }

    override fun getItemCount(): Int {
        return guessesList.size
    }

}