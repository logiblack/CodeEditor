package com.example.a14779.codeeditor.View;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.a14779.codeeditor.Controller.HomeRecyclerAdapter;
import com.example.a14779.codeeditor.R;
import com.example.a14779.codeeditor.View.CodeView.AbsCodeEditText;
import com.github.clans.fab.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by liangtao on 18-1-24.
 */

public class HomePageView extends View implements View.OnClickListener, AdapterView.OnItemClickListener{
    private Context context;
    private List<String> nameList = new ArrayList<>();
    private String basePath;
    private View programTypeView;
    private ListView programTypeList;
    private Toolbar toolbar;
    private RecyclerView programList;
    private FloatingActionButton createProgram;
    private FloatingActionButton createPackage;
    private CircleImageView programTypeImage;

    public HomePageView(Context context) {
        super(context);
        this.context = context;
    }


    public View initData(){
        View view = LayoutInflater.from(context).inflate(R.layout.home_page_layout, null, false);
        bindView(view);
        basePath = context.getExternalFilesDir(null).getPath();
        getProgramsList();
        programList.setLayoutManager(new LinearLayoutManager(context));
        programList.setAdapter(new HomeRecyclerAdapter(context, nameList));
        initToolBar();
        createProgram.setOnClickListener(this);
        createPackage.setOnClickListener(this);
        programTypeList.setOnItemClickListener(this);
        return view;
    }

    private void bindView(View view) {
        programList = view.findViewById(R.id.program_list);
        createProgram = view.findViewById(R.id.create_project_button);
        createPackage = view.findViewById(R.id.create_package_button);
        programTypeImage = view.findViewById(R.id.program_type_image);
        toolbar = view.findViewById(R.id.home_page_tool_bar);
        programTypeView = LayoutInflater.from(context).inflate(R.layout.program_type_list_layout, null, false);
        programTypeList = programTypeView.findViewById(R.id.program_type);
    }

    private void initToolBar() {
        toolbar.setTitle("CodeEditor");
        toolbar.inflateMenu(R.menu.tool_bar_menu);
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
                        .setView(programTypeView)
                        .show();
                break;
            case R.id.create_package_button:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
