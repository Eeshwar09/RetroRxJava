package com.example.retrorxjava.home

import android.app.Application
import org.junit.Test

import org.junit.Assert.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

class MyCustomAppTest:Application() {

    override fun onCreate() {

        startKoin {
            androidContext(this@MyCustomAppTest)
            androidLogger()
            modules(emptyList())
        }
    }
    internal fun loadModules(module: Module, block: () -> Unit) {
        loadKoinModules(module)
        block()
        unloadKoinModules(module)
    }

}