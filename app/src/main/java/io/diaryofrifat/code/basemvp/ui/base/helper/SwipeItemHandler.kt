package io.diaryofrifat.code.basemvp.ui.base.helper

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import io.diaryofrifat.code.basemvp.R
import io.diaryofrifat.code.basemvp.ui.base.helper.SwipeItemHandler.BackgroundMaterial.ICON
import io.diaryofrifat.code.utils.helper.DataUtils
import io.diaryofrifat.code.utils.helper.ViewUtils

class SwipeItemHandler(private val mSwipeDirection: SwipeDirection,
                       private val mBackgroundMaterial: BackgroundMaterial,
                       private val mBackgroundColorResourceId: Int,
                       private val mBackgroundMaterialResourceId: Int,
                       private val mCallback: SwipeCallback?) :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
    // Setting Right dragging by default

    init {
        val direction: Int = when (mSwipeDirection) {
            SwipeDirection.LEFT -> ItemTouchHelper.LEFT
            SwipeDirection.RIGHT -> ItemTouchHelper.RIGHT
        }

        setDefaultSwipeDirs(direction)
    }

    override fun onMove(recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder): Boolean {
        return false // As we are not moving our item, that's why we are putting false here
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        mCallback?.onSwipe(viewHolder)
    }

    override fun onChildDraw(canvas: Canvas, recyclerView: RecyclerView,
                             viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float,
                             actionState: Int, isCurrentlyActive: Boolean) {

        val itemView = viewHolder.itemView
        val backgroundLeftBound: Int
        val backgroundRightBound: Int
        val backgroundUpBound: Int
        val backgroundDownBound: Int
        var foregroundLeftBound = 0
        var foregroundRightBound = 0
        var foregroundUpBound = 0
        var foregroundDownBound = 0
        val foregroundMargin: Int
        val itemHeight = itemView.bottom - itemView.top

        val foreground: Drawable? =
                if (mBackgroundMaterial == ICON) {
                    ViewUtils.getDrawable(mBackgroundMaterialResourceId)
                } else {
                    TextDrawable.builder()
                            .beginConfig()
                            .fontSize(ViewUtils.getResources().getDimensionPixelSize(R.dimen.text_body))
                            .useFont(ViewUtils.getFont(R.font.roboto_regular))
                            .textColor(ViewUtils.getColor(R.color.white))
                            .bold()
                            .endConfig()
                            .buildRect(DataUtils.getString(mBackgroundMaterialResourceId),
                                    ViewUtils.getColor(R.color.white))
                }

        val background = ColorDrawable()
        background.color = ViewUtils.getColor(mBackgroundColorResourceId)

        when (mSwipeDirection) {
            SwipeDirection.LEFT -> {
                backgroundUpBound = itemView.top
                backgroundDownBound = itemView.bottom
                backgroundLeftBound = (itemView.right + dX).toInt()
                backgroundRightBound = itemView.right

                if (foreground != null) {
                    foregroundMargin = (itemHeight - foreground.intrinsicHeight) / 2
                    foregroundUpBound = itemView.top + foregroundMargin
                    foregroundDownBound = foregroundUpBound + foreground.intrinsicHeight
                    foregroundRightBound = itemView.right - foregroundMargin
                    foregroundLeftBound = foregroundRightBound - foreground.intrinsicWidth
                }
            }

            SwipeDirection.RIGHT -> {
                backgroundLeftBound = itemView.left
                backgroundUpBound = itemView.top
                backgroundDownBound = itemView.bottom
                backgroundRightBound = (itemView.left + dX).toInt()

                if (foreground != null) {
                    foregroundMargin = (itemHeight - foreground.intrinsicHeight) / 2
                    foregroundUpBound = itemView.top + foregroundMargin
                    foregroundDownBound = foregroundUpBound + foreground.intrinsicHeight
                    foregroundLeftBound = itemView.left + foregroundMargin
                    foregroundRightBound = foregroundLeftBound + foreground.intrinsicWidth
                }
            }
        }

        background.setBounds(backgroundLeftBound, backgroundUpBound,
                backgroundRightBound, backgroundDownBound)
        background.draw(canvas)

        if (foreground != null) {
            foreground.setBounds(foregroundLeftBound, foregroundUpBound,
                    foregroundRightBound, foregroundDownBound)
            foreground.draw(canvas)
        }

        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    /**
     * This method attaches the swipe handler to a particular RecyclerView
     *
     * @param recyclerView current RecyclerView
     * @return [ItemTouchHelper] touch helper of the RecyclerView
     * */
    fun attachToRecyclerView(recyclerView: RecyclerView): ItemTouchHelper {
        return ViewUtils.addSwipeHandler(recyclerView, this)
    }

    /**
     * Callback to get the state of swipe completed
     * */
    interface SwipeCallback {
        fun onSwipe(viewHolder: RecyclerView.ViewHolder)
    }

    /**
     * Enum to set swipe direction
     */
    enum class SwipeDirection {
        LEFT, RIGHT
    }

    /**
     * Enum to set background material
     */
    enum class BackgroundMaterial {
        TEXT, ICON
    }
}
