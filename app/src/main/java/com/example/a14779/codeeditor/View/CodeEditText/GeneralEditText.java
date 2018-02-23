package com.example.a14779.codeeditor.View.CodeEditText;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Editable;
import android.util.AttributeSet;

/**
 * Created by liangtao on 18-2-6.
 */

public class GeneralEditText extends AbsCodeEditText {
    private Canvas canvas;

    public GeneralEditText(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public GeneralEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void highLight(Editable editable) {

    }

    @Override
    public void autoIndent(Editable editable, int type) {

    }

    @Override
    public void autoComplete(Editable e) {

    }
}
