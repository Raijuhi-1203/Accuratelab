package digitalbull.app.accuratelab.Models;

import java.io.Serializable;

public class ProductImageeModel implements Serializable {
    private float id;
    private String product_id;
    private String photo_path;
    private String display_order = null;

    // Getter Methods

    public float getId() {
        return id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public String getDisplay_order() {
        return display_order;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    public void setDisplay_order(String display_order) {
        this.display_order = display_order;
    }
}