package com.example.a14779.codeeditor.View;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.example.a14779.codeeditor.MainActivity;
import com.example.a14779.codeeditor.R;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by liangtao on 18-2-6.
 */

public class SettingView extends View implements View.OnClickListener {
    private Context mContext;
    private android.support.v7.widget.Toolbar toolbar;
    private List<ViewGroup> settingItems;

    public SettingView(Context context) {
        super(context);
        mContext = context;
    }

    public SettingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public View initView(){
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.setting_view_layout, null, false);
        initToolBar(view);
        return view;
    }

    private void initToolBar(View view) {
        toolbar = view.findViewById(R.id.setting_tool_bar);
        toolbar.setTitle("设置");
        toolbar.setNavigationIcon(R.drawable.return_icon_32);
        toolbar.setNavigationOnClickListener(this);
    }

    public void initSettingItem(View view){
        settingItems.get(0).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case -1:
                MainActivity.container.onBackPressed();
                break;
        }
    }
}
