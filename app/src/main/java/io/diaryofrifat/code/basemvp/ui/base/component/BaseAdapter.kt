package io.diaryofrifat.code.basemvp.ui.base.component

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.diaryofrifat.code.basemvp.R
import io.diaryofrifat.code.basemvp.ui.base.callback.ItemClickListener
import io.diaryofrifat.code.basemvp.ui.base.callback.ItemLongClickListener
import io.diaryofrifat.code.utils.helper.DataUtils
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    /**
     * fields
     * */
    protected var mItemClickListener: ItemClickListener<T>? = null
    protected var mItemLongClickListener: ItemLongClickListener<T>? = null
    private var mItemList: MutableList<T>
    private var mRxAdapterSize: BehaviorSubject<Int>
    private lateinit var mRecyclerView: RecyclerView

    /**
     * Initialization block
     * */
    init {
        mItemList = ArrayList()
        mRxAdapterSize = BehaviorSubject.create()
        mRxAdapterSize.onNext(itemCount)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = recyclerView
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        mRxAdapterSize.onComplete()
        super.onDetachedFromRecyclerView(recyclerView)
    }

    /**
     * Getter method for stream of getting adapter size
     *
     * @return [Flowable] rx stream of adapter size
     * */
    fun dataChanges(): Flowable<Int> {
        return mRxAdapterSize
                .toFlowable(BackpressureStrategy.LATEST)
                ?: Flowable.error(Throwable(DataUtils.getString(R.string.error_adapter_size_is_null)))
    }

    /**
     * This method sets item long click listener
     *
     * @param listener item long click listener
     */
    fun setItemLongClickListener(listener: ItemLongClickListener<T>) {
        this.mItemLongClickListener = listener
    }

    /**
     * This method sets item click listener
     *
     * @param listener item click listener
     */
    fun setItemClickListener(listener: ItemClickListener<T>) {
        this.mItemClickListener = listener
    }

    /**
     * This method sets item list externally
     *
     * @param itemList list of items
     * */
    fun setItems(itemList: MutableList<T>) {
        mItemList = itemList
        mRxAdapterSize.onNext(itemCount)
    }

    /**
     * This method provides list of items
     *
     * @return [MutableList] item list
     */
    fun getItems(): MutableList<T> {
        return mItemList
    }

    /**
     * This method provides an item from the list by position
     *
     * @param position adapter position
     * @return [T] model object
     */
    fun getItem(position: Int): T? {
        return if (position < 0 || position >= mItemList.size) null else mItemList[position]
    }

    /**
     * This method provides the count of items
     *
     * @return [Int] count of items
     * */
    override fun getItemCount(): Int {
        return mItemList.size
    }

    /**
     * This method returns position of the item in the list
     *
     * @param item model object
     * @return [Int] item position
     */
    fun getItemPosition(item: T): Int {
        return mItemList.indexOf(item)
    }

    /**
     * This method finds an item if exists in the item list
     *
     * @param item model object
     * @return [T] model object
     */
    fun findItem(item: T): T? {
        for (itemFromList in mItemList) {
            if (isEqual(item, itemFromList)) {
                return itemFromList
            }
        }

        return null
    }

    /**
     * This method adds an item to the item list
     *
     * @param item model object
     * @return [Int] added position
     */
    fun addItem(item: T): Int {
        val itemFromList = findItem(item)

        if (itemFromList == null) {
            mItemList.add(item)
            notifyItemInserted(mItemList.size - 1)
            mRxAdapterSize.onNext(itemCount)
            return mItemList.size - 1
        }

        return updateItem(item, itemFromList)
    }

    /**
     * This method adds an item to specific position in the item list
     *
     * @param item model object
     * @param position item adding position
     */
    fun addItem(item: T, position: Int) {
        mItemList.add(position, item)
        notifyItemInserted(position)
        mRxAdapterSize.onNext(itemCount)
    }

    /**
     * This method adds a collection to the item list
     *
     * @param items list of model objects
     */
    fun addItems(items: List<T>) {
        for (item in items) {
            addItem(item)
        }
    }

    /**
     * This method updates an item in the item list
     *
     * @param oldItem old model object
     * @param newItem new model object
     * @return [Int] updated model object position
     */
    fun updateItem(oldItem: T, newItem: T): Int {
        val oldItemIndex = getItemPosition(oldItem)
        mItemList[oldItemIndex] = newItem
        notifyItemChanged(oldItemIndex)
        mRxAdapterSize.onNext(itemCount)
        return oldItemIndex
    }

    /**
     * This method removes an item from the item list
     *
     * @param item model object
     */
    fun removeItem(item: T) {
        val itemIndex = getItemPosition(item)
        if (itemIndex < 0 || itemIndex >= mItemList.size) return
        mItemList.removeAt(itemIndex)
        notifyItemRemoved(itemIndex)
        mRxAdapterSize.onNext(itemCount)
    }

    /**
     * This method clears current item list
     */
    fun clear() {
        mItemList.clear()
        notifyDataSetChanged()
        mRxAdapterSize.onNext(itemCount)
    }

    /**
     * This method provides if two model objects are equal or not
     *
     * @param left first model object
     * @param right second model object
     * @return [Boolean] if the model objects are equal
     * */
    abstract fun isEqual(left: T, right: T): Boolean

    /**
     * This method prepares a new view holder for the item
     *
     * @param parent parent ViewGroup
     * @param viewType type of the view
     * @return [BaseViewHolder] a new view holder
     * */
    abstract fun newViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T>

    /**
     * This method creates a new view holder for the item
     *
     * @param parent parent ViewGroup
     * @param viewType type of the view
     * @return [BaseViewHolder] a new view holder
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return newViewHolder(parent, viewType)
    }

    /**
     * This method binds the item data to the view holder
     *
     * @param holder item view holder
     * @param position adapter position
     * */
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }

    /**
     * This method provides a data binding object
     *
     * @param parent parent ViewGroup
     * @param itemLayout layout id of the item
     * @return [ViewDataBinding] data binding object
     * */
    fun inflate(parent: ViewGroup, itemLayout: Int): ViewDataBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                itemLayout, parent, false)
    }
}