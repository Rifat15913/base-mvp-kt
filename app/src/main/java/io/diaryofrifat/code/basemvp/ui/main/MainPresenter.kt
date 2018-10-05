package io.diaryofrifat.code.basemvp.ui.main

import io.diaryofrifat.code.basemvp.ui.base.BasePresenter
import io.diaryofrifat.code.utils.libs.ToastUtils

class MainPresenter : BasePresenter<MainMvpView>() {
    fun one() {
        ToastUtils.success("Happy")
    }
}