package com.example.a14779.codeeditor.View.CodeView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Editable;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by liangtao on 18-1-22.
 */

@SuppressLint("AppCompatCustomView")
public abstract class AbsCodeEditText extends EditText {

    Paint paint;
    Canvas canvas;
    Context context;


    public AbsCodeEditText(Context context) {
        super(context);
    }

    public AbsCodeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsCodeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AbsCodeEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init();
    }

    private void init() {

    }

    /**
     * 高亮文本
     * @param editable 需要高亮的文本*/
    public abstract void highLight(Editable editable);

    /**
     * 自动缩进文本
     * @param editable 需要缩进的文本
     * @param type
     *          0:
     *          1:*/

    public abstract void autoIndent(Editable editable, int type);

    /**
     * 画出行数*/
    public  void drawLineNum(Editable e){
        int line = getLineCount();
        int spaceCount=0,k=line;
        int left = getLeft();
        int top = getTop();
        for (;k>0;){
            spaceCount++;
            k = k/10;
        }
        for (int i = 0; i<line; i++){
        }
    }

    public void clearSpan(Editable e){
        ForegroundColorSpan spans[] = e.getSpans(0, e.length(), ForegroundColorSpan.class);

    }
}
