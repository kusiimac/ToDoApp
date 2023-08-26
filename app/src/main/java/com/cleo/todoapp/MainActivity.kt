package com.cleo.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cleo.todoapp.data.ActivityAdapter
import com.cleo.todoapp.data.ActivityModel
import com.cleo.todoapp.data.ActivityStatus
import com.cleo.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = ArrayList<ActivityModel>()
        list.add(ActivityModel(R.drawable.exercise,"Exercising", "Morning", "This activity always begins at 5 O'clock", ActivityStatus.Pending))
        list.add(ActivityModel(R.drawable.cooking_dinner,"Cooking", "Morning", "Cooking always begins at 8 O'clock", ActivityStatus.Postponed))
        list.add(ActivityModel(R.drawable.coding,"Coding", "Afternoon", "This activity will begin at 1 O'clock", ActivityStatus.Pending))
        list.add(ActivityModel(R.drawable.soccer,"Playing soccer", "Evening", "This activity always begins at 3 O'clock", ActivityStatus.Completed))
        list.add(ActivityModel(R.drawable.cooking,"Cooking dinner", "Evening", "This activity always begins at 7 O'clock", ActivityStatus.Pending))
        list.add(ActivityModel(R.drawable.coding,"Coding", "Evening", "This activity always begins at 10 O'clock. It is either " +
                "conducted by the trainer or it can be by the learner as a self study practice session to improve on" +
                "the skills learned in the previous online sessions.", ActivityStatus.Completed))

        val activityAdapter = ActivityAdapter(this,list)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = activityAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}