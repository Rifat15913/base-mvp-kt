package io.diaryofrifat.code.basemvpkt.ui.base.component

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelProviders
import io.diaryofrifat.code.basemvpkt.ui.base.callback.MvpView
import io.diaryofrifat.code.utils.helper.imagepicker.ImagePickerUtils
import io.diaryofrifat.code.utils.libs.ImageCropperUtils
import timber.log.Timber

abstract class BaseFragment<V : MvpView, P : BasePresenter<V>> : Fragment(),
        MvpView, View.OnClickListener {

    /**
     * LifecycleRegistry is an implementation of Lifecycle that can handle multiple observers.
     * It is used by Fragments and Support Library Activities.
     * You can also directly use it if you have a custom LifecycleOwner.
     */
    private val mLifecycleRegistry = LifecycleRegistry(this)

    /**
     * Fields
     * */
    // Child class has to pass it's layout resource id via this field
    protected abstract val layoutId: Int
    // Child class data binding object for views
    protected var viewDataBinding: ViewDataBinding? = null
        private set
    protected lateinit var presenter: P

    // This fragment context
    protected var mContext: Context? = null

    /**
     * The methods to be implemented by the child class (Abstract methods)
     * */
    // This method initializes the presenter
    protected abstract fun getFragmentPresenter(): P

    // This method is called when activity initialization gets completed
    protected abstract fun startUI()

    // This method is called when activity gets destroyed
    protected abstract fun stopUI()

    /**
     * Optional to be overridden methods
     * */
    // Child class will pass the menu id by this method
    protected open fun getMenuId(): Int {
        return INVALID_ID
    }

    @Suppress("UNCHECKED_CAST")
    private val baseActivity: BaseActivity<*, *>?
        get() = activity as BaseActivity<*, *>?

    private val isBaseActivityInstance: Boolean
        get() = BaseActivity::class.java.isInstance(activity)

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context != null) {
            mContext = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getMenuId() > INVALID_ID) {
            setHasOptionsMenu(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        if (getMenuId() > INVALID_ID) {
            inflater!!.inflate(getMenuId(), menu)
            super.onCreateOptionsMenu(menu, inflater)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return if (layoutId > INVALID_ID) {
            initializeLayout(inflater, layoutId, container)
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    /**
     * This method initializes the layout to the fragment
     *
     * @param inflater to inflate the layout
     * @param layoutId id of the layout
     * @param container ViewGroup of the layout
     * @return [View] inflated layout view
     * */
    private fun initializeLayout(inflater: LayoutInflater, layoutId: Int, container: ViewGroup?): View? {
        var view: View? = null

        try {
            viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            view = viewDataBinding?.root
        } catch (e: Exception) {
            Timber.e(e)
        }

        if (viewDataBinding == null) {
            view = inflater.inflate(layoutId, container, false)
        }

        return view
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializePresenter()
    }

    /**
     * This method initializes activity presenter
     * */
    private fun initializePresenter() {
        val viewModel = ViewModelProviders.of(this)
                .get(BaseViewModel<V, P>().javaClass)

        var isPresenterCreated = false

        if (viewModel.getPresenter() == null) {
            viewModel.setPresenter(getFragmentPresenter())
            isPresenterCreated = true
        }

        presenter = viewModel.getPresenter()!!
        presenter.attachLifecycle(mLifecycleRegistry)
        @Suppress("UNCHECKED_CAST")
        presenter.attachView(this as V)

        if (isPresenterCreated) {
            presenter.onPresenterCreated()
        }

        presenter.activity = activity
    }

    override fun onStart() {
        super.onStart()
        this.startUI()
    }

    override fun onClick(view: View) {

    }

    override fun onStop() {
        super.onStop()
        presenter.compositeDisposable.clear()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        ImagePickerUtils.onActivityResult(this, requestCode, resultCode, data)
        ImageCropperUtils.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.stopUI()

        presenter.detachLifecycle(mLifecycleRegistry)
        presenter.detachView()
    }

    /**
     * This method sets title of the toolbar
     *
     * @param title toolbar title
     * */
    fun setTitle(title: String) {
        if (isBaseActivityInstance) {
            baseActivity?.setTitle(title)
        }
    }

    /**
     * This method sets subtitle of the toolbar
     *
     * @param subtitle toolbar subtitle
     * */
    fun setSubtitle(subtitle: String) {
        if (isBaseActivityInstance) {
            baseActivity?.setSubtitle(subtitle)
        }
    }

    /**
     * This method sets both title and subtitle of toolbar
     *
     * @param title    toolbar title
     * @param subtitle toolbar subtitle
     * */
    fun setToolbarText(title: String, subtitle: String) {
        if (isBaseActivityInstance) {
            baseActivity?.setToolbarText(title, subtitle)
        }
    }

    /**
     * This method sets click listener to multiple views
     *
     * @param views multiple views
     * */
    protected fun setClickListener(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    /**
     * This method sets animation to multiple views
     *
     * @param animationResourceId resource id of an animation
     * @param views multiple views
     * */
    protected fun setAnimation(animationResourceId: Int, vararg views: View) {
        if (context != null) {
            val animation = AnimationUtils.loadAnimation(context, animationResourceId)

            for (view in views) {
                view.startAnimation(animation)
            }
        }
    }

    companion object {
        private const val INVALID_ID = -1
    }
}