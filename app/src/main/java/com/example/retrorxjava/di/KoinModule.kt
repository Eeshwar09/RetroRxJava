@file:Suppress("DEPRECATION")

package com.example.retrorxjava.di

import android.preference.PreferenceManager
import com.example.retrorxjava.viewmodel.BookViewModel
import com.example.retrorxjava.network.Api
import com.example.retrorxjava.utils.AppConfig.baseUrl
import com.example.retrorxjava.viewmodel.ArticleModel
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val retrofitModule = module {
    single {
        val defaultSharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(get())
        defaultSharedPreferences
    }


    viewModel {
        BookViewModel(api = get())

    }
    viewModel {
        ArticleModel()
    }


    single {
        provideForecastApi(retrofit(createOkHttpClient()))
    }

}

val gson = GsonBuilder().serializeNulls().create()!!
 fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor).build()
}

 fun retrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .callFactory(OkHttpClient.Builder().build())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(okHttpClient)
        .build()

}


 fun provideForecastApi(retrofit: Retrofit): Api {
    return retrofit.create(Api::class.java)
}



