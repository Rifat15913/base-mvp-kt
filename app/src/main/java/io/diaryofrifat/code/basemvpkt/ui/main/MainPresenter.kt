package io.diaryofrifat.code.basemvpkt.ui.main

import io.diaryofrifat.code.basemvpkt.data.BaseRepository
import io.diaryofrifat.code.basemvpkt.ui.base.component.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainPresenter : BasePresenter<MainMvpView>() {
    fun test() {
        compositeDisposable.add(
                BaseRepository.on().getAllPhotosFromServer()
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