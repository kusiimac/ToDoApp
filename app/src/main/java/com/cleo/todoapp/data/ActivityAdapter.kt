package com.cleo.todoapp.data

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cleo.todoapp.DetailsActivity
import com.cleo.todoapp.R

class ActivityAdapter(var context:Context, var list:ArrayList<ActivityModel>):
    RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>(){
    class ActivityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.activity_icon)
        val name = itemView.findViewById<TextView>(R.id.activity_title)
        val time = itemView.findViewById<TextView>(R.id.activity_instant)

        val card = itemView.findViewById<CardView>(R.id.activity_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_layout, null, false)
        return ActivityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.image.setImageResource(list[position].image)
        holder.name.text = list[position].activityName
        holder.time.text = list[position].activityTime

        holder.card.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("image", list[position].image)
            intent.putExtra("activityName", list[position].activityName)
            intent.putExtra("activityTime", list[position].activityTime)
            intent.putExtra("activityDescription", list[position].activityDescription)
            intent.putExtra("activityStatus", list[position].activityStatus)
            context.startActivity(intent)

        }
    }
}