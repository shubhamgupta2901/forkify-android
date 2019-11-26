package com.sg.forkify.components.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sg.forkify.R;
import com.sg.forkify.ui.base.BaseActivity;


public class SampleActivity extends BaseActivity {
    private SampleFragment mSampleFragment;

    public static Intent newIntent(Context mContext) {
        Intent mIntent = new Intent(mContext, SampleActivity.class);
        return mIntent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_generic;
    }

    @Override
    protected void onCreateActivity(Bundle bundle) {
        setSupportActionBar(this.mToolbar);
        this.mSampleFragment = SampleFragment.newInstance();
        showFragment(this.mSampleFragment,R.id.container,true);
    }
}
