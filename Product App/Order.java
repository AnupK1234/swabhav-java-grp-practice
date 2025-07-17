package com.practice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private Date date;
    private List<LineItem> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public Order(int id, Date date) {
        this.id = id;
        this.date = date;
        this.items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public void setItems(List<LineItem> items) {
        this.items = items;
    }

    public void addLineItem(LineItem item) {
        if (this.items != null) {
            this.items.add(item);
        }
    }

    public double calculateOrderPrice() {
        double totalPrice = 0.0;
        if (items != null) {
            for (LineItem item : items) {
                totalPrice += item.calculateLineItemCost();
            }
        }
        return totalPrice;
    }
}