package com.example.admin.lazada_app.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;

import com.example.admin.lazada_app.R;

public class MyDialog extends Dialog{
    public MyDialog(@NonNull Context context) {
        super(context);
        Init();
    }

    private void Init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_dialog);
    }
}
