package io.diaryofrifat.code.rifbase.ui.base

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import butterknife.ButterKnife
import timber.log.Timber

abstract class BaseActivity<V : MvpView, P : BasePresenter<V>>
    : AppCompatActivity(), MvpView, View.OnClickListener, View.OnFocusChangeListener {

    companion object {
        private const val INVALID_ID = -1

        /**
         * This method runs current activity
         *
         * @param context UI context
         * @param intent intent for current activity
         * */
        fun runCurrentActivity(context: Context, intent: Intent) {
            context.startActivity(intent)
        }
    }

    /**
     * Fields
     * */
    // Child class has to pass it's layout resource id via this field
    protected abstract val layoutId: Int
    // Child class data binding object for views
    protected var viewDataBinding: ViewDataBinding? = null
        private set
    // Child class will pass the toolbar id via this field if needed
    protected val toolbarId = INVALID_ID
    protected var presenter: P? = null

    /**
     * The methods to be implemented by the child class (Abstract methods)
     * */
    protected abstract fun initializePresenter(): P

    /**
     * LifecycleRegistry is an implementation of Lifecycle that can handle multiple observers.
     * It is used by Fragments and Support Library Activities.
     * You can also directly use it if you have a custom LifecycleOwner.
     */
    private val mLifecycleRegistry = LifecycleRegistry(this)

    //FixMe: have to enable for commit Fragment in Activity
    /*
     * Get current running fragment
     * */
    protected var currentFragment: BaseFragment<*, *>
        private set
    /*
     * To get the current menu. It will return current menu if you set it. Otherwise return null.
     * */
    protected var menu: Menu? = null
        private set

    /**
     * its built in method in Fragment Activity
     * that is extends by AppCompatActivity
     *
     * @return
     */
    override fun getLifecycle(): LifecycleRegistry {
        return mLifecycleRegistry
    }

    /*
     * Child class can(optional) override this method. On this method you will pass the menu file of current activity
     * */
    protected fun getMenuId(): Int {
        return toolbarId
    }

    /*
     * Child class can(optional) override this method. On this method you will pass the color id
     * */
    protected fun statusBarColor(): Int {
        return toolbarId
    }

    /*FixMe: have to enable for on Resume state
    @Override
    protected void onResume() {
        super.onResume();
        onResumeUI();
    }
    protected abstract void onResumeUI();
    */

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutId = layoutId
        if (layoutId > INVALID_ID) {
            updateLayoutView(layoutId)

            val viewModel = ViewModelProviders.of(this)
                    .get(BaseViewModel<V, P>().javaClass)
            var isPresenterCreated = false

            if (viewModel.getPresenter() == null) {
                viewModel.setPresenter(initializePresenter())
                isPresenterCreated = true
            }

            presenter = viewModel.getPresenter()
            presenter?.attachLifecycle(lifecycle)
            presenter?.attachView(this as V)

            if (isPresenterCreated){
                presenter?.onPresenterCreated()
            }

            val toolbarId = toolbarId

            setStatusBarColor()

            if (toolbarId > this.toolbarId) {
                val toolbar = findViewById<Toolbar>(toolbarId)

                if (toolbar != null) {
                    setSupportActionBar(toolbar)
                }

                val actionBar = supportActionBar
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true)
                    actionBar.setDisplayShowHomeEnabled(true)
                }
            }
        }

        this.startUI()
    }

    private fun updateLayoutView(layoutId: Int) {
        try {
            viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        } catch (e: Exception) {
            Timber.e(e)
        }

        if (viewDataBinding == null) {
            setContentView(layoutId)
            ButterKnife.bind(this)
        }
    }

    protected fun enableHomeButton(isShow: Boolean) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(isShow)
            actionBar.setDisplayShowHomeEnabled(isShow)
        }
    }

    override fun onStart() {
        super.onStart()

        presenter!!.activity = this
    }


    /*
     * Child class have to implement this method. This method run on onStart lifecycle
     * */
    protected abstract fun startUI()

    /*
     * Child class have to implement this method. This method run on onDestroy lifecycle
     * */
    protected abstract fun stopUI()

    /*private void setStatusBarColor() {

        int statusBarColor = statusBarColor();

        if (statusBarColor > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = this.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                }
                window.setStatusBarColor(ContextCompat.getColor(this, statusBarColor));
            }
        }
    }*/

    private fun setStatusBarColor() {
        val statusBarColor = statusBarColor()

        if (statusBarColor > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = this.window
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN)
                window.statusBarColor = ContextCompat.getColor(this, statusBarColor)
            }
        }
    }

    override fun onClick(view: View) {}

    override fun onFocusChange(view: View, b: Boolean) {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (getMenuId() > toolbarId) {
            menuInflater.inflate(getMenuId(), menu)
            this.menu = menu
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    protected fun refreshMenu() {
        supportInvalidateOptionsMenu()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.stopUI()

        if (presenter != null) {
            presenter!!.detachLifecycle(lifecycle)
            presenter!!.detachView()
        }
    }

    /**
     * To set title on toolbar
     *
     * @param title string value
     * @return void
     */
    fun setTitle(title: String) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = title
        }
    }

    /**
     * To set sub title on toolbar
     *
     * @param subtitle string value
     * @return void
     */
    fun setSubtitle(subtitle: String) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.subtitle = subtitle
        }
    }

    /**
     * To set both title and subtitle in toolbar
     *
     * @param title    string value
     * @param subtitle string value
     * @return void
     */
    fun setToolbarText(title: String, subtitle: String) {
        val actionBar = supportActionBar

        if (actionBar != null) {
            actionBar.title = title
            actionBar.subtitle = subtitle
        }
    }

    /**
     * To set click listener on any view, You can pass multiple view at a time
     *
     * @param views View as params
     * @return void
     */
    protected fun setClickListener(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    /**
     * To set animation on any view
     *
     * @param views View as params
     * @return void
     */
    protected fun setAnimation(vararg views: View) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.grow_effect)

        for (view in views) {
            view.startAnimation(animation)
        }
    }

    /**
     * Commit child fragment of BaseFragment on a frameLayout
     *
     * @param viewId       int value
     * @param baseFragment BaseFragment object
     * @return void
     */
    protected fun commitFragment(viewId: Int, baseFragment: BaseFragment<*, *>) {
        supportFragmentManager
                .beginTransaction()
                .replace(viewId, baseFragment, baseFragment.javaClass.name)
                .commit()

        currentFragment = baseFragment
    }
}
