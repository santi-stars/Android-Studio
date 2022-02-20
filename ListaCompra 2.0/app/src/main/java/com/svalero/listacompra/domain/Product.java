package com.svalero.listacompra.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String category;
    @ColumnInfo
    private int quantity;
    @ColumnInfo
    private float price;
    @ColumnInfo
    private boolean important;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB) // Para guardar fotos en la base de datos
    private byte[] image;

    public Product(String name, String category, int quantity, float price, boolean important, byte[] image) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price * quantity;
        this.important = important;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name + "(" + quantity + ") = " + price + " €";
    }
}
