package io.diaryofrifat.code.basemvp.ui.main

import io.diaryofrifat.code.basemvp.data.BaseMvpRepository
import io.diaryofrifat.code.basemvp.ui.base.component.BasePresenter
import io.diaryofrifat.code.utils.libs.ToastUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainPresenter : BasePresenter<MainMvpView>() {
    fun test() {
        compositeDisposable.add(
                BaseMvpRepository.on().getAllPhotosFromServer()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            ToastUtils.success("Successful")
                            Timber.d(it.toString())
                        }, {
                            ToastUtils.error("Error happened")
                            Timber.d(it)
                        }))
    }
}