package com.abhishek.text_recognition

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private var imageBitmap : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        detectTxt()

    }

    private fun detectTxt(){
        val image = FirebaseVisionImage.fromBitmap(imageBitmap!!)
        val detector = FirebaseVision.getInstance().onDeviceTextRecognizer
        detector.processImage(image).addOnSuccessListener { firebaseVisionText ->
            processTxt(firebaseVisionText)
        }.addOnFailureListener {
            showToast("Something went wrong!!")
        }
    }

    private fun processTxt(text: FirebaseVisionText){
        val blocks = text.textBlocks
        if(blocks.size == 0){
            Toast.makeText(this, "No Text Found! Try Again", Toast.LENGTH_SHORT).show()
            return
        }

        for(block in text.textBlocks){
            val txt = block.getText()
            txtView!!.textSize = 16f
            txtView!!.setText(txt)
        }
    }

    private fun showToast(message : String){
        Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
    }

}