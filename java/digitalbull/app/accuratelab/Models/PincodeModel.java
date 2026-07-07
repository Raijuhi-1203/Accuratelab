package digitalbull.app.accuratelab.Models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class PincodeModel implements Serializable {
    private String id;
    private String state_name;
    private String city_name;
    private String pincode;
    private String area;
    private String state_id;
    private String district_id;
    private String district_name;


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

    public String getState_id() {
        return state_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public String getDistrict_name() {
        return district_name;
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

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    @NonNull
    @Override
    public String toString() {
        return pincode;
    }
}
