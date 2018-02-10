package com.example.a14779.codeeditor.View.CodeEditText;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;

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
    public void highLight(Editable editable) {

    }

    @Override
    public void autoIndent(Editable editable, int type) {

    }
}
