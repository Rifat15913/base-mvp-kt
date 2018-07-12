package io.diaryofrifat.code.rifbase

import android.content.Context
import android.support.multidex.MultiDexApplication
import timber.log.Timber

class RifBaseApplication : MultiDexApplication() {

    private var sContext: Context? = null

    fun getBaseApplicationContext(): Context? {
        return sContext
    }

    override fun onCreate() {
        super.onCreate()
        sContext = applicationContext

        initiate(sContext!!)
        if (BuildConfig.DEBUG) {
            initiateOnlyInDebugMode(sContext!!)
        }
    }

    private fun initiateOnlyInDebugMode(context: Context) {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return super.createStackElementTag(element) +
                        ":${element.methodName}:${element.lineNumber}"
            }
        })
    }

    private fun initiate(context: Context) {

    }
}