package model;

/**
 * Created by lenovo on 19-Apr-16.
 */
public class NavigationAllCategoryData {

    private String name, thumbnailUrl;

    public NavigationAllCategoryData(String name, String thumbnailUrl) {
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
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
