package io.diaryofrifat.code.rifbase.ui.base

import android.arch.lifecycle.ViewModel

class BaseViewModel<V : MvpView, P : Presenter<V>> : ViewModel() {
    /**
     * Fields
     * */
    private var presenter: P? = null

    /**
     * Getter and setter methods for presenter
     * */
    internal fun getPresenter(): P? {
        return this.presenter
    }

    internal fun setPresenter(presenter: P) {
        if (this.presenter == null) {
            this.presenter = presenter
        }
    }

    /**
     * This method is fired when to clear the presenter
     * */
    override fun onCleared() {
        super.onCleared()
        presenter?.onPresenterDestroy()
        presenter = null
    }
}
