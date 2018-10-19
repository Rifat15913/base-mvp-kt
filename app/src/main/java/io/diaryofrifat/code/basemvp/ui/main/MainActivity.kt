package io.diaryofrifat.code.basemvp.ui.main

import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.diaryofrifat.code.basemvp.R
import io.diaryofrifat.code.basemvp.data.remote.retrophoto.RetroPhoto
import io.diaryofrifat.code.basemvp.databinding.ActivityMainBinding
import io.diaryofrifat.code.basemvp.ui.base.component.BaseActivity
import io.diaryofrifat.code.basemvp.ui.base.helper.LinearMarginItemDecoration
import io.diaryofrifat.code.utils.helper.ViewUtils
import io.diaryofrifat.code.utils.libs.ToastUtils

class MainActivity : BaseActivity<MainMvpView, MainPresenter>(), MainMvpView {
    /**
     * Fields
     * */
    private lateinit var mBinding: ActivityMainBinding

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override fun getActivityPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun getToolbarId(): Int? {
        return R.id.toolbar
    }

    override fun shouldShowBackIconAtToolbar(): Boolean {
        return false
    }

    override fun startUI() {
        mBinding = viewDataBinding as ActivityMainBinding

        ViewUtils.initializeRecyclerView(
                mBinding.recyclerViewPhotos,
                MainAdapter(),
                null,
                null,
                LinearLayoutManager(this),
                LinearMarginItemDecoration(ViewUtils.getPixel(R.dimen.margin_16)),
                null,
                DefaultItemAnimator()
        )

        presenter.test()
    }

    private fun getRecyclerView(): RecyclerView {
        return mBinding.recyclerViewPhotos
    }

    private fun getAdapter(): MainAdapter {
        return getRecyclerView().adapter as MainAdapter
    }

    override fun onFetchingData(list: List<RetroPhoto>) {
        getAdapter().clear()
        getAdapter().addItems(list)
        ToastUtils.success("Successful")
    }

    override fun stopUI() {

    }

    override fun onClick(view: View) {
        when (view.id) {

        }
    }
}
