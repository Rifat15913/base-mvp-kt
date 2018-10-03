package io.diaryofrifat.code.rifbase.ui.base

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View

class LinearSeparatorItemDecoration(context: Context, separatorResourceId: Int) : RecyclerView.ItemDecoration() {

    /**
     * Fields
     */
    private val mSeparator: Drawable? = ContextCompat.getDrawable(context, separatorResourceId)

    /**
     * This overridden method provides each item offsets from here
     *
     * @param outRect item rectangle
     * @param view item view
     * @param parent the recycler view
     * @param state state of the recycler view
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) == 0) {
            return
        }

        outRect.top = mSeparator?.intrinsicHeight!!
    }

    /**
     * This method is fired upon drawing the item
     *
     * @param canvas drawing object
     * @param parent the recycler view
     * @param state state of the recycler view
     */
    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)

        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight
        val childCount = parent.childCount

        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val dividerTop = child.bottom + params.bottomMargin
            val dividerBottom = dividerTop + mSeparator!!.intrinsicHeight

            mSeparator.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            mSeparator.draw(canvas)
        }
    }
}
