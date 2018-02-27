package com.example.a14779.codeeditor.View.CodeEditText;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;

import java.util.logging.Handler;

import static android.content.ContentValues.TAG;

/**
 * Created by liangtao on 18-1-22.
 */

@SuppressLint("AppCompatCustomView")
public abstract class AbsCodeEditText extends EditText {

    protected Paint mNumPaint;
    protected Paint mNumBacPaint;
    protected Context mContext;
    protected Layout mLayout;

    protected android.os.Handler handler = new android.os.Handler();
    protected Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Editable e = getText();
            highLight(e);
        }
    };

    public AbsCodeEditText(Context mContext) {
        super(mContext);
        this.mContext = mContext;
        init();
    }

    public AbsCodeEditText(Context mContext, AttributeSet attrs) {
        super(mContext, attrs);
        this.mContext = mContext;
        init();
    }

    public AbsCodeEditText(Context mContext, AttributeSet attrs, int defStyleAttr) {
        super(mContext, attrs, defStyleAttr);
        this.mContext = mContext;
        init();
    }

    public AbsCodeEditText(Context mContext, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(mContext, attrs, defStyleAttr, defStyleRes);
        this.mContext = mContext;
        init();
    }

    public void init() {
        mNumPaint = new Paint();
        mNumPaint.setTextSize(getPixels(14));
        mNumPaint.setAntiAlias(false);
        mNumPaint.setStyle(Paint.Style.FILL);
        mNumPaint.setColor(Color.parseColor("#FFFFFF"));

        mNumBacPaint = new Paint();
        mNumBacPaint.setAntiAlias(false);
        mNumBacPaint.setStyle(Paint.Style.FILL);
        mNumBacPaint.setColor(Color.parseColor("#bbbbbb"));

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mLayout = getLayout();
            }
        });



        addTextChangedListener(new TextWatcher() {
            int start;
            int tabType;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0 && start+1< s.length()) {
                    if (s.charAt(start) == '\n'&&s.charAt(start+1) != '}')
                        tabType = 1;
                    if (s.charAt(start) == '\n'&&s.charAt(start+1) == '}')
                        tabType = 2;
                }
                this.start = start;
            }

            @Override
            public void afterTextChanged(Editable s) {
                handler.postDelayed(runnable, 500);
                if (tabType > 0) {
                    removeTextChangedListener(this);
                    switch (tabType) {
                        case 1:
                            addTab(s, start, getEffectiveCount(s, start));
                            break;
                        case 2:
                            addTab(s, start, getEffectiveCount(s, start));
                            s.insert(start + 2 * getEffectiveCount(s, start) + 1, "\n");
                            addTab(s, start + 2 * getEffectiveCount(s, start) + 1, getEffectiveCount(s, start) - 1);
                            setSelection(start + 2 * getEffectiveCount(s, start) + 1);
                            break;
                    }
                    tabType = 0;
                    addTextChangedListener(this);
                }

            }
        });

    }

    /**
     * 检查{@code editable}的{@code position}位置之前的有效的“{”的数量
     * @param editable 需要检查的字符串
     * @param position 所需检查的位置终点*/
    public int getEffectiveCount(Editable editable, int position){
        int count = 0;
        for (int i = 0; i<position; i++){
            if (editable.charAt(i) == '{')
                count++;
            if (editable.charAt(i) == '}')
                count--;
        }
        return count;
    }

    /**
     * 在{@code editable}的{@code position}位置之后添加{@code count}个制表符}*/
    private void addTab(Editable editable, int position, int count){
        for (int i=0; i<count; i++){
            editable.insert(position+1, "\u3000\u3000");
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLineNum(canvas);

    }

    /**
     * 高亮文本
     * @param editable 需要高亮的文本*/
    public abstract Editable highLight(Editable editable);

    /**
     * 自动缩进文本
     * @param editable 需要缩进的文本
     * @param type
     *          0:
     *          1:*/

    public abstract void autoIndent(Editable editable, int type);

    public abstract void autoComplete(Editable e);


    public void setHighLightText(CharSequence text) {
        setText(highLight(new SpannableStringBuilder(text)));
    }

    /**
     * 画出行数以及行号背景*/
    public  void drawLineNum(Canvas canvas){
        int digitCount = getDigitCount();
        int paddingLeft = (int) getPixels(digitCount*10+15);
        setPadding(paddingLeft,0,0,0);

        float numY = getBaseline();
        float numX = mLayout.getLineLeft(0) + getPixels(2);
        float recL = mLayout.getLineLeft(0);
        float recR = recL + paddingLeft - 5;
        float recT = mLayout.getLineTop(0);
        float recB = mLayout.getLineBottom(0);
        canvas.drawRect(recL, recT, recR, recB, mNumBacPaint);
        canvas.drawText(String.valueOf(1), numX, numY, mNumPaint);
        for (int i = 1; i<getLineCount(); i++){
            numY += mLayout.getLineBaseline(i) - mLayout.getLineBaseline(i-1);
            numX = mLayout.getLineLeft(i) + getPixels(2);
            recT = mLayout.getLineTop(i);
            recB = mLayout.getLineBottom(i);
            canvas.drawRect(recL, recT, recR, recB, mNumBacPaint);
            canvas.drawText(String.valueOf(i+1), numX, numY, mNumPaint);
        }

    }

    public void clearSpan(Editable e){
        // remove foreground color spans
        {
            ForegroundColorSpan spans[] = e.getSpans(0, e.length(), ForegroundColorSpan.class);

            for (int n = spans.length; n-- > 0; )
                e.removeSpan(spans[n]);
        }

        // remove background color spans
        {
            BackgroundColorSpan spans[] = e.getSpans(0, e.length(), BackgroundColorSpan.class);

            for (int n = spans.length; n-- > 0; )
                e.removeSpan(spans[n]);
        }
    }

    private int getDigitCount() {
        int len = getLineCount();
        int count=0;
        while (len/10>0){
            count++;
            len /= 10;
        }
        return count;
    }

    private float getPixels(int dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());
    }
}
