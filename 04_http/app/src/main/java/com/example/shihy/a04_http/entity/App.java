package com.example.shihy.a04_http.entity;

/**
 * Created by shihy on 16/8/18.
 */
public class App {
    private String name;
    private String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public App() {
    }

    public App(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "name:"+name+" ,icon:"+icon;
    }
}
