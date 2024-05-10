package com.example.lostandfind;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RemoveItemActivity extends AppCompatActivity {

    private TextView itemNameTextView;
    private TextView  itemDateTextView;
    private TextView itemLocationTextView;
    private Button removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_view);

        // 初始化视图
        itemNameTextView = findViewById(R.id.itemNameText);
        itemDateTextView =findViewById(R.id.timeDateText);
        itemLocationTextView=findViewById(R.id.locationText);
        removeButton = findViewById(R.id.removebutton);

        // 获取传递的物品ID
        int itemId = getIntent().getIntExtra("item_id", -1);

        // 从数据库获取对应的物品
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Item item = databaseHelper.getItem(itemId);

        // 显示物品名称
        itemNameTextView.setText(item.getItemName());
        itemDateTextView.setText(item.getDate());
        itemLocationTextView.setText(item.getLocation());

        // 设置按钮点击事件监听器
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 从数据库中删除物品
                databaseHelper.deleteItem(item.getId());
                Intent intent = new Intent(RemoveItemActivity.this, MainActivity.class);
                startActivity(intent);
                // 结束当前页面
                finish();
            }
        });
    }
}
