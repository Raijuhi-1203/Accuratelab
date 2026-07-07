package digitalbull.app.accuratelab.Models;

import java.io.Serializable;

public class StateModel implements Serializable {
    private float id;
    private String state_id;
    private String state_name;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getState_id() {
        return state_id;
    }

    public String getState_name() {
        return state_name;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    @Override
    public String toString() {
        return state_name;
    }
}