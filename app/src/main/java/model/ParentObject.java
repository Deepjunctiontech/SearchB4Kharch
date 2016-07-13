package model;

import java.util.List;

/**
 * Created by TrangHo on 10-05-2015.
 */
public class ParentObject {
    public String parentText;
    public List<ChildObject> childObjects;
    boolean plusMinus;

    public ParentObject(String parentText, List<ChildObject> childObjects, boolean plusMinus) {
        this.parentText = parentText;
        this.childObjects = childObjects;
        this.plusMinus = plusMinus;
    }

    public String getParentText() {
        return parentText;
    }

    public void setParentText(String parentText) {
        this.parentText = parentText;
    }

    public List<ChildObject> getChildObjects() {
        return childObjects;
    }

    public void setChildObjects(List<ChildObject> childObjects) {
        this.childObjects = childObjects;
    }

    public boolean isPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(boolean plusMinus) {
        this.plusMinus = plusMinus;
    }
}
