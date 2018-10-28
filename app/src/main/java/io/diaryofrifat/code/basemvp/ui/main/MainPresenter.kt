package io.diaryofrifat.code.basemvp.ui.main

import io.diaryofrifat.code.basemvp.data.BaseMvpRepository
import io.diaryofrifat.code.basemvp.ui.base.component.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainPresenter : BasePresenter<MainMvpView>() {
    fun test() {
        compositeDisposable.add(
                BaseMvpRepository.on().getAllPhotosFromServer()
                        .map { it.subList(0, 9) }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            Timber.d(it.size.toString())
                            mvpView?.onFetchingData(it)
                        }, {
                            Timber.d(it)
                        }))
    }
}