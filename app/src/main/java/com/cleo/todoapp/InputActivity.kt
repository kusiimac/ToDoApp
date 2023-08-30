package com.cleo.todoapp

import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.icu.util.Calendar
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.cleo.todoapp.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() , DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0


    lateinit var image: ImageView
    private val pickImage = 0
    private val cameraCapture = 1
    private var imageUri: Uri? = null
    private var imageBitmap: Bitmap? = null


    lateinit var binding: ActivityInputBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)


        image = binding.inputImage


        // Setting click listener to the image TextView
        image.setOnClickListener {
            selectImage()
        }

        val activityName = binding.inputName.text.toString()
        val activityDescription = binding.inputDetails.text.toString()

        pickDate()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == cameraCapture) {
            if (grantResults.isNotEmpty() && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(takePictureIntent, cameraCapture)
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == pickImage) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val filePictureIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(filePictureIntent, pickImage)
            } else {
                Toast.makeText(this, "File Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            image.setImageURI(imageUri)
        }


        else if (resultCode == RESULT_OK && requestCode == cameraCapture) {
            imageBitmap = data?.extras!!["data"] as Bitmap?
            image.setImageBitmap(imageBitmap)
        }

    }

    private fun selectImage() {
        // Creating AlertDialog
        val choice = arrayOf<CharSequence>("Capture Image", "Choose from Gallery", "Cancel")
        val myAlertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        //myAlertDialog.setTitle("Select Image")
        myAlertDialog.setItems(choice, DialogInterface.OnClickListener { dialog, item ->
            when {
                choice[item] == "Choose from Gallery" -> {
                    checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, pickImage)
                    val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                    startActivityForResult(gallery, pickImage)
                }
                choice[item] == "Capture Image" -> {
                    checkPermission(Manifest.permission.CAMERA, cameraCapture)
                    val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(camera, cameraCapture)
                }
                choice[item] == "Cancel" -> {
                    dialog.dismiss()
                }
            }
        })
        myAlertDialog.show()
    }

    private fun getDateTimeCalender(){
        val calendar: Calendar = Calendar.getInstance()
        day = calendar.get(Calendar.DAY_OF_MONTH)
        month = calendar.get(Calendar.MONTH)
        year = calendar.get(Calendar.YEAR)
        hour = calendar.get(Calendar.HOUR)
      //minute = calendar.get(Calendar.MINUTE) --> alternatively, it can be done as shown below
        minute = calendar[Calendar.MINUTE]
    }

    private fun pickDate(){
        val date = findViewById<TextView>(R.id.input_date)
        date.setOnClickListener {
            getDateTimeCalender()
            DatePickerDialog(this,this, year, month, day).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month + 1
        savedYear = year

        getDateTimeCalender()
        TimePickerDialog(this, this, hour, minute, false).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute
        val dateTime = findViewById<TextView>(R.id.input_date)
        dateTime.text = " Date: $savedDay - $savedMonth - $savedYear    Time: $savedHour : $savedMinute"
    }

    // Function to check and request permission.
    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this@InputActivity, permission) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this@InputActivity, arrayOf(permission), requestCode)
        }
    }
}