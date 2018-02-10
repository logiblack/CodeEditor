package com.example.a14779.codeeditor.View;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import com.example.a14779.codeeditor.Controller.CodeHelper.CodeCRHelper;
import com.example.a14779.codeeditor.Controller.CodeHelper.CompileCallback;
import com.example.a14779.codeeditor.Controller.CodeHelper.RunCallback;
import com.example.a14779.codeeditor.R;
import com.example.a14779.codeeditor.View.CodeEditText.GeneralEditText;

/**
 * Created by liangtao on 18-1-31.
 */

public class CodeView extends View implements Toolbar.OnMenuItemClickListener, RunCallback, CompileCallback {
    private Context context;
    private String title;
    private GeneralEditText editText;
    private android.support.v7.widget.Toolbar toolbar;

    public CodeView(Context context) {
        super(context);
        this.context = context;
    }

    public CodeView(Context context, String title){
        super(context);
        this.context = context;
        this.title = title;

    }

    public View initView(){
        View view = LayoutInflater.from(context).inflate(R.layout.code_view_layout, null, false);
        bindView(view);
        initToolBar();
        return view;
    }

    private void initToolBar() {
        toolbar.setTitle(title);
        toolbar.inflateMenu(R.menu.coding_tool_bar_menu);
    }

    private void bindView(View view) {
        editText = view.findViewById(R.id.code_edit_text_container);
        toolbar = view.findViewById(R.id.code_view_tool_bar);
        toolbar.setOnMenuItemClickListener(this);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.code_compile:
                CodeCRHelper.instance.Compile(editText.getText().toString(), this);
                break;
            case R.id.code_run:
                CodeCRHelper.instance.Run(editText.getText().toString(), this);
                break;
        }
        return true;
    }

    @Override
    public void onRunning(String result) {

    }

    @Override
    public void onCompiling(String result) {

    }
}