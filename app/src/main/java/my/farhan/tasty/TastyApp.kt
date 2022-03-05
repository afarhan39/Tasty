package my.farhan.tasty

import android.app.Application
import my.farhan.tasty.di.viewModelModule
import my.farhan.tasty.di.databaseModule
import my.farhan.tasty.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TastyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@TastyApp)
            modules(databaseModule + viewModelModule + repositoryModule)
        }
    }
}