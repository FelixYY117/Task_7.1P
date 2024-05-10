package com.example.lostandfind;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    private EditText itemNameEditText, descriptionEditText, dateEditText, locationEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_advert);

        // 初始化视图
        itemNameEditText = findViewById(R.id.nameEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        dateEditText = findViewById(R.id.dateEditText);
        locationEditText = findViewById(R.id.locationEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取输入值
                String itemName = itemNameEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String date = dateEditText.getText().toString();
                String location = locationEditText.getText().toString();

                // 将物品保存到数据库
                DatabaseHelper databaseHelper = new DatabaseHelper(AddItemActivity.this);
                databaseHelper.insertItem(itemName, description, date, location);

                // 结束当前页面
                finish();
            }
        });
    }
}
