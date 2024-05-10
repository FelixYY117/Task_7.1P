package com.example.lostandfind;



public class Item {

    private int id;
    private String itemName;
    private String description;
    private String date;
    private String location;

    // 构造函数
    public Item() {
    }

    // 构造函数
    public Item(String itemName, String description, String date, String location) {
        this.itemName = itemName;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    // 获取id
    public int getId() {
        return id;
    }

    // 设置id
    public void setId(int id) {
        this.id = id;
    }

    // 获取物品名称
    public String getItemName() {
        return itemName;
    }

    // 设置物品名称
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    // 获取物品描述
    public String getDescription() {
        return description;
    }

    // 设置物品描述
    public void setDescription(String description) {
        this.description = description;
    }

    // 获取日期
    public String getDate() {
        return date;
    }

    // 设置日期
    public void setDate(String date) {
        this.date = date;
    }

    // 获取位置
    public String getLocation() {
        return location;
    }

    // 设置位置
    public void setLocation(String location) {
        this.location = location;
    }
}