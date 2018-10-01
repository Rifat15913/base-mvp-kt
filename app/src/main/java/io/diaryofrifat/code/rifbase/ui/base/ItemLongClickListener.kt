package io.diaryofrifat.code.rifbase.ui.base

import android.view.View

interface ItemLongClickListener<T> {
    /**
     * This method is called when an item gets long clicked.
     *
     * @param view clicked view
     * @param item model object
     */
    fun onItemLongClick(view: View, item: T) {}

    /**
     * This method is called when an item gets long clicked.
     *
     * @param view clicked view
     * @param item model object
     * @param position model object position in the list
     */
    fun onItemLongClick(view: View, item: T, position: Int) {}
}