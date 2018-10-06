package io.diaryofrifat.code.basemvp.ui.main

import android.view.View
import io.diaryofrifat.code.basemvp.R
import io.diaryofrifat.code.basemvp.databinding.ActivityMainBinding
import io.diaryofrifat.code.basemvp.ui.base.BaseActivity
import io.diaryofrifat.code.utils.libs.GlideUtils

class MainActivity : BaseActivity<MainMvpView, MainPresenter>(), MainMvpView {

    /**
     * Fields
     * */
    private lateinit var mBinding: ActivityMainBinding

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getActivityPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun startUI() {
        mBinding = viewDataBinding as ActivityMainBinding
        GlideUtils.normal(mBinding.imageViewDemo,
                "https://i.pinimg.com/originals/46/d9/15/46d915f51e10fccfbce9d6cb5df326b1.jpg")
        setClickListener(mBinding.imageViewDemo)
    }

    override fun stopUI() {

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.image_view_demo -> presenter.test()
        }
    }
}
