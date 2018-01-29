package com.example.a14779.codeeditor.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.a14779.codeeditor.Controller.FileHelper;
import com.example.a14779.codeeditor.Controller.HomeRecyclerAdapter;
import com.example.a14779.codeeditor.R;
import com.github.clans.fab.FloatingActionButton;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangtao on 18-1-24.
 */

public class HomePageView extends View implements View.OnClickListener, Toolbar.OnMenuItemClickListener{

    private static final String TAG = "HomePageView";
    private Context context;
    private List<String> nameList = new ArrayList<>();
    private String basePath;
    private View createNewProgramView;
    private Toolbar toolbar;
    private RecyclerView programList;
    private FloatingActionButton createProgram;
    private FloatingActionButton createPackage;
    private ViewGroup noProgramLayout;
    private HomeRecyclerAdapter mAdapter;

    public HomePageView(Context context) {
        super(context);
        this.context = context;
    }

    private void bindView(View view) {
        programList = view.findViewById(R.id.program_list);
        createProgram = view.findViewById(R.id.create_project_button);
        createPackage = view.findViewById(R.id.create_package_button);
        toolbar = view.findViewById(R.id.home_page_tool_bar);
        noProgramLayout = view.findViewById(R.id.no_program_layout);
        createNewProgramView = LayoutInflater.from(context).inflate(R.layout.program_type_list_layout, null, false);
    }

    public View initData(){
        View view = LayoutInflater.from(context).inflate(R.layout.home_page_layout, null, false);
        bindView(view);
        basePath = context.getExternalFilesDir(null).getPath();
        try {
            FileHelper.saveFile("test.c", "liangtao", basePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getProgramsList();
        //如果没有程序显示没有程序的布局
        if (nameList.size() == 0){
            noProgramLayout.setVisibility(View.VISIBLE);
        }
        initRecyclerView();

        initToolBar();
        createProgram.setOnClickListener(this);
        createPackage.setOnClickListener(this);
        return view;
    }

    private void initRecyclerView() {
        mAdapter = new HomeRecyclerAdapter(context, nameList);
        mAdapter.setOnItemClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mAdapter.setOnItemLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        programList.setLayoutManager(new LinearLayoutManager(context));
        programList.setAdapter(mAdapter);
    }

    private void initToolBar() {
        toolbar.setTitle("CodeEditor");
        toolbar.inflateMenu(R.menu.tool_bar_menu);
        toolbar.setOnMenuItemClickListener(this);
    }

    /***读取文件中的程序源代码文件名存储于 nameList 中*/
    private void getProgramsList() {
        File file = new File(basePath);
        String[] names = file.list();
        for (int i=0; i<names.length; i++){
            nameList.add(names[i]);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_project_button:
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("选择程序类型")
                        .setIcon(R.drawable.fab_add)
                        .setView(createNewProgramView)
                        .show();
                break;
            case R.id.create_package_button:
                new AlertDialog.Builder(context)
                        .setTitle("新建包")
                        .setView(R.layout.create_package_dialog_layput)
                        .setIcon(R.drawable.fab_add)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_page_menu_setting:
                break;
            case R.id.home_page_menu_help:
                break;
        }
        return true;
    }
}
