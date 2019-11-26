package com.sg.template.components.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sg.template.AppMain;
import com.sg.template.R;
import com.sg.template.ui.base.BaseFragment;
import com.sg.template.utils.LogUtils;

import butterknife.BindView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SampleFragment extends BaseFragment {
    private static final String TAG = SampleFragment.class.getSimpleName();

    @BindView(R.id.textView)
    TextView textView;


    public static SampleFragment newInstance() {
        SampleFragment fragment = new SampleFragment();
        return fragment;
    }


    @Override
    public void onAttachFragment(Activity activity) {

    }

    @Override
    public void onCreateFragment(Bundle bundle) {

    }

    /**
     * Layout sent by your onInflateViewFragment() is converted into a View, enhanced and all the views in the component are binded.
     * and is  given to you as an argument,make all the modifications in this method and return the view.
     * View
     *
     * @param view
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateViewFragment(View view, Bundle savedInstanceState) {
        showLoadingDialog();
        AppMain.getNetworkService().getPost(1).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                closeLoadingDialog();
                textView.setText(response.body().toString());
                LogUtils.debug(TAG, response.body().toString());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                closeLoadingDialog();
                textView.setText(t.getMessage());
                LogUtils.debug(TAG, t.getMessage());
            }
        });
        return view;
    }

    @Override
    public int onInflateViewFragment() {
        return R.layout.fragment_sample;
    }

    @Override
    public String getFragmentTitle() {
        return "Sample Fragment";
    }


}
