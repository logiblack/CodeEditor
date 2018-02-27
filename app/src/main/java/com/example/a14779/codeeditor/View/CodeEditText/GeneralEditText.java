package com.example.a14779.codeeditor.View.CodeEditText;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by liangtao on 18-2-6.
 */

public class GeneralEditText extends AbsCodeEditText {

    public GeneralEditText(Context context) {
        super(context);
    }

    public GeneralEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Editable highLight(Editable editable) {
        Log.i(TAG, "highLight: ................");
        return editable;
    }

    @Override
    public void autoIndent(Editable editable, int type) {

    }

    @Override
    public void autoComplete(Editable e) {

    }

}
