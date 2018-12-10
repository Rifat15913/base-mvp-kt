package io.diaryofrifat.code.basemvp.ui.demo

import android.view.View
import io.diaryofrifat.code.basemvp.R
import io.diaryofrifat.code.basemvp.databinding.ActivityDemoBinding
import io.diaryofrifat.code.basemvp.ui.base.component.BaseActivity
import io.diaryofrifat.code.utils.helper.imagepicker.ImageInfo
import io.diaryofrifat.code.utils.helper.imagepicker.ImagePickerUtils
import timber.log.Timber

class DemoActivity : BaseActivity<DemoMvpView, DemoPresenter>() {

    private lateinit var mBinding: ActivityDemoBinding

    override val layoutResourceId: Int
        get() = R.layout.activity_demo

    override fun getActivityPresenter(): DemoPresenter {
        return DemoPresenter()
    }

    override fun startUI() {
        mBinding = viewDataBinding as ActivityDemoBinding

        setClickListener(mBinding.buttonDo)
    }

    override fun onClick(view: View) {
        super.onClick(view)

        when (view.id) {
            R.id.button_do -> {
                ImagePickerUtils.pickImage(this, object : ImagePickerUtils.Listener {
                    override fun onError(error: Throwable) {
                        Timber.e(error)
                    }

                    override fun onSuccess(imageInfo: ImageInfo) {
                        Timber.e(imageInfo.isTakenByCamera.toString())
                        Timber.e(imageInfo.imageUri.toString())
                    }
                })
            }
        }
    }

    override fun stopUI() {

    }
}