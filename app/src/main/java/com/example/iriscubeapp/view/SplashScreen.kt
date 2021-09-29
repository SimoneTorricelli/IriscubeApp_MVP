package com.example.iriscubeapp.view


import android.content.ContentValues
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.iriscubeapp.R
import java.io.InputStream
import java.lang.Exception

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val diamondImage: ImageView = findViewById(R.id.SplashScreenImage)
        //Insert my custom png to diamonImage
        val assetManager: AssetManager? = applicationContext?.assets
        try {
            assetManager?.let {
                val diamondAsset: InputStream = it.open("diamond.png")
                val bitmap = BitmapFactory.decodeStream(diamondAsset)
                diamondImage.setImageBitmap(bitmap)
            }
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, e.toString())

        }

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            println("AVVIO ATTIVITA DA SPLASHSCREEN")
            finish()
        }, 1000) // 1000 is the delayed time in milliseconds.
    }
}