package model;

/**
 * Created by lenovo on 09-Apr-16.
 */
public class BestDeals {

    private String detail, extra, merchantName,thumbnailUrl;

    public BestDeals(String detail, String extra, String merchantName) {
        this.detail = detail;
        this.extra = extra;
        this.merchantName = merchantName;
    }

    public BestDeals(String detail, String extra, String merchantName, String thumbnailUrl) {
        this.detail = detail;
        this.extra = extra;
        this.merchantName = merchantName;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public BestDeals() {

    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}
