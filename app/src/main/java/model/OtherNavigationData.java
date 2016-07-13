package model;

/**
 * Created by lenovo on 17-Apr-16.
 */
public class OtherNavigationData {

    private String name;
    private int image_resource_id;

    public int getImage_resource_id() {
        return image_resource_id;
    }

    public void setImage_resource_id(int image_resource_id) {
        this.image_resource_id = image_resource_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OtherNavigationData(int image_resource_id, String name) {

        this.image_resource_id = image_resource_id;
        this.name = name;
    }
}


