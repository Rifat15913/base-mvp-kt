package io.diaryofrifat.code.rifbase.ui.base;

import android.app.Activity;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

public abstract class BasePresenter<V extends MvpView> implements LifecycleObserver, Presenter<V> {

    /**
     * Marks a class as a LifecycleObserver.
     * It does not have any methods, instead,
     * relies on OnLifecycleEvent annotated methods.
     * <p>
     * class TestObserver implements LifecycleObserver {
     *
     * @OnLifecycleEvent(ON_CREATE) void onCreated(LifecycleOwner source) {}
     * @OnLifecycleEvent(ON_ANY) void onAny(LifecycleOwner source, Event event) {}
     * }
     */

    private volatile V mMvpView;
    private Bundle mStateBundle;
    private Activity mActivity = null;

    @Override
    public void attachView(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    @Override
    public void attachLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    /**
     * Check MvpView attached with presenter or not
     *
     * @return boolean value is view attached state
     */
    public boolean isViewAttached() {
        return mMvpView != null;
    }

    /**
     * @return current MvpView
     */
    public V getMvpView() {
        return mMvpView;
    }

    /**
     * @return Bundle value
     */
    public Bundle getStateBundle() {
        return mStateBundle == null ? mStateBundle = new Bundle() : mStateBundle;
    }

    /**
     * Check MvpView attached with presenter, otherwise through exceptions
     *
     * @return void
     */
    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    @Override
    public void onPresenterDestroy() {
        if (mStateBundle != null && !mStateBundle.isEmpty()) mStateBundle.clear();
    }

    @Override
    public void onPresenterCreated() {
        //NO-OP
    }

    /**
     * @return Activity current active activity
     */
    public Activity getActivity() {
        return mActivity;
    }

    /**
     * To set current Activity on presenter
     *
     * @param activity Activity as param
     * @return void
     */
    protected void setActivity(Activity activity) {
        mActivity = activity;
    }

    @SuppressWarnings("TypeParameterUnusedInFormals")
    @NonNull
    protected <T extends AndroidViewModel> T getViewModel(@NonNull Class<T> modelClass) {
        return ViewModelProviders.of((FragmentActivity) mActivity).get(modelClass);
    }

    /**
     * To get current LifecycleOwner of active activity
     *
     * @return LifecycleOwner object
     */
    protected LifecycleOwner getLifecycleOwner() {
        return (LifecycleOwner) mActivity;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before requesting data to the Presenter");
        }
    }
}