package model;

/**
 * Created by deep on 01/06/16.
 */
public class DealsData {

   private String offer_name,coupon_title,coupon_description,link,url,coupon_expiry,added, coupon_code;

    public DealsData(String offer_name, String coupon_title, String coupon_description, String link, String url, String coupon_expiry, String added, String coupon_code) {
        this.offer_name = offer_name;
        this.coupon_title = coupon_title;
        this.coupon_description = coupon_description;
        this.link = link;
        this.url = url;
        this.coupon_expiry = coupon_expiry;
        this.added = added;
        this.coupon_code = coupon_code;
    }

    public DealsData() {

    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public DealsData(String offer_name, String coupon_title, String coupon_description, String link, String url, String coupon_expiry, String added) {
        this.offer_name = offer_name;
        this.coupon_title = coupon_title;
        this.coupon_description = coupon_description;
        this.link = link;
        this.url = url;
        this.coupon_expiry = coupon_expiry;
        this.added = added;
    }

    public String getOffer_name() {
        return offer_name;
    }

    public void setOffer_name(String offer_name) {
        this.offer_name = offer_name;
    }

    public String getCoupon_title() {
        return coupon_title;
    }

    public void setCoupon_title(String coupon_title) {
        this.coupon_title = coupon_title;
    }

    public String getCoupon_description() {
        return coupon_description;
    }

    public void setCoupon_description(String coupon_description) {
        this.coupon_description = coupon_description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCoupon_expiry() {
        return coupon_expiry;
    }

    public void setCoupon_expiry(String coupon_expiry) {
        this.coupon_expiry = coupon_expiry;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }
}
