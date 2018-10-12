package io.diaryofrifat.code.basemvp.ui.base.helper

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @see "If this item decoration is added to any [RecyclerView], then we need to remove it at
 * onStop() of the fragment/activity. Otherwise, the margin gets incremented each time."
 * */
class LinearMarginItemDecoration : RecyclerView.ItemDecoration {

    /**
     * Fields
     */
    private var mTop: Int = 0
    private var mBottom: Int = 0
    private var mLeft: Int = 0
    private var mRight: Int = 0
    private var mOuterLeftRightSpace: Int = 0
    private var mOuterTopBottomSpace: Int = 0
    private var mInnerSpace: Int = 0
    private var mIsOuterInnerMarginDifferent: Boolean = false

    /**
     * Constructors
     */
    constructor(space: Int) {
        mTop = space
        mBottom = space
        mLeft = space
        mRight = space
    }

    constructor(topBottomSpace: Int, leftRightSpace: Int) {
        mTop = topBottomSpace
        mBottom = topBottomSpace
        mLeft = leftRightSpace
        mRight = leftRightSpace
    }

    constructor(outerLeftRightSpace: Int, outerTopBottomSpace: Int, innerSpace: Int) {
        mIsOuterInnerMarginDifferent = true
        mOuterLeftRightSpace = outerLeftRightSpace
        mOuterTopBottomSpace = outerTopBottomSpace
        mInnerSpace = innerSpace
    }

    constructor(top: Int, bottom: Int, left: Int, right: Int) {
        mTop = top
        mBottom = bottom
        mLeft = left
        mRight = right
    }

    /**
     * This overridden method provides each item offsets from here
     *
     * @param outRect item rectangle
     * @param view    item view
     * @param parent  the recycler view
     * @param state   state of the recycler view
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (mIsOuterInnerMarginDifferent) {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = mOuterTopBottomSpace
            }

            if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
                outRect.bottom = mOuterTopBottomSpace
            } else {
                outRect.bottom = mInnerSpace
            }

            outRect.left = mOuterLeftRightSpace
            outRect.right = mOuterLeftRightSpace
        } else {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = mTop
            }

            outRect.bottom = mBottom
            outRect.left = mLeft
            outRect.right = mRight
        }
    }
}
