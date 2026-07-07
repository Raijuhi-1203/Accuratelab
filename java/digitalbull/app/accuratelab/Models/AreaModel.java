package digitalbull.app.accuratelab.Models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class AreaModel implements Serializable {
    private String id;
    private String state_name;
    private String city_name;
    private String pincode;
    private String area;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getState_name() {
        return state_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getPincode() {
        return pincode;
    }

    public String getArea() {
        return area;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @NonNull
    @Override
    public String toString() {
        return area;
    }
}
