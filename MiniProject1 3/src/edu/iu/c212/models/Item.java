package edu.iu.c212.models;

public class Item {



    private String name;


    private double price;
    private int quantity;


    private int aisleNum;


    /**
     * this defines the Item class.
     * @param name is the given name of the item
     * @param price is the given price of the item
     * @param quantity is the given item of the quantity
     * @param aisleNum is where the given aisle is located.
     *
     *
     */
    public Item(String name, double price, int quantity, int aisleNum){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.aisleNum = aisleNum;
    }

    public String getName() {
        return name;
    }









    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }


    public int getAisleNum() {
        return aisleNum;
    }



    public  void setQuantity(int newQuantity){


        this.quantity = newQuantity;

    }


}
