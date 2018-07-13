package io.diaryofrifat.code.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import io.diaryofrifat.code.RifBaseApplication
import java.util.*

object PermissionUtils {

    private const val REQUEST_CODE_PERMISSION_DEFAULT = 1

    /**
     * This method is to get specific permission/s from user with default request code and
     * returns true/false depending on the allowance of the requested permissions
     *
     * @param permissions required permission/s
     * @return true if the requested permissions are already allowed otherwise false
     * */
    fun requestPermission(vararg permissions: String): Boolean {
        return requestPermission(REQUEST_CODE_PERMISSION_DEFAULT, permissions.toList())
    }

    /**
     * This method is to get specific permission/s from user with custom request code and
     * returns true/false depending on the allowance of the requested permissions
     *
     * @param requestCode custom request code
     * @param permissions required permission/s
     * @return true if the requested permissions are already allowed otherwise false
     * */
    fun requestPermission(requestCode: Int, vararg permissions: String): Boolean {
        return requestPermission(requestCode, permissions.toList())
    }

    private fun requestPermission(requestCode: Int, permissions: List<String>): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }

        val context = RifBaseApplication.getBaseApplicationContext()
        val permissionsNotTaken = ArrayList<String>()

        for (i in permissions.indices) {
            if (!isAllowed(permissions[i])) {
                permissionsNotTaken.add(permissions[i])
            }
        }

        if (permissionsNotTaken.isEmpty()) {
            return true
        }

        (context as Activity).requestPermissions(
                permissionsNotTaken.toTypedArray(), requestCode
        )

        return false
    }

    /**
     * This method returns the allowance state of a permission
     *
     * @param permission permission to be checked
     * @return true if the permission is already granted, else false
     * */
    fun isAllowed(permission: String): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }

        return RifBaseApplication.getBaseApplicationContext()
                .checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }
}