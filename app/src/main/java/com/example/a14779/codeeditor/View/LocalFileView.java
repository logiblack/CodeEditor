package com.example.a14779.codeeditor.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.example.a14779.codeeditor.MainActivity;
import com.example.a14779.codeeditor.R;

/**
 * Created by liangtao on 18-2-28.
 */

public class LocalFileView extends View {
    private Context mContext;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    public LocalFileView(Context context) {
        super(context);
        mContext = context;
    }

    public LocalFileView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public View initView(){
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.open_local_file_view_layout, null, false);
        initToolBar(view);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {

    }

    private void initToolBar(View view) {
        mToolbar = view.findViewById(R.id.open_local_file_view_tool_bar);
        mToolbar.setTitle("选择文件");
        mToolbar.setNavigationIcon(R.drawable.return_icon_32);
        mToolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.container.onBackPressed();
            }
        });
    }
}
