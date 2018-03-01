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

public class JavaCodeEditText extends GeneralEditText{

    private static Pattern JAVA_KEY_WORD = Pattern.compile("\\b(" +
            "public|protected|private|class|extends|abstract|" +
            "interface|synchronized|final|default|break|" +
            "switch|case|continue|this|try|static|true|false|do|while|" +
            "instanceof|if|else|return|for|;|new|native|super" +
            ")\\b");

    private static Pattern JAVA_DATA_TYPE = Pattern.compile("\\b(" +
            "boolen|char|double|Editable|float|HashMap" +
            "|int|long|List|Map|String|null|void" +
            ")\\b");

    private static Pattern JAVA_HEAD_FILE = Pattern.compile("([^\\s\\S]*" +
            "package|import)|\\.");


    @SuppressLint("SetTextI18n")
    public JavaCodeEditText(Context context) {
        super(context);
    }

    @Override
    public Editable highLight(Editable editable) {
        if (editable.length() == 0){
            return editable;
        }
        clearSpan(editable);
        for (Matcher m = JAVA_HEAD_FILE.matcher(editable); m.find();){
            editable.setSpan(new ForegroundColorSpan(mContext.getColor(R.color.javaHeadFile)),
                    m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        for (Matcher m = JAVA_DATA_TYPE.matcher(editable); m.find();){
            editable.setSpan(new ForegroundColorSpan(mContext.getColor(R.color.javaDataType)),
                    m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        for (Matcher m = JAVA_KEY_WORD.matcher(editable); m.find();){
            editable.setSpan(new ForegroundColorSpan(mContext.getColor(R.color.javaKeyWord)),
                    m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        for (Matcher m = MATH_SIGN.matcher(editable); m.find();){
            editable.setSpan(new ForegroundColorSpan(mContext.getColor(R.color.mathSign)),
                    m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return editable;
    }

    @Override
    public void autoComplete(Editable e) {
        super.autoComplete(e);
    }


}
