package io.diaryofrifat.code.utils.helper

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
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
        fun getVersionCode(): Int {
            return getPackageInfo()?.versionCode ?: 0
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
    }
}