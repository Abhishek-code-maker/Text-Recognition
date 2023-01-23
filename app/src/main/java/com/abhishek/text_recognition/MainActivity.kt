package com.abhishek.text_recognition

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var imageBitmap : Bitmap? = null
    var uriString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgBtn.setOnClickListener {
            dispatchTakeImageIntent()
        }

        detectBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("uri", uriString)
            startActivity(intent)
        }
    }

    val REQUEST_IMAGE_CAPTURE = 1

    @SuppressLint("QueryPermissionsNeeded")
    private fun dispatchTakeImageIntent(){
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(takePictureIntent.resolveActivity(packageManager)!= null){
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){
            val extras = data!!.extras
            imageBitmap = extras!!.get("data") as Bitmap
            imgView!!.setImageBitmap(imageBitmap)
        }
    }

}