package io.diaryofrifat.code.basemvp.ui.base.callback

import android.view.View
import io.diaryofrifat.code.basemvp.ui.base.component.BaseAdapter

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

    /**
     * This method sets this long click listener to the adapter
     *
     * @param adapter RecyclerView adapter
     * */
    fun setAdapter(adapter: BaseAdapter<T>) {
        adapter.setItemLongClickListener(this)
    }
}