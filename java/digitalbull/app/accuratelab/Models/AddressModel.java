package digitalbull.app.accuratelab.Models;

import java.io.Serializable;

public class AddressModel implements Serializable {
    private String id;
    private String customer_id;
    private String address_customer_name;
    private String address_customer_mobileno;
    private String address_customer_email;
    private String address_line_1;
    private String address_line_2;
    private String address_city_id;
    private String address_city_name;
    private String address_state_id;
    private String address_state_name;
    private String address_pincode;
    private String address_landmark;
    private String address_default;
    private String address_section = null;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getAddress_customer_name() {
        return address_customer_name;
    }

    public String getAddress_customer_mobileno() {
        return address_customer_mobileno;
    }

    public String getAddress_customer_email() {
        return address_customer_email;
    }

    public String getAddress_line_1() {
        return address_line_1;
    }

    public String getAddress_line_2() {
        return address_line_2;
    }

    public String getAddress_city_id() {
        return address_city_id;
    }

    public String getAddress_city_name() {
        return address_city_name;
    }

    public String getAddress_state_id() {
        return address_state_id;
    }

    public String getAddress_state_name() {
        return address_state_name;
    }

    public String getAddress_pincode() {
        return address_pincode;
    }

    public String getAddress_landmark() {
        return address_landmark;
    }

    public String getAddress_default() {
        return address_default;
    }

    public String getAddress_section() {
        return address_section;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public void setAddress_customer_name(String address_customer_name) {
        this.address_customer_name = address_customer_name;
    }

    public void setAddress_customer_mobileno(String address_customer_mobileno) {
        this.address_customer_mobileno = address_customer_mobileno;
    }

    public void setAddress_customer_email(String address_customer_email) {
        this.address_customer_email = address_customer_email;
    }

    public void setAddress_line_1(String address_line_1) {
        this.address_line_1 = address_line_1;
    }

    public void setAddress_line_2(String address_line_2) {
        this.address_line_2 = address_line_2;
    }

    public void setAddress_city_id(String address_city_id) {
        this.address_city_id = address_city_id;
    }

    public void setAddress_city_name(String address_city_name) {
        this.address_city_name = address_city_name;
    }

    public void setAddress_state_id(String address_state_id) {
        this.address_state_id = address_state_id;
    }

    public void setAddress_state_name(String address_state_name) {
        this.address_state_name = address_state_name;
    }

    public void setAddress_pincode(String address_pincode) {
        this.address_pincode = address_pincode;
    }

    public void setAddress_landmark(String address_landmark) {
        this.address_landmark = address_landmark;
    }

    public void setAddress_default(String address_default) {
        this.address_default = address_default;
    }

    public void setAddress_section(String address_section) {
        this.address_section = address_section;
    }
}