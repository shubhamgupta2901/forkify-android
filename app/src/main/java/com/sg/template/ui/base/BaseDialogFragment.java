package com.sg.template.ui.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;


import com.sg.template.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseDialogFragment extends DialogFragment {
    protected String TAG;
    private BaseActivity mActivity;
    Unbinder mUnbinder;

    public abstract void onAttachFragment(Activity activity);

    public abstract void onCreateFragment(Bundle bundle);

    public abstract View onCreateViewFragment(View view);

    public abstract int onInflateViewFragment();

    public BaseDialogFragment() {
        this.TAG = getClass().getSimpleName();
    }

    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.5f;
        //TODO: HACK Added For RatingDialogFragment, TO be revised all child classes of BaseDialogFragment
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        windowParams.flags |= 2;
        window.setAttributes(windowParams);

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.debug(this.TAG, "* onCreateFragment");
        onCreateFragment(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.debug(this.TAG, "* onCreateViewFragment");
        View view = inflater.inflate(onInflateViewFragment(), container, false);
        mUnbinder= ButterKnife.bind((Object) this, view);
        view = onCreateViewFragment(view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getDialog().getWindow().requestFeature(1);
        return view;
    }

    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
        onAttachFragment(mActivity);
    }

    public void onResume() {
        super.onResume();
        LogUtils.debug(this.TAG, "* onResumeFragment");
    }

    public void onDestroy() {
        super.onDestroy();
        LogUtils.debug(this.TAG, "%* onDestroyFragment");
        mUnbinder.unbind();
    }

    public void onPause() {
        super.onPause();
        LogUtils.debug(this.TAG, "* onPauseFragment");
    }

    protected void showToast(String text) {
        this.mActivity.showToast(text);
    }

    protected void showToast(int res) {
        this.mActivity.showToast(res);
    }
}
