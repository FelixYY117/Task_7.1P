package com.example.lostandfind;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button createButton, showButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化按钮
        createButton = findViewById(R.id.createbutton);
        showButton = findViewById(R.id.showbutton);

        // 设置按钮点击事件监听器
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 当点击创建按钮时，启动 AddItemActivity
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 当点击展示按钮时，启动 ShowItemActivity
                Intent intent = new Intent(MainActivity.this, ShowItemActivity.class);
                startActivity(intent);
            }
        });
    }
}