package com.mm.shoppersstore;

public class Product
{

    String name;
    String bran;
    String description;
    String imageAddress;

    Product(String name, String bran, String description)
    {
        this.name = name;
        this.bran = bran;
        this.description = description;
    }
    Product(String name, String bran, String description, String imageAddress)
    {
        this.name = name;
        this.bran = bran;
        this.description = description;
        this.imageAddress = imageAddress;
    }

}
