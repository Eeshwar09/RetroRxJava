package com.example.retrorxjava

import com.example.retrorxjava.Network.Api
import com.example.retrorxjava.utils.AppConfig.baseUrl
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.Provider
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.retrorxjava.model.BookResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

fun getProjectList(userId: String): LiveData<List<BookResponse>> {
    val data = MutableLiveData<List<BookResponse>>()
    //retrofit
    return data
}


private fun provideForecastApi(retrofit: Retrofit): Api {
    return retrofit.create(Api::class.java)
}



