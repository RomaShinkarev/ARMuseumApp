package com.example.armuseumapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import java.util.jar.Manifest



class ScannerActivity: AppCompatActivity(), ZBarScannerView.ResultHandler {

    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    private lateinit var  zbView: ZBarScannerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zbView = ZBarScannerView(this)
        setContentView(zbView)
    }

    override fun onResume() {
        super.onResume()
        zbView.setResultHandler(this)
        zbView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        zbView.stopCamera()
    }

    override fun handleResult(result: Result?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(INTENT_PARCELABLE, strToImg(result?.contents))
        startActivity(intent)
    }

    private fun strToImg(str: String?): Image{

        val res = MainActivity().imageList.filter {
            it.imageDesc == str?.subSequence(str.indexOf("imageDesc") + 11, str.length - 2)

        }
        if(res.isEmpty()){
            return Image(
                R.drawable.img1,
                "Ошибка",
                "Ошибка чтения QR-кода"
            )
        }

        return res[0]
    }
}