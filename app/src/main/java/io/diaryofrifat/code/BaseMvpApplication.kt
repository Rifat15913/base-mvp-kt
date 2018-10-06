package io.diaryofrifat.code

import android.content.Context
import android.support.multidex.MultiDexApplication
import io.diaryofrifat.code.basemvp.BuildConfig
import timber.log.Timber

class BaseMvpApplication : MultiDexApplication() {

    init {
        sInstance = this
    }

    companion object {
        private lateinit var sInstance: BaseMvpApplication

        fun getBaseApplicationContext(): Context {
            return sInstance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        initiate()
        if (BuildConfig.DEBUG) {
            initiateOnlyInDebugMode()
        }
    }

    private fun initiateOnlyInDebugMode() {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return super.createStackElementTag(element) +
                        ":${element.methodName}:${element.lineNumber}"
            }
        })
    }

    private fun initiate() {

    }
}