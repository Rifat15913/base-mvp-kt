package io.diaryofrifat.code.rifbase.ui.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import io.diaryofrifat.code.rifbase.R
import io.diaryofrifat.code.rifbase.databinding.ActivityMainBinding
import io.diaryofrifat.code.rifbase.ui.base.BaseActivity

class MainActivity : BaseActivity<MainMvpView, MainPresenter>(), MainMvpView {

    override val layoutId: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun initializePresenter(): MainPresenter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startUI() {

    }

    override fun stopUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Fields
     * */
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
