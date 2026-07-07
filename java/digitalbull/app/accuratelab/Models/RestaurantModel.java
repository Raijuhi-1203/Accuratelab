package digitalbull.app.accuratelab.Models;

import java.io.Serializable;

public class RestaurantModel implements Serializable {
    private String id;
    private String seller_id;
    private String seller_name;
    private String seller_firm_name;
    private String seller_email = null;
    private String seller_mobileno;
    private String seller_gender = null;
    private String seller_address_line_1;
    private String seller_address_line_2 = null;
    private String seller_state_id;
    private String seller_state_name;
    private String seller_city_id;
    private String seller_city_name;
    private String seller_gst;
    private String seller_gst_state_code;
    private String seller_gst_state;
    private String seller_pincode;
    private String seller_password;
    private String seller_photo;
    private String identity_option = null;
    private String mobileno_verified = null;
    private String otp = null;
    private String seller_date;
    private String seller_time;
    private String seller_paid_amount;
    private String seller_due_amount;
    private String seller_status;
    private String category_id;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    private String area;

    public String getOpening_time() {
        return opening_time;
    }

    public void setOpening_time(String opening_time) {
        this.opening_time = opening_time;
    }

    private String opening_time;

    public String getClosing_time() {
        return closing_time;
    }

    public void setClosing_time(String closing_time) {
        this.closing_time = closing_time;
    }

    private String closing_time;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public String getSeller_firm_name() {
        return seller_firm_name;
    }

    public String getSeller_email() {
        return seller_email;
    }

    public String getSeller_mobileno() {
        return seller_mobileno;
    }

    public String getSeller_gender() {
        return seller_gender;
    }

    public String getSeller_address_line_1() {
        return seller_address_line_1;
    }

    public String getSeller_address_line_2() {
        return seller_address_line_2;
    }

    public String getSeller_state_id() {
        return seller_state_id;
    }

    public String getSeller_state_name() {
        return seller_state_name;
    }

    public String getSeller_city_id() {
        return seller_city_id;
    }

    public String getSeller_city_name() {
        return seller_city_name;
    }

    public String getSeller_gst() {
        return seller_gst;
    }

    public String getSeller_gst_state_code() {
        return seller_gst_state_code;
    }

    public String getSeller_gst_state() {
        return seller_gst_state;
    }

    public String getSeller_pincode() {
        return seller_pincode;
    }

    public String getSeller_password() {
        return seller_password;
    }

    public String getSeller_photo() {
        return seller_photo;
    }

    public String getIdentity_option() {
        return identity_option;
    }

    public String getMobileno_verified() {
        return mobileno_verified;
    }

    public String getOtp() {
        return otp;
    }

    public String getSeller_date() {
        return seller_date;
    }

    public String getSeller_time() {
        return seller_time;
    }

    public String getSeller_paid_amount() {
        return seller_paid_amount;
    }

    public String getSeller_due_amount() {
        return seller_due_amount;
    }

    public String getSeller_status() {
        return seller_status;
    }

   public String getCategory_id() {
        return category_id;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public void setSeller_firm_name(String seller_firm_name) {
        this.seller_firm_name = seller_firm_name;
    }

    public void setSeller_email(String seller_email) {
        this.seller_email = seller_email;
    }

    public void setSeller_mobileno(String seller_mobileno) {
        this.seller_mobileno = seller_mobileno;
    }

    public void setSeller_gender(String seller_gender) {
        this.seller_gender = seller_gender;
    }

    public void setSeller_address_line_1(String seller_address_line_1) {
        this.seller_address_line_1 = seller_address_line_1;
    }

    public void setSeller_address_line_2(String seller_address_line_2) {
        this.seller_address_line_2 = seller_address_line_2;
    }

    public void setSeller_state_id(String seller_state_id) {
        this.seller_state_id = seller_state_id;
    }

    public void setSeller_state_name(String seller_state_name) {
        this.seller_state_name = seller_state_name;
    }

    public void setSeller_city_id(String seller_city_id) {
        this.seller_city_id = seller_city_id;
    }

    public void setSeller_city_name(String seller_city_name) {
        this.seller_city_name = seller_city_name;
    }

    public void setSeller_gst(String seller_gst) {
        this.seller_gst = seller_gst;
    }

    public void setSeller_gst_state_code(String seller_gst_state_code) {
        this.seller_gst_state_code = seller_gst_state_code;
    }

    public void setSeller_gst_state(String seller_gst_state) {
        this.seller_gst_state = seller_gst_state;
    }

    public void setSeller_pincode(String seller_pincode) {
        this.seller_pincode = seller_pincode;
    }

    public void setSeller_password(String seller_password) {
        this.seller_password = seller_password;
    }

    public void setSeller_photo(String seller_photo) {
        this.seller_photo = seller_photo;
    }

    public void setIdentity_option(String identity_option) {
        this.identity_option = identity_option;
    }

    public void setMobileno_verified(String mobileno_verified) {
        this.mobileno_verified = mobileno_verified;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setSeller_date(String seller_date) {
        this.seller_date = seller_date;
    }

    public void setSeller_time(String seller_time) {
        this.seller_time = seller_time;
    }

    public void setSeller_paid_amount(String seller_paid_amount) {
        this.seller_paid_amount = seller_paid_amount;
    }

    public void setSeller_due_amount(String seller_due_amount) {
        this.seller_due_amount = seller_due_amount;
    }

    public void setSeller_status(String seller_status) {
        this.seller_status = seller_status;
    }
    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
}
