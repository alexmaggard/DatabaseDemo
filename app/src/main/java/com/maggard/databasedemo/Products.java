package com.maggard.databasedemo;

/**
 * Created by 660252397 on 9/21/2016.
 *
 *
 * will contain the constructors and setters and getters
 */


public class Products {
    private int _id;
    private String productName;

    //**********************default constructor*******************************************
    public Products(){

    }
    //***************************product name constructor*********************************
    public Products(String productName) {
        this.productName = productName;
    }

    //*****************************setters ***********************************************
    public void set_id(int _id) {
        this._id = _id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    //*****************************getters ***********************************************
    public int get_id() {
        return _id;
    }

    public String getProductName() {
        return productName;
    }

}
