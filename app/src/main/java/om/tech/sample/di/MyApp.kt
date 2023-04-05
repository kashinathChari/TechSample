package om.tech.sample.di

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: Application()
{

    override fun onCreate() {
        super.onCreate()
        Log.d("Test","Application created")
    }

}
