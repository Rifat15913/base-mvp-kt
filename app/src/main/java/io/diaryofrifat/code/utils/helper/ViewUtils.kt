package io.diaryofrifat.code.utils.helper

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import io.diaryofrifat.code.RifBaseApplication
import io.diaryofrifat.code.rifbase.R
import io.diaryofrifat.code.rifbase.ui.base.BaseAdapter
import io.diaryofrifat.code.rifbase.ui.base.ItemClickListener
import io.diaryofrifat.code.rifbase.ui.base.ItemLongClickListener
import io.diaryofrifat.code.rifbase.ui.base.SwipeItemHandler
import io.reactivex.Observable


class ViewUtils {
    companion object {
        /**
         * This method returns local resources
         *
         * @return desired resources
         * */
        fun getResources(): Resources {
            return RifBaseApplication.getBaseApplicationContext().resources
        }

        /**
         * This method returns a local font
         *
         * @param resourceId desired resource id
         * @return desired font
         * */
        fun getFont(resourceId: Int): Typeface? {
            return ResourcesCompat.getFont(RifBaseApplication.getBaseApplicationContext(),
                    resourceId)
        }

        /**
         * This method returns a local string
         *
         * @param resourceId desired resource id
         * @return desired string
         * */
        fun getString(resourceId: Int): String {
            return RifBaseApplication.getBaseApplicationContext().getString(resourceId)
        }

        /**
         * This method returns a local drawable
         *
         * @param resourceId desired resource
         * @return drawable of the resource
         * */
        fun getDrawable(resourceId: Int): Drawable? {
            return ContextCompat.getDrawable(
                    RifBaseApplication.getBaseApplicationContext(),
                    resourceId)
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
                    colorResourceId)
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

        /**
         * This method attaches a swipe handler to the RecyclerView
         *
         * @param recyclerView current RecyclerView
         * @param handler swipe handler
         * @return [ItemTouchHelper] touch helper of the [RecyclerView]
         */
        fun addSwipeHandler(recyclerView: RecyclerView, handler: SwipeItemHandler): ItemTouchHelper {
            val itemTouchHelper = ItemTouchHelper(handler)
            itemTouchHelper.attachToRecyclerView(recyclerView)

            return itemTouchHelper
        }

        /**
         * This method sets up a RecyclerView
         *
         * @param recyclerView current RecyclerView
         * @param adapter adapter for the view
         * @param itemClickListener listens to click on items
         * @param itemLongClickListener listens to long click on items
         * @param layoutManager linear or grid or other layout manager for the RecyclerView
         * @param itemDecoration item decoration for margin or separator
         * @param swipeItemHandler handler to work with item swipe
         * @param itemAnimator animator for RecyclerView items
         * */
        fun <T> initializeRecyclerView(recyclerView : RecyclerView,
                                       adapter: BaseAdapter<T>,
                                       itemClickListener: ItemClickListener<T>?,
                                       itemLongClickListener: ItemLongClickListener<T>?,
                                       layoutManager: RecyclerView.LayoutManager,
                                       itemDecoration: RecyclerView.ItemDecoration?,
                                       swipeItemHandler: SwipeItemHandler?,
                                       itemAnimator: RecyclerView.ItemAnimator?){

            if(itemDecoration != null){
                recyclerView.addItemDecoration(itemDecoration)
            }

            recyclerView.itemAnimator = itemAnimator ?: DefaultItemAnimator()
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            recyclerView.setHasFixedSize(true)

            if (itemClickListener != null) {
                adapter.setItemClickListener(itemClickListener)
            }

            if (itemLongClickListener != null) {
                adapter.setItemLongClickListener(itemLongClickListener)
            }

            if(swipeItemHandler != null){
                ViewUtils.addSwipeHandler(recyclerView, swipeItemHandler)
            }
        }
    }
}