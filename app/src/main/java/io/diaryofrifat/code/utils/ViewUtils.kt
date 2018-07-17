package io.diaryofrifat.code.utils

import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import io.diaryofrifat.code.RifBaseApplication


class ViewUtils {
    companion object {
        /**
         * This method returns a local drawable
         *
         * @param resourceId desired resource
         * @return drawable of the resource
         * */
        fun getDrawable(resourceId: Int): Drawable? {
            return ContextCompat.getDrawable(
                    RifBaseApplication.getBaseApplicationContext(),
                    resourceId
            )
        }

        /**
         * This method returns a local color id
         *
         * @param colorResourceId desired color resource
         * @return color id
         * */
        fun getColor(colorResourceId: Int): Int {
            return ContextCompat.getColor(
                    RifBaseApplication.getBaseApplicationContext(),
                    colorResourceId
            )
        }
    }
}