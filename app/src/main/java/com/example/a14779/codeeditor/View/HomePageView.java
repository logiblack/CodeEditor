package com.example.a14779.codeeditor.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a14779.codeeditor.Controller.FileHelper;
import com.example.a14779.codeeditor.Controller.HomeRecyclerAdapter;
import com.example.a14779.codeeditor.Controller.ReClickListener;
import com.example.a14779.codeeditor.MainActivity;
import com.example.a14779.codeeditor.R;
import com.github.clans.fab.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by liangtao on 18-1-24.
 */

public class HomePageView extends View implements View.OnClickListener, Toolbar.OnMenuItemClickListener{

    private static final String TAG = "HomePageView";
    private Context context;
    private List<String> nameList = new ArrayList<>();
    private String basePath;
    private Toolbar toolbar;
    private RecyclerView programList;
    private FloatingActionButton createProgram;
    private FloatingActionButton openLocalFile;
    private ViewGroup noProgramLayout;
    private HomeRecyclerAdapter mAdapter;

    public HomePageView(Context context) {
        super(context);
        this.context = context;
    }

    private void bindView(View view) {
        programList = view.findViewById(R.id.program_list);
        createProgram = view.findViewById(R.id.create_project_button);
        openLocalFile = view.findViewById(R.id.open_local_file_button);
        toolbar = view.findViewById(R.id.home_page_tool_bar);
        noProgramLayout = view.findViewById(R.id.no_program_layout);
    }

    public View initData(){
        View view = LayoutInflater.from(context).inflate(R.layout.home_page_layout, null, false);
        bindView(view);
        basePath = context.getExternalFilesDir(null).getPath();
        FileHelper.instance.saveFile("test.c", "#include<stdio.h>" +
                "\nint main(){printf(\"hello\\n \"); return 0;}", basePath);
        getProgramsList();
        //如果没有程序显示没有程序的布局
        if (nameList.size() == 0){
            noProgramLayout.setVisibility(View.VISIBLE);
        }
        initRecyclerView();
        initToolBar();
        initFloatingActionBt();
        return view;
    }

    private void initToolBar() {
        toolbar.setTitle("CodeEditor");
        toolbar.inflateMenu(R.menu.home_tool_bar_menu);
        toolbar.setOnMenuItemClickListener(this);
    }

    private void initRecyclerView() {
        mAdapter = new HomeRecyclerAdapter(context, nameList);
        mAdapter.setClickListener(new ReClickListener() {
            @Override
            public void onClick(View view, int position) {
                File file = new File(basePath+"/"+nameList.get(position));
                CodeView codeView = new CodeView(context, nameList.get(position));
                MainActivity.container.showView(codeView.initView());
            }

            @Override
            public void onLongClick(View view, int position) {
                File file = new File(basePath+"/"+nameList.get(position));
                List<String> editList = new ArrayList<>();
                editList.add("打开");
                editList.add("重命名");
                editList.add("删除");
                editList.add("属性");
            }
        });
        programList.setLayoutManager(new LinearLayoutManager(context));
        programList.setAdapter(mAdapter);
    }

    private void initFloatingActionBt() {
        createProgram.setOnClickListener(this);
        openLocalFile.setOnClickListener(this);
    }

    /***读取文件中的程序源代码文件名存储于 nameList 中*/
    private void getProgramsList() {
        File file = new File(basePath);
        String[] names = file.list();
        nameList.addAll(Arrays.asList(names));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_project_button:
                final ViewGroup createNewProgramView;
                createNewProgramView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.create_program_dialog_layout, null, false);
                new AlertDialog.Builder(context)
                        .setTitle("选择程序类型")
                        .setIcon(R.drawable.fab_add)
                        .setView(createNewProgramView)
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText name = (EditText) createNewProgramView.getChildAt(0);
                                Spinner type = (Spinner) createNewProgramView.getChildAt(1);
                                if (Objects.equals(name.getText().toString(), ""))
                                    Toast.makeText(context, "文件名不能为空", Toast.LENGTH_SHORT).show();
                                else {
                                    String suffix = "";
                                    switch (type.getSelectedItem().toString().trim()) {
                                        case "C语言":
                                            suffix = ".c";
                                            break;
                                        case "Java":
                                            suffix = ".java";
                                            break;
                                        case "C++":
                                            suffix = ".cpp";
                                            break;
                                    }
                                    MainActivity.container.showView(new CodeView(context, name.getText().toString().trim() + suffix).initView());
                                    mAdapter.addItem(name.getText().toString() + suffix);
                                }
                            }
                        })
                        .show();

                break;
            case R.id.open_local_file_button:
                MainActivity.container.showView(new LocalFileView(context).initView());
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_page_menu_setting:
                MainActivity.container.showView(new SettingView(context).initView());
                break;
            case R.id.home_page_menu_help:
                MainActivity.container.showView(new HelpView(context).initView());
                break;
        }
        return true;
    }
}
