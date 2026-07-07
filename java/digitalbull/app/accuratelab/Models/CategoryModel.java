package digitalbull.app.accuratelab.Models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    private float id;
    private float category_temp_id;
    private String category_id;
    private String main_category_id;
    private String main_sub_category_id = null;
    private String category_title;
    private String category_name;
    private String category_photo;
    private float category_orderno;
    private String category_status;
    private String category_type;
    private String category_display;
    private String category_banner = null;

    public String getSeller_firm_name() {
        return seller_firm_name;
    }

    public void setSeller_firm_name(String seller_firm_name) {
        this.seller_firm_name = seller_firm_name;
    }

    private String seller_firm_name = null;


    // Getter Methods

    public float getId() {
        return id;
    }

    public float getCategory_temp_id() {
        return category_temp_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getMain_category_id() {
        return main_category_id;
    }

    public String getMain_sub_category_id() {
        return main_sub_category_id;
    }

    public String getCategory_title() {
        return category_title;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getCategory_photo() {
        return category_photo;
    }

    public float getCategory_orderno() {
        return category_orderno;
    }

    public String getCategory_status() {
        return category_status;
    }

    public String getCategory_type() {
        return category_type;
    }

    public String getCategory_display() {
        return category_display;
    }

    public String getCategory_banner() {
        return category_banner;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setCategory_temp_id(float category_temp_id) {
        this.category_temp_id = category_temp_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public void setMain_category_id(String main_category_id) {
        this.main_category_id = main_category_id;
    }

    public void setMain_sub_category_id(String main_sub_category_id) {
        this.main_sub_category_id = main_sub_category_id;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setCategory_photo(String category_photo) {
        this.category_photo = category_photo;
    }

    public void setCategory_orderno(float category_orderno) {
        this.category_orderno = category_orderno;
    }

    public void setCategory_status(String category_status) {
        this.category_status = category_status;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }

    public void setCategory_display(String category_display) {
        this.category_display = category_display;
    }

    public void setCategory_banner(String category_banner) {
        this.category_banner = category_banner;
    }

    @NonNull
    @Override
    public String toString() {
        return seller_firm_name + " (" + category_name+")";
    }

//    @Override
//    public String toString() {
//        return "CategoryModel{" +
//                "category_name='" + category_name + '\'' +
//                ", seller_firm_name='" + seller_firm_name + '\'' +
//                '}';
//    }

}

