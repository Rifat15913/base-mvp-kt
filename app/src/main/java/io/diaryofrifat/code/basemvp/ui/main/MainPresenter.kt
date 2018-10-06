package io.diaryofrifat.code.basemvp.ui.main

import io.diaryofrifat.code.basemvp.data.BaseMvpRepository
import io.diaryofrifat.code.basemvp.data.local.model.user.UserEntity
import io.diaryofrifat.code.basemvp.ui.base.BasePresenter
import io.diaryofrifat.code.utils.libs.ToastUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

class MainPresenter : BasePresenter<MainMvpView>() {
    fun test() {
        compositeDisposable.add(
                BaseMvpRepository.on().insertUserToDatabase(UserEntity("Rifat", "id"))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableCompletableObserver() {
                            override fun onError(e: Throwable) {
                                ToastUtils.error("Unhappy. Reason - ${e.message}")
                            }

                            override fun onComplete() {
                                ToastUtils.success("Happy")
                            }
                        }))
    }
}