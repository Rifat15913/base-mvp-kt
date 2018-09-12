package io.diaryofrifat.code.rifbase.ui.base;

import android.arch.lifecycle.ViewModel;

public class BaseViewModel<V extends MvpView, P extends Presenter<V>> extends ViewModel {
    private P presenter;

    P getPresenter() {
        return this.presenter;
    }

    void setPresenter(P presenter) {
        if (this.presenter == null) {
            this.presenter = presenter;
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        presenter.onPresenterDestroy();
        presenter = null;
    }
}
