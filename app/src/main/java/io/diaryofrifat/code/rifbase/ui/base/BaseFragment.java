package io.diaryofrifat.code.rifbase.ui.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import butterknife.ButterKnife;
import io.left.core.reflex.R;

/*
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : Anjan Debnath
 * * Date : 10/25/17
 * * Email : anjan@w3engineers.com
 * *
 * * Purpose: Abstract BaseFragment that every other fragment in this application must extend.
 * *
 * * Last Edited by : SUDIPTA KUMAR PAIK on 03/16/18.
 * * History:
 * * 1:
 * * 2:
 * *
 * * Last Reviewed by : SUDIPTA KUMAR PAIK on 03/16/18.
 * ****************************************************************************
 */

public abstract class BaseFragment<V extends MvpView, P extends BasePresenter<V>>
        extends Fragment implements MvpView, View.OnClickListener {

    /**
     * LifecycleRegistry is an implementation of Lifecycle that can handle multiple observers.
     * It is used by Fragments and Support Library Activities.
     * You can also directly use it if you have a custom LifecycleOwner.
     */
    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    protected P presenter;
    private int mDefaultValue = -1;
    private ViewDataBinding mViewDataBinding;

    /*
     * Child class have to implement this method. On this method you will pass the layout file of current fragment
     * */
    protected abstract int getLayoutId();

    /*
     * Child class can(optional) override this method. On this method you will pass the menu file of current fragment
     * */
    protected int getMenuId() {
        return mDefaultValue;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getMenuId() > mDefaultValue) {
            setHasOptionsMenu(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (getMenuId() > mDefaultValue) {
            inflater.inflate(getMenuId(), menu);
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int layoutId = getLayoutId();

        if (layoutId <= mDefaultValue) { // if default or invalid layout id, then no possibility to createDb view
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        return updateLayoutView(inflater, layoutId, container);
    }

    private View updateLayoutView(LayoutInflater inflater, int layoutId, ViewGroup container) {
        View view = null;

        try {
            mViewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false);

            view = mViewDataBinding.getRoot();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mViewDataBinding == null) {
            view = inflater.inflate(layoutId, container, false);
            ButterKnife.bind(getActivity());
        }

        return view;
    }

    @SuppressWarnings("unchecked")
    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BaseViewModel<V, P> viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);

        boolean isPresenterCreated = false;

        if (viewModel.getPresenter() == null) {
            viewModel.setPresenter(initPresenter());
            isPresenterCreated = true;
        }

        presenter = viewModel.getPresenter();
        presenter.attachLifecycle(getLifecycle());
        presenter.attachView((V) this);

        if (isPresenterCreated) presenter.onPresenterCreated();
    }

    @Override
    public void onStart() {
        super.onStart();

        presenter.setActivity(getActivity());

        this.startUI();
    }

    /*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    */

    @Override
    public void onClick(View view) {

    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycleRegistry;
    }

    /*
     * Child class have to implement this method. This method run on onStart lifecycle
     * */
    protected abstract void startUI();

    /*
     * Child class have to implement this method. This method run on onDestroy lifecycle
     * */
    protected abstract void stopUI();

    /*
     * Return current viewDataBinding
     * */
    protected ViewDataBinding getViewDataBinding() {
        return mViewDataBinding;
    }

    /*
        @CallSuper
        @Override
        public void onDestroyView() {
            super.onDestroyView();

            if (presenter != null) {
                presenter.detachLifecycle(getLifecycle());
                presenter.detachView();
            }
        }
    */

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.stopUI();

        if (presenter != null) {
            presenter.detachLifecycle(getLifecycle());
            presenter.detachView();
        }
    }

    private BaseActivity getBaseActivity() {
        return ((BaseActivity) getActivity());
    }

    private boolean isBaseActivityInstance() {
        return BaseActivity.class.isInstance(getActivity());
    }

    /**
     * To set title on toolbar
     *
     * @param title string value
     * @return void
     */
    protected void setTitle(String title) {
        if (isBaseActivityInstance()) getBaseActivity().setTitle(title);
    }

    /**
     * To set sub title on toolbar
     *
     * @param subtitle string value
     * @return void
     */
    protected void setSubtitle(String subtitle) {
        if (isBaseActivityInstance()) getBaseActivity().setSubtitle(subtitle);
    }

    /**
     * To set both title and subtitle in toolbar
     *
     * @param title    string value
     * @param subtitle string value
     * @return void
     */
    public void setToolbarText(String title, String subtitle) {
        if (isBaseActivityInstance()) getBaseActivity().setToolbarText(title, subtitle);
    }

    /**
     * To set click listener on any view, You can pass multiple view at a time
     *
     * @param views View as params
     * @return void
     */
    protected void setClickListener(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }

        //if (isBaseActivityInstance()) getBaseActivity().setClickListener(views);
    }

    protected RecyclerView initRecyclerView(Context context, RecyclerView recyclerView, BaseAdapter adapter) {
        if (recyclerView == null) return null;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        return recyclerView;
    }

    protected RecyclerView.Adapter getAdapter(RecyclerView recyclerView) {
        return recyclerView.getAdapter();
    }

    /**
     * To set animation on any view
     *
     * @param views View as params
     * @return void
     */
    protected void setAnimation(View... views) {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.grow_effect);

        for (View view : views) {
            view.startAnimation(animation);
        }

        //if (isBaseActivityInstance()) getBaseActivity().setAnimation(views);
    }

    /*
     * Child class have to implement this Generic method and will pass the current new object of presenter
     * */
    protected abstract P initPresenter();
}