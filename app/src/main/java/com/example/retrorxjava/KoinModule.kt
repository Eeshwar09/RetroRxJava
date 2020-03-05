package com.example.retrorxjava

import com.example.retrorxjava.Network.Api
import com.example.retrorxjava.utils.AppConfig.baseUrl
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

    viewModel {
        BookViewModel(get())
    }


    single {
        provideForecastApi(retrofit(createOkHttpClient()))
    }

}

val gson = GsonBuilder().serializeNulls().create()!!
private fun createOkHttpClient():OkHttpClient  {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor).build()
}

private fun retrofit(okHttpClient: OkHttpClient):Retrofit {
   return Retrofit.Builder()
        .callFactory(OkHttpClient.Builder().build())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(okHttpClient)
        .build()

}




private fun provideForecastApi(retrofit: Retrofit): Api {
    return retrofit.create(Api::class.java)
}



