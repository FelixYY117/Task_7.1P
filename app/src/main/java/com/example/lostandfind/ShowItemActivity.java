package com.example.lostandfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ShowItemActivity extends AppCompatActivity {

    private ListView itemListView;
    private ArrayAdapter<String> adapter;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_item_list);

        // 初始化 ListView
        itemListView = findViewById(R.id.itemListView);

        // 从数据库获取物品列表
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        itemList = databaseHelper.getAllItems();

        // 将物品名称添加到列表适配器
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (Item item : itemList) {
            adapter.add(item.getItemName());

        }

        // 设置适配器
        itemListView.setAdapter(adapter);

        // 设置 ListView 的点击事件监听器
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取点击的物品
                Item selectedItem = itemList.get(position);
                // 启动 RemoveItemActivity 并传递物品ID
                Intent intent = new Intent(ShowItemActivity.this, RemoveItemActivity.class);
                intent.putExtra("item_id", selectedItem.getId());
                startActivity(intent);
            }
        });
    }
}