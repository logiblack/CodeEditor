package com.example.a14779.codeeditor.View.CodeEditText;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.example.a14779.codeeditor.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liangtao on 18-1-22.
 */

public class CCodeEditText extends GeneralEditText {
    private static Pattern C_KEY_WORD = Pattern.compile("\\b("
            +"if|else|switch|case" +
            "|break|while|do|continue|goto|return|this|default|auto|extern|typedef" +
            "|sizeof|for|static|volatile|inline|restrict|_Bool|_Complex|_Imaginary" +
            "|register" +
            ")\\b");
    private static Pattern C_DATA_TYPE = Pattern.compile("\\b(" +
            "short|int|long|double|unsigned|char|struct|const|enum|float|signed|void" +
            "|union" +
            ")\\b");
    private static Pattern C_HEAD_FILE = Pattern.compile("\\b(" +
            "#include<.*>|#define" +
            ")\\b");

    @SuppressLint("SetTextI18n")
    public CCodeEditText(Context context) {
        super(context);
        setText("CCodeEditText");
    }

    @Override
    public Editable highLight(Editable editable) {
        if (editable.length() == 0){
            return editable;
        }
        clearSpan(editable);
        for (Matcher m = C_HEAD_FILE.matcher(editable); m.find();){
            editable.setSpan(new ForegroundColorSpan(mContext.getColor(R.color.javaHeadFile)),
                    m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        for (Matcher m = C_DATA_TYPE.matcher(editable); m.find();){
            editable.setSpan(new ForegroundColorSpan(mContext.getColor(R.color.javaDataType)),
                    m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        for (Matcher m = C_KEY_WORD.matcher(editable); m.find();){
            editable.setSpan(new ForegroundColorSpan(mContext.getColor(R.color.javaKeyWord)),
                    m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        for (Matcher m = MATH_SIGN.matcher(editable); m.find();){
            editable.setSpan(new ForegroundColorSpan(mContext.getColor(R.color.mathSign)),
                    m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return editable;
    }
}
