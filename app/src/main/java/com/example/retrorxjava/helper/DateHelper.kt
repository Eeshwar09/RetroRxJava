package com.example.retrorxjava.helper

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object DateHelper {
  @SuppressLint("SimpleDateFormat")
  fun Date(date:String):String{

      val newFormat = SimpleDateFormat("yyyy-MM-dd")
      val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
      val NormalDate = format.parse(date)
      val formattedDate = newFormat.format(NormalDate!!)
      return formattedDate
  }

}