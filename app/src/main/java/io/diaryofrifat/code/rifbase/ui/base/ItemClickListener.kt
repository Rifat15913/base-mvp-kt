package io.diaryofrifat.code.rifbase.ui.base

import android.view.View

interface ItemClickListener<T> {
    /**
     * This method is called when an item gets clicked.
     *
     * @param view clicked view
     * @param item model object
     */
    fun onItemClick(view: View, item: T) {}

    /**
     * This method is called when an item gets clicked.
     *
     * @param view clicked view
     * @param item model object
     * @param position model object position in the list
     */
    fun onItemClick(view: View, item: T, position: Int) {}
}
