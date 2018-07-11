package code.rifatsdev.rifbase

import android.content.Context
import android.support.multidex.MultiDexApplication

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

    }

    private fun initiate(context: Context) {

    }
}