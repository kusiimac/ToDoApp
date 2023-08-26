package com.cleo.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val image = intent.getIntExtra("image", R.drawable.ic_launcher_background)
        val name = intent.getStringExtra("activityName")
        val time = intent.getStringExtra("activityTime")
        val details = intent.getStringExtra("activityDescription")
        val status = intent.getSerializableExtra("activityStatus")

        val button = findViewById<Button>(R.id.status_button)



        val icon = findViewById<ImageView>(R.id.activity_icon)
        icon.setImageResource(image)

        val title = findViewById<TextView>(R.id.activity_details)
        title.text = name

        val instant = findViewById<TextView>(R.id.activity_instant)
        instant.text = time

        val activityDetails = findViewById<TextView>(R.id.activity_details)
        activityDetails.text = details

        button.setOnClickListener {
            val activityStatus = findViewById<TextView>(R.id.activity_status)
            activityStatus.text = status.toString()
        }

    }
}