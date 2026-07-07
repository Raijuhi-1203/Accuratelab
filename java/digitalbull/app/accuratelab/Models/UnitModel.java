package digitalbull.app.accuratelab.Models;

import java.io.Serializable;

public class UnitModel implements Serializable {
    private String product_unit;
    private String product_unit_value;


    // Getter Methods

    public String getProduct_unit() {
        return product_unit;
    }

    public String getProduct_unit_value() {
        return product_unit_value;
    }

    // Setter Methods

    public void setProduct_unit(String product_unit) {
        this.product_unit = product_unit;
    }

    public void setProduct_unit_value(String product_unit_value) {
        this.product_unit_value = product_unit_value;
    }
}