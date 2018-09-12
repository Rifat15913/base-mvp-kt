package io.diaryofrifat.code.rifbase.ui.base

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<T>(private val viewDataBinding: ViewDataBinding)
    : RecyclerView.ViewHolder(viewDataBinding.root), View.OnClickListener {

    /**
     * Initializer block
     * */
    init {
        this.viewDataBinding.executePendingBindings()
    }

    /**
     * This method binds the item to item layout
     *
     * @param item model object
     * */
    abstract fun bind(item: T)

    /**
     * This method sets click listener to passed view/s
     *
     * @param views View as params
     */
    protected fun setClickListener(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    /**
     * This method is fired upon clicking on any view of the item layout
     *
     * @param view clicked view
     * */
    override fun onClick(view: View) {

    }
}