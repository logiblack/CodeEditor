package com.example.a14779.codeeditor.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by liangtao on 18-1-29.
 */

public class MyDialogView extends AlertDialog.Builder {
    private String mPostiveButtonText;
    private String mNegtiveButtonText;

    protected MyDialogView(Context context) {
        super(context);

    }

    @Override
    public AlertDialog.Builder setPositiveButton(int textId, DialogInterface.OnClickListener listener) {
        return super.setPositiveButton(textId, listener);
    }

    @Override
    public AlertDialog.Builder setPositiveButton(CharSequence text, DialogInterface.OnClickListener listener) {
        return super.setPositiveButton(text, listener);
    }
}
