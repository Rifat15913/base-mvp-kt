package io.diaryofrifat.code.rifbase.ui.main

import io.diaryofrifat.code.rifbase.ui.base.BasePresenter
import io.diaryofrifat.code.utils.libs.ToastUtils

class MainPresenter : BasePresenter<MainMvpView>() {
    fun one() {
        ToastUtils.success("Happy")
    }
}