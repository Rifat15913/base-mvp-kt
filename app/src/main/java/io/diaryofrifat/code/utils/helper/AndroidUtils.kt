package io.diaryofrifat.code.utils.helper

import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import io.diaryofrifat.code.BaseMvpApplication

class AndroidUtils private constructor() {
    companion object {
        /**
         * This method provides application id of the application
         *
         * @return [String] application id
         * */
        fun getApplicationId(): String? {
            return getPackageInfo()?.packageName
        }

        /**
         * This method provides version code of the application
         *
         * @return [Int] version code
         * */
        @Suppress("DEPRECATION")
        fun getVersionCode(): Long {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                getPackageInfo()?.longVersionCode ?: 0
            } else {
                getPackageInfo()?.versionCode as Long? ?: 0
            }
        }

        /**
         * This method provides version name of the application
         *
         * @return [String] version name
         * */
        fun getVersionName(): String? {
            return getPackageInfo()?.versionName
        }

        /**
         * This method provides package info of the application
         *
         * @return [PackageInfo] package info of the application
         * */
        fun getPackageInfo(): PackageInfo? {
            val context: Context = BaseMvpApplication.getBaseApplicationContext()

            return try {
                context.packageManager.getPackageInfo(context.packageName, 0)
            } catch (nameException: PackageManager.NameNotFoundException) {
                null
            }
        }

        /**
         * This method provides the state if a service is running or not
         *
         * @param context UI context
         * @param serviceClass service class
         * @return [Boolean] if the service is running or not
         * */
        @Suppress("DEPRECATION")
        fun isServiceRunning(context: Context, serviceClass: Class<*>): Boolean {
            val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
                if (serviceClass.name == service.service.className) {
                    return true
                }
            }
            return false
        }
    }
}