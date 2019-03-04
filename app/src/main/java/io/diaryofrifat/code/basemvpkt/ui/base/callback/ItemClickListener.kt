package io.diaryofrifat.code.basemvpkt.ui.base.callback

import android.view.View
import io.diaryofrifat.code.basemvpkt.ui.base.component.BaseAdapter

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

    /**
     * This method sets this click listener to the adapter
     *
     * @param adapter RecyclerView adapter
     * */
    fun setAdapter(adapter: BaseAdapter<T>) {
        adapter.setItemClickListener(this)
    }
}
