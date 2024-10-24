package com.meli.freemarket

import android.app.Application
import com.meli.network.pocDi.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FreeMarketMainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FreeMarketMainApplication)
            modules(
                networkModule
            )
        }
    }
}
