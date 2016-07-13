package model;

/**
 * Created by lenovo on 20-Apr-16.
 */
public class ProductDataList {

    private String name, description, price, imageUrl,productUrlKey,categoriesUrlKey,sb4kProductID;

    public ProductDataList(String description, String imageUrl, String name, String price) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
    }

    public String getSb4kProductID() {
        return sb4kProductID;
    }

    public void setSb4kProductID(String sb4kProductID) {
        this.sb4kProductID = sb4kProductID;
    }

    public ProductDataList(String name, String description, String price, String imageUrl, String productUrlKey, String categoriesUrlKey, String sb4kProductID) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.productUrlKey = productUrlKey;
        this.categoriesUrlKey = categoriesUrlKey;
        this.sb4kProductID = sb4kProductID;
    }

    public ProductDataList(String name, String description, String price, String imageUrl, String productUrlKey, String categoriesUrlKey) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.productUrlKey = productUrlKey;
        this.categoriesUrlKey = categoriesUrlKey;
    }

    public String getCategoriesUrlKey() {
        return categoriesUrlKey;
    }

    public void setCategoriesUrlKey(String categoriesUrlKey) {
        this.categoriesUrlKey = categoriesUrlKey;
    }

    public String getProductUrlKey() {
        return productUrlKey;
    }

    public void setProductUrlKey(String productUrlKey) {
        this.productUrlKey = productUrlKey;
    }

    public ProductDataList(String description, String imageUrl, String name, String price, String productUrlKey) {

        this.description = description;
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
        this.productUrlKey = productUrlKey;
    }

    public ProductDataList() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
