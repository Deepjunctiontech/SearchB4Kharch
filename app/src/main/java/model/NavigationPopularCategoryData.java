package model;

/**
 * Created by lenovo on 18-Apr-16.
 */
public class NavigationPopularCategoryData {

    private String name, thumbnailUrl,categoriesUrlKey;


    public NavigationPopularCategoryData(String name, String thumbnailUrl) {
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
    }

    public NavigationPopularCategoryData(String categoriesUrlKey, String name, String thumbnailUrl) {
        this.categoriesUrlKey = categoriesUrlKey;
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getCategoriesUrlKey() {
        return categoriesUrlKey;
    }

    public void setCategoriesUrlKey(String categoriesUrlKey) {
        this.categoriesUrlKey = categoriesUrlKey;
    }

    public NavigationPopularCategoryData() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
