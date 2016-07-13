package model;

/**
 * Created by lenovo on 08-Apr-16.
 */
public class TopDealsData {

    private String description, price, price_caption, product_thumbnailUrl, merchant_thumbnailUrl;

    public TopDealsData(String description, String merchant_thumbnailUrl, String price, String product_thumbnailUrl) {
        this.description = description;
        this.merchant_thumbnailUrl = merchant_thumbnailUrl;
        this.price = price;
        this.product_thumbnailUrl = product_thumbnailUrl;
    }

    public TopDealsData(String description, String merchant_thumbnailUrl, String price, String price_caption, String product_thumbnailUrl) {
        this.description = description;
        this.merchant_thumbnailUrl = merchant_thumbnailUrl;
        this.price = price;
        this.price_caption = price_caption;
        this.product_thumbnailUrl = product_thumbnailUrl;
    }

    public TopDealsData() {

    }

    public TopDealsData(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMerchant_thumbnailUrl() {
        return merchant_thumbnailUrl;
    }

    public void setMerchant_thumbnailUrl(String merchant_thumbnailUrl) {
        this.merchant_thumbnailUrl = merchant_thumbnailUrl;
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

    public String getPrice_caption() {
        return price_caption;
    }

    public void setPrice_caption(String price_caption) {
        this.price_caption = price_caption;
    }
}


