package com.cleo.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.cleo.todoapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.getIntExtra("image", R.drawable.ic_launcher_background)
        val name = intent.getStringExtra("activityName")
        val time = intent.getStringExtra("activityTime")
        val details = intent.getStringExtra("activityDescription")
        val status = intent.getSerializableExtra("activityStatus")

        val button = findViewById<Button>(R.id.status_button)


        val icon = findViewById<ImageView>(R.id.activity_icon)
        icon.setImageResource(image)

        val title = findViewById<TextView>(R.id.activity_title)
        title.text = name

        val instant = findViewById<TextView>(R.id.activity_instant)
        instant.text = time

        val activityDetails = findViewById<TextView>(R.id.activity_details)
        activityDetails.text = details

        button.setOnClickListener {
            val activityStatus = findViewById<TextView>(R.id.activity_status)
            activityStatus.text = status.toString()
        }
        val floatButton = binding.floatButton

        floatButton.setOnClickListener {
            val intent = Intent(this@DetailsActivity, InputActivity::class.java)
            startActivity(intent)
        }

    }
}