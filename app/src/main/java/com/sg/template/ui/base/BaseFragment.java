package com.sg.template.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;


import com.sg.template.ui.dialogs.LoadingDialog;
import com.sg.template.utils.LogUtils;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment {
    protected String TAG;
    private BaseActivity mActivity;
    public LoadingDialog loadingDialog;

    private View view;
    protected Boolean wasPause;

    public abstract void onAttachFragment(Activity activity);

    public abstract void onCreateFragment(Bundle bundle);

    public abstract View onCreateViewFragment(View view, Bundle savedInstanceState);

    public abstract int onInflateViewFragment();

    public abstract String getFragmentTitle();

    public BaseFragment() {
        this.TAG = LogUtils.makeLogTag(getClass());
        this.wasPause = Boolean.valueOf(false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.debug(this.TAG, "* onCreateFragment");

        onCreateFragment(savedInstanceState);
        LogUtils.info(this.TAG, "Setting screen name: " + this.TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.debug(this.TAG, "* onCreateViewFragment");
        this.view = inflater.inflate(onInflateViewFragment(), container, false);
        ButterKnife.bind(this, view);
        this.view = onCreateViewFragment(view, savedInstanceState);
        return this.view;
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.debug(this.TAG, "* onStartFragment");
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
        onAttachFragment((Activity) context);
    }

    @Override
    public void onResume() {
        super.onResume();
        setFragmentTitle(getFragmentTitle());
        LogUtils.debug(this.TAG, "* onResumeFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.debug(this.TAG, "* onDestroyFragment");

    }

    @Override
    public void onPause() {
        super.onPause();
        this.wasPause = Boolean.valueOf(true);
        LogUtils.debug(this.TAG, "* onPauseFragment");
        hideSoftKeyboard();
    }

    protected void setFragmentTitle(String fragmentTitle) {
        ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (mActionBar != null && fragmentTitle != null)
            mActionBar.setTitle(fragmentTitle);
    }


    protected void showToast(String text) {
        this.mActivity.showToast(text);
    }

    protected void showToast(int res) {
        this.mActivity.showToast(res);
    }

    protected void showLoadingDialog() {
        if (this.loadingDialog == null) {
            this.loadingDialog = new LoadingDialog(getActivity());
            this.loadingDialog.setCancelable(false);
        }
        this.loadingDialog.show();

    }


    protected void closeLoadingDialog() {
        if (this.loadingDialog != null && this.loadingDialog.isShowing()) {
            this.loadingDialog.dismiss();
        }
    }


    public void hideSoftKeyboard() {
        if (getActivity().getCurrentFocus() != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }


}
