package com.example.a14779.codeeditor.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;

import com.example.a14779.codeeditor.MainActivity;
import com.example.a14779.codeeditor.R;

/**
 * Created by liangtao on 18-2-6.
 */

public class HelpView extends View implements View.OnClickListener {
    private Context mContext;
    private Toolbar mToolbar;
    private WebView mWebView;

    public HelpView(Context context) {
        super(context);
        mContext = context;
    }

    public HelpView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @SuppressLint("InflateParams")
    public View initView(){
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.help_view_layout, null, false);
        initToolBar(view);
        initWebView(view);
        return view;
    }

    private void initToolBar(View view) {
        mToolbar = view.findViewById(R.id.help_view_tool_bar);
        mToolbar.setNavigationIcon(R.drawable.return_icon_32);
        mToolbar.setNavigationOnClickListener(this);
        mToolbar.setTitle("帮助");
    }

    private void initWebView(View view){
        mWebView = view.findViewById(R.id.help_content_text);
        mWebView.loadUrl("file:///android_asset/help.html");
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
