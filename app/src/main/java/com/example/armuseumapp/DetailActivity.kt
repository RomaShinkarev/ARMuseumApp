package com.example.armuseumapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    var modelClick: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        modelClick = findViewById(R.id.model_button)

        val image: Image? = intent.getParcelableExtra(INTENT_PARCELABLE)
        Log.d("MyLog", "Result:${image}")


        val imgSrc = findViewById<ImageView>(R.id._imageDetail)
        val imgTitle = findViewById<TextView>(R.id._imageTitle)
        val imgDesc = findViewById<TextView>(R.id._imageDesc)

        imgSrc.setImageResource(image!!.imageSrc)
        imgTitle.text = image.imageTitle
        imgDesc.text = image.imageDesc

        if(image.imageSrc == 2131165298) {
            modelClick?.visibility = android.view.View.VISIBLE

            modelClick?.setOnClickListener {
                val sceneViewerIntent = Intent(Intent.ACTION_VIEW)
                sceneViewerIntent.data =
                    Uri.parse("https://arvr.google.com/scene-viewer/1.0?file=" + getString(R.string.model_url_1))
                sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox")
                startActivity(sceneViewerIntent)
            }
        }

        else if(image.imageSrc == 2131165352) {
            modelClick?.visibility = android.view.View.VISIBLE

            modelClick?.setOnClickListener {
                val sceneViewerIntent = Intent(Intent.ACTION_VIEW)
                sceneViewerIntent.data =
                    Uri.parse("https://arvr.google.com/scene-viewer/1.0?file=" + getString(R.string.model_url_2))
                sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox")
                startActivity(sceneViewerIntent)
            }
        }
    }
}
