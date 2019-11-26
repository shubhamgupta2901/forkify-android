package com.sg.template.ui.dialogs;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.sg.template.R;


public class LoadingDialog extends ProgressDialog {

    public LoadingDialog(Context context) {
        super(context);

    }

    @Override
    public void show() {
        super.show();
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(R.layout.view_loading_dialog);
    }

}
