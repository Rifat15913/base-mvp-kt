package io.diaryofrifat.code

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import io.diaryofrifat.code.basemvp.BuildConfig
import io.diaryofrifat.code.basemvp.data.BaseRepository
import timber.log.Timber

class BaseApplication : MultiDexApplication() {

    init {
        sInstance = this
    }

    companion object {
        private lateinit var sInstance: BaseApplication

        fun getBaseApplicationContext(): Context {
            return sInstance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (applicationContext != null) {
            if (BuildConfig.DEBUG) {
                initiateOnlyInDebugMode()
            }
            initiate(applicationContext)
        }
    }

    private fun initiateOnlyInDebugMode() {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return super.createStackElementTag(element) +
                        " - Method:${element.methodName} - Line:${element.lineNumber}"
            }
        })

        /*if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this)
        }*/
    }

    private fun initiate(context: Context) {
        BaseRepository.init(context)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}