package com.example.a14779.codeeditor.Controller.Container;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Stack;

/**
 * Created by liangtao on 18-2-6.
 */

public class LinearContainer extends LinearLayout implements container {
    private Stack<View> stack = new Stack<>();
    private Context context;

    public LinearContainer(Context context) {
        super(context);
        this.context = context;
    }

    public LinearContainer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void showView(View view) {
        this.addView(view);
        if (view.getVisibility() == View.INVISIBLE){
            view.setVisibility(View.VISIBLE);
        }

        stack.push(view);
        stack.get(stack.size()-1).setClickable(true);

    }

    @Override
    public void onBackPressed() {
        View topView = stack.get(stack.size()-1);
        topView.setVisibility(View.INVISIBLE);
        this.removeView(topView);
        stack.pop();
    }

    public int getStackSize(){
        return stack.size();
    }

}
