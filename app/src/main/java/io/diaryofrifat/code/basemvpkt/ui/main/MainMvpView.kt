package io.diaryofrifat.code.basemvpkt.ui.main

import io.diaryofrifat.code.basemvpkt.data.remote.retrophoto.RetroPhoto
import io.diaryofrifat.code.basemvpkt.ui.base.callback.MvpView

interface MainMvpView : MvpView {
    fun onFetchingData(list: List<RetroPhoto>)
}