package com.quarantine.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Karl on 2/8/2017.
 */
public class ToDoListBean implements Serializable {

    private String email;
    private String owner;
    private String id;
    private ArrayList<ItemBean> items;
    private boolean isPrivate;


    public ToDoListBean() {
        this("default", "default", "default", false);
    }

    public ToDoListBean(String email, String id, String owner, boolean isPrivate) {
        this.email = email;
        this.id = id;
        this.isPrivate = isPrivate;
        this.owner = owner;
        this.items = new ArrayList<>();
    }

    public void setItems(ArrayList<ItemBean> items) {
        this.items = items;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


    public ArrayList<ItemBean> getItems() {
        return items;
    }

    public void addItem(ItemBean item) {
        this.items.add(item);
    }


    public String getId() {
        return id;
    }

    public String generateId(){
        Random rand = new Random();

        int  n = rand.nextInt(10000) + 1;

        return Integer.toString(n);
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ToDoListBean{" +
                "email='" + email + '\'' +
                ", owner='" + owner + '\'' +
                ", id='" + id + '\'' +
                ", items=" + items +
                ", isPrivate=" + isPrivate +
                '}';
    }
}
