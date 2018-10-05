package io.diaryofrifat.code.rifbase.ui.main

import android.os.Bundle
import io.diaryofrifat.code.rifbase.R
import io.diaryofrifat.code.rifbase.databinding.ActivityMainBinding
import io.diaryofrifat.code.rifbase.ui.base.BaseActivity
import io.diaryofrifat.code.utils.libs.GlideUtils

class MainActivity : BaseActivity<MainMvpView, MainPresenter>(), MainMvpView {

    /**
     * Fields
     * */
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding as ActivityMainBinding
    }

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getActivityPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun startUI() {
        GlideUtils.normal(mBinding.imageViewDemo,
                "https://i.pinimg.com/originals/46/d9/15/46d915f51e10fccfbce9d6cb5df326b1.jpg")
    }

    override fun stopUI() {

    }
}
