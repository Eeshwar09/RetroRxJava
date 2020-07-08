package com.example.retrorxjava.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

 abstract class AndroidTest<A:AppCompatActivity> {
     private lateinit var context: Context

     protected lateinit var activity: A
     abstract fun createActivityInstance(intent: Intent? = null): A
     fun setupActivity(intent: Intent? = null) {
         activity = createActivityInstance(intent)
     }


}