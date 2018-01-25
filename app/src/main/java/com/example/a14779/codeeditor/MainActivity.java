package com.example.a14779.codeeditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a14779.codeeditor.View.HomePageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomePageView homePageView = new HomePageView(MainActivity.this);
        setContentView(homePageView.initData());
    }
}