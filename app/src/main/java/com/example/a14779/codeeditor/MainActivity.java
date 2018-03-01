package com.example.a14779.codeeditor;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a14779.codeeditor.Controller.Container.FrameContainer;
import com.example.a14779.codeeditor.Controller.PluginInit;
import com.example.a14779.codeeditor.View.HomePageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static FrameContainer container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!new File(this.getFilesDir()+File.separator+"gcc").exists()) {
            PluginInit.instance.addGCC(this);
        }
        HomePageView homePageView = new HomePageView(MainActivity.this);
        setContentView(R.layout.main_activity_layout);
        container = findViewById(R.id.container);
        container.showView(homePageView.initData());
    }

    @Override
    public void onBackPressed() {
        if (container.getStackSize() == 1) {
            super.onBackPressed();
        } else {
            container.onBackPressed();
        }
    }
}
