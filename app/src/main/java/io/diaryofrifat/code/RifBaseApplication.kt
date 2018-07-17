package io.diaryofrifat.code

import android.content.Context
import android.support.multidex.MultiDexApplication
import io.diaryofrifat.code.rifbase.BuildConfig
import io.diaryofrifat.code.utils.Toaster
import timber.log.Timber

class RifBaseApplication : MultiDexApplication() {

    init {
        sInstance = this
    }

    companion object {
        private lateinit var sInstance: RifBaseApplication

        fun getBaseApplicationContext(): Context {
            return sInstance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        initiate(applicationContext)
        if (BuildConfig.DEBUG) {
            initiateOnlyInDebugMode(applicationContext)
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
        Toaster.init()
    }
}