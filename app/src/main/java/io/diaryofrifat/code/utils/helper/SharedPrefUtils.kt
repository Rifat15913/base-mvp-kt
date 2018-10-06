package io.diaryofrifat.code.utils.helper

import android.content.SharedPreferences
import android.preference.PreferenceManager
import io.diaryofrifat.code.BaseMvpApplication

object SharedPrefUtils {
    private val preferences: SharedPreferences = PreferenceManager
            .getDefaultSharedPreferences(BaseMvpApplication.getBaseApplicationContext())

    /**
     * Below methods are to write data in SharedPreference
     * @param key key to write
     * @param value value to be written
     * */

    fun write(key: String, value: String): Boolean {
        val editor = preferences.edit()
        editor.putString(key, value)
        return editor.commit()
    }

    fun write(key: String, value: Boolean): Boolean {
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        return editor.commit()
    }

    fun write(key: String, value: Int): Boolean {
        val editor = preferences.edit()
        editor.putInt(key, value)
        return editor.commit()
    }

    fun write(key: String, value: Long): Boolean {
        val editor = preferences.edit()
        editor.putLong(key, value)
        return editor.commit()
    }

    /**
     * Below methods are to read data from SharedPreference
     * @param key key to fetch data
     * */

    fun read(key: String): String? {
        return preferences.getString(key, Constants.Default.DEFAULT_STRING)
    }

    fun readLong(key: String): Long {
        return preferences.getLong(key, Constants.Default.DEFAULT_LONG)
    }

    fun readInt(key: String): Int {
        return preferences.getInt(key, Constants.Default.DEFAULT_INTEGER)
    }

    fun readBoolean(key: String): Boolean {
        return preferences.getBoolean(key, Constants.Default.DEFAULT_BOOLEAN)
    }

    fun readBooleanDefaultTrue(key: String): Boolean {
        return preferences.getBoolean(key, true)
    }

    /**
     * This method provides a state if the SharedPreference contains the key
     *
     * @param key key to be searched
     * @return [Boolean] state
     * */
    operator fun contains(key: String): Boolean {
        return preferences.contains(key)
    }
}
