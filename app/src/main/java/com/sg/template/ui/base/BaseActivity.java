

package com.sg.template.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.sg.template.R;
import com.sg.template.ui.dialogs.LoadingDialog;
import com.sg.template.utils.LogUtils;

import java.util.Locale;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/**
 * Created by Shubham on 12/9/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG;
    protected Toolbar mToolbar;
    protected LoadingDialog loadingDialog;

    protected abstract int getContentViewId();

    protected abstract void onCreateActivity(Bundle bundle);

    public BaseActivity() {
        this.TAG = getClass().getSimpleName();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.debug(this.TAG, "* onCreateActivity");
        setContentView(getContentViewId());
        ButterKnife.bind((Activity) this);
        this.mToolbar = (Toolbar) ButterKnife.findById((Activity) this, (int) R.id.app_bar);
        if (this.mToolbar != null) {
            setSupportActionBar(this.mToolbar);
        }
        onCreateActivity(savedInstanceState);
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }

    private void updateLocale(String stringLocale) {
        Locale locale = new Locale(stringLocale);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    protected void onResume() {
        super.onResume();
        LogUtils.debug(this.TAG, "* onResumeActivity");

    }

    protected void onDestroy() {
        super.onDestroy();
        LogUtils.debug(this.TAG, "* onDestroyActivity");
    }

    protected void onPause() {
        super.onPause();
        LogUtils.debug(this.TAG, "* onPause");
        hideSoftKeyboard();
    }

    public void showToast(String text) {
        if (text != null) {
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
            LogUtils.debug(this.TAG, "toast: " + text);
        }
    }

    public void showToast(int res) {
        showToast(getString(res));
    }

    protected void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
       /* if (!(this instanceof TerrierMainActivity) && id == android.R.id.home) {
            onBackPressed();
        }*/
        return super.onOptionsItemSelected(item);
    }

    protected void showFragment(Fragment fragment, int containerViewId, boolean isFirstFragmentInContainer) {
        if (isFirstFragmentInContainer) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(containerViewId, fragment, null);
            fragmentTransaction.commitAllowingStateLoss();
        } else {
            String fragmentTag = fragment.getClass().getSimpleName();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(containerViewId, fragment, fragmentTag);
            fragmentTransaction.addToBackStack(fragmentTag);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }


    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void showLoadingDialog() {
        if (this.loadingDialog == null) {
            this.loadingDialog = new LoadingDialog(this);
            this.loadingDialog.setCancelable(false);
        }
        this.loadingDialog.show();
    }

    protected void closeHidingDialog() {
        if (this.loadingDialog != null && this.loadingDialog.isShowing()) {
            this.loadingDialog.dismiss();
        }
    }

}
