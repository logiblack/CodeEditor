package com.example.a14779.codeeditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a14779.codeeditor.Controller.Container.FrameContainer;
import com.example.a14779.codeeditor.View.HomePageView;

public class MainActivity extends AppCompatActivity {
    public static FrameContainer container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomePageView homePageView = new HomePageView(MainActivity.this);
        //setContentView(homePageView.initData());
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
