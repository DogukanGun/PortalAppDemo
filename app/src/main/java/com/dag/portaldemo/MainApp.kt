package com.dag.portaldemo

import android.app.Application
import com.dag.portaldemo.di.appModule
import com.dag.portaldemo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MainApp)
            // Load modules
            modules(appModule)
            modules(viewModelModule)
        }
    }
}