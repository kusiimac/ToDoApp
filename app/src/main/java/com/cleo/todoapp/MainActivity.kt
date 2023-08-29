package com.cleo.todoapp

import android.content.Intent
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
        list.add(ActivityModel(R.drawable.exercise,"Exercising", "Morning", "This activity always " +
                "begins at 5 O'clock. It all started as a spiritual practice but has become popular as a " +
                "way of promoting physical and mental well-being.", ActivityStatus.Pending))
        list.add(ActivityModel(R.drawable.cooking_dinner,"Cooking", "Morning", "Cooking always " +
                "begins at 8 O'clock. You only need 15 tools in your kitchen to make pretty much" +
                " any breakfast recipe that comes your way, from eggs Benedict to oatmeal and " +
                "toast.", ActivityStatus.Postponed))
        list.add(ActivityModel(R.drawable.coding,"Coding", "Afternoon", "This activity will begin at" +
                " 1 O'clock. Interested in learning how to code, but unsure where to start? This " +
                "path provides an overview of the main branches of programming: computer science, " +
                "web development, and data science ", ActivityStatus.Pending))
        list.add(ActivityModel(R.drawable.soccer,"Playing soccer", "Afternoon", "Football, also called " +
                "soccer, is a game involving two teams of 11 players who try to maneuver the ball" +
                " into the other team's goal. The team that scores more goals wins ", ActivityStatus.Pending))
        list.add(ActivityModel(R.drawable.cooking,"Cooking dinner", "Evening", "This activity always " +
                "begins at 7 O'clock We've rounded up our 60 best, easy dinner recipes! From" +
                " grilled steak to braised chickpeas to all the sheet pan dinners, we've got" +
                " you covered.", ActivityStatus.Pending))
        list.add(ActivityModel(R.drawable.coding,"Coding", "Evening", "This activity always begins " +
                "at 10 O'clock. It is either conducted by the trainer or by the learner as a " +
                "self study practice session to improve on the skills learned in the previous " +
                "lessons.", ActivityStatus.Completed))

        val activityAdapter = ActivityAdapter(this,list)
        val recyclerView = binding.recyclerView
        recyclerView.adapter = activityAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        val floatButton = binding.floatButton

        floatButton.setOnClickListener {
            val intent = Intent(this@MainActivity, InputActivity::class.java)
            startActivity(intent)
        }

    }
}