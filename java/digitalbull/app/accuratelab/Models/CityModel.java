package digitalbull.app.accuratelab.Models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class CityModel implements Serializable {
    private String id;
    private String city_id;
    private String city_name;
    private String state_code;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getCity_id() {
        return city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getState_code() {
        return state_code;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    @NonNull
    @Override
    public String toString() {
        return city_name;
    }
}