package io.diaryofrifat.code.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.view.View
import io.diaryofrifat.code.RifBaseApplication
import io.diaryofrifat.code.rifbase.R
import io.reactivex.Observable


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

        /**
         * This method converts pixels to dp
         *
         * @param px desired pixels
         * @return amount in dp
         * */
        fun pxToDp(px: Float): Float {
            val densityDpi = Resources.getSystem().displayMetrics.densityDpi.toFloat()
            return px / (densityDpi / 160f)
        }

        /**
         * This method converts dp to pixels
         *
         * @param dp desired amount of dp
         * @return amount in pixels
         * */
        fun dpToPx(dp: Int): Float {
            val density = Resources.getSystem().displayMetrics.density
            return Math.round(dp * density).toFloat()
        }

        /**
         * This method returns bitmap from the view
         *
         * @param view desired view
         * @return bitmap of the view
         * */
        fun getBitmapFromView(view: View): Bitmap? {
            val bitmap =
                    Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(bitmap))

            return bitmap
        }

        /**
         * This method returns Observable<Bitmap> of the view
         *
         * @param view desired view
         * @return observable of the bitmap of the view
         * */
        fun getObservableBitmapFromView(view: View): Observable<Bitmap> {
            return Observable.defer {
                val bitmap = getBitmapFromView(view)

                if (bitmap == null) {
                    Observable.error(
                            Throwable(RifBaseApplication.getBaseApplicationContext()
                                    .getString(R.string.error_could_not_create_bitmap))
                    )
                } else {
                    Observable.just(bitmap)
                }
            }
        }
    }
}