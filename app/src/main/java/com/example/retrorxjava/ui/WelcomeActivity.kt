package com.example.retrorxjava.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import com.example.retrorxjava.R
import android.widget.ProgressBar
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.content.Intent
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.animation.ObjectAnimator
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.android.synthetic.main.toolbar.*


class WelcomeActivity : AppCompatActivity() {

    var splashProgress: ProgressBar? = null
    var SPLASH_TIME = 5000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        splashProgress = findViewById(R.id.splashProgress);
        playProgress();
        toolbar.title = getString(R.string.app_name)


        Handler().postDelayed({
            val mySuperIntent = Intent(this,BookActivity::class.java)
            startActivity(mySuperIntent)

            finish()
        }, SPLASH_TIME.toLong())

    }
    private fun playProgress() {
        ObjectAnimator.ofInt(splashProgress, "progress", 100)
            .setDuration(5000)
            .start()
    }
}
