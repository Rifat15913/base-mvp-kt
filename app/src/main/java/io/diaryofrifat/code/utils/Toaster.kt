package io.diaryofrifat.code.utils

import android.text.TextUtils
import android.widget.Toast
import io.diaryofrifat.code.RifBaseApplication

// TODO: Explore Toasty and implement here
class Toaster private constructor() {

    companion object {
        /**
         * This method creates and shows a toast for long period
         *
         * @param text the text to be shown
         * @return void
         * */
        fun showLong(text: String) {
            if (!TextUtils.isEmpty(text)) {
                Toast.makeText(RifBaseApplication.getBaseApplicationContext(),
                        text, Toast.LENGTH_LONG).show()
            }
        }

        /**
         * This method creates and shows a toast for short period
         *
         * @param text the text to be shown
         * @return void
         * */
        fun showShort(text: String) {
            if (!TextUtils.isEmpty(text)) {
                Toast.makeText(RifBaseApplication.getBaseApplicationContext(),
                        text, Toast.LENGTH_SHORT).show()
            }
        }
    }
}