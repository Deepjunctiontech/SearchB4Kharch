package model;

import java.io.Serializable;

/**
 * Created by lenovo on 18-Apr-16.
 */
public class Productdata implements Serializable{

    private String description, price, product_thumbnailUrl;

    public Productdata(String description, String price, String product_thumbnailUrl) {
        this.description = description;
        this.price = price;
        this.product_thumbnailUrl = product_thumbnailUrl;
    }

    public Productdata() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProduct_thumbnailUrl() {
        return product_thumbnailUrl;
    }

    public void setProduct_thumbnailUrl(String product_thumbnailUrl) {
        this.product_thumbnailUrl = product_thumbnailUrl;
    }
}
