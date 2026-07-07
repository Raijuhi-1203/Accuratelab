package digitalbull.app.accuratelab.Models;

import java.io.Serializable;

public class ProductModel implements Serializable {
    private String product_id;

    public String getProduct_price_id() {
        return product_price_id;
    }

    public void setProduct_price_id(String product_price_id) {
        this.product_price_id = product_price_id;
    }

    private String product_price_id;
    private String product_full_name;
    private String product_description;
    private String product_parent_category_id;
    private String product_parent_category_name;
    private String product_sub_category_id;
    private String product_sub_category_name;
    private String product_full_description;
    private String publish_status;
    private String country_of_origin;
    private String verticle_id;
    private String verticle_name;
    private String product_unit;
    private String product_unit_value;
    private String product_GST_type;
    private String product_tax_type;
    private String product_GST_percentage;
    private String product_GST_rate;
    private String product_CGST_percentage;
    private String product_CGST_rate;
    private String product_SGST_percentage;
    private String product_SGST_rate;
    private String product_IGST_percentage = null;
    private String product_IGST_rate = null;
    private String product_market_price;
    private String product_sell_price;
    private String product_discount_percentage;
    private String product_discount_price;
    private String product_with_gst_Price;
    private String product_final_sell_price;
    private String product_shipping_charge;
    private String product_stock;
    private String photo_path;
    private int qty;
    private int cart_qty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public int getCart_qty() {
        return cart_qty;
    }

    public void setCart_qty(int cart_qty) {
        this.cart_qty = cart_qty;
    }

    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }

    // Getter Methods

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_full_name() {
        return product_full_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public String getProduct_parent_category_id() {
        return product_parent_category_id;
    }

    public String getProduct_parent_category_name() {
        return product_parent_category_name;
    }

    public String getProduct_sub_category_id() {
        return product_sub_category_id;
    }

    public String getProduct_sub_category_name() {
        return product_sub_category_name;
    }

    public String getProduct_full_description() {
        return product_full_description;
    }

    public String getPublish_status() {
        return publish_status;
    }

    public String getCountry_of_origin() {
        return country_of_origin;
    }

    public String getVerticle_id() {
        return verticle_id;
    }

    public String getVerticle_name() {
        return verticle_name;
    }

    public String getProduct_unit() {
        return product_unit;
    }

    public String getProduct_unit_value() {
        return product_unit_value;
    }

    public String getProduct_GST_type() {
        return product_GST_type;
    }

    public String getProduct_tax_type() {
        return product_tax_type;
    }

    public String getProduct_GST_percentage() {
        return product_GST_percentage;
    }

    public String getProduct_GST_rate() {
        return product_GST_rate;
    }

    public String getProduct_CGST_percentage() {
        return product_CGST_percentage;
    }

    public String getProduct_CGST_rate() {
        return product_CGST_rate;
    }

    public String getProduct_SGST_percentage() {
        return product_SGST_percentage;
    }

    public String getProduct_SGST_rate() {
        return product_SGST_rate;
    }

    public String getProduct_IGST_percentage() {
        return product_IGST_percentage;
    }

    public String getProduct_IGST_rate() {
        return product_IGST_rate;
    }

    public String getProduct_market_price() {
        return product_market_price;
    }

    public String getProduct_sell_price() {
        return product_sell_price;
    }

    public String getProduct_discount_percentage() {
        return product_discount_percentage;
    }

    public String getProduct_discount_price() {
        return product_discount_price;
    }

    public String getProduct_with_gst_Price() {
        return product_with_gst_Price;
    }

    public String getProduct_final_sell_price() {
        return product_final_sell_price;
    }

    public String getProduct_shipping_charge() {
        return product_shipping_charge;
    }

    public String getProduct_stock() {
        return product_stock;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    // Setter Methods

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_full_name(String product_full_name) {
        this.product_full_name = product_full_name;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public void setProduct_parent_category_id(String product_parent_category_id) {
        this.product_parent_category_id = product_parent_category_id;
    }

    public void setProduct_parent_category_name(String product_parent_category_name) {
        this.product_parent_category_name = product_parent_category_name;
    }

    public void setProduct_sub_category_id(String product_sub_category_id) {
        this.product_sub_category_id = product_sub_category_id;
    }

    public void setProduct_sub_category_name(String product_sub_category_name) {
        this.product_sub_category_name = product_sub_category_name;
    }

    public void setProduct_full_description(String product_full_description) {
        this.product_full_description = product_full_description;
    }

    public void setPublish_status(String publish_status) {
        this.publish_status = publish_status;
    }

    public void setCountry_of_origin(String country_of_origin) {
        this.country_of_origin = country_of_origin;
    }

    public void setVerticle_id(String verticle_id) {
        this.verticle_id = verticle_id;
    }

    public void setVerticle_name(String verticle_name) {
        this.verticle_name = verticle_name;
    }

    public void setProduct_unit(String product_unit) {
        this.product_unit = product_unit;
    }

    public void setProduct_unit_value(String product_unit_value) {
        this.product_unit_value = product_unit_value;
    }

    public void setProduct_GST_type(String product_GST_type) {
        this.product_GST_type = product_GST_type;
    }

    public void setProduct_tax_type(String product_tax_type) {
        this.product_tax_type = product_tax_type;
    }

    public void setProduct_GST_percentage(String product_GST_percentage) {
        this.product_GST_percentage = product_GST_percentage;
    }

    public void setProduct_GST_rate(String product_GST_rate) {
        this.product_GST_rate = product_GST_rate;
    }

    public void setProduct_CGST_percentage(String product_CGST_percentage) {
        this.product_CGST_percentage = product_CGST_percentage;
    }

    public void setProduct_CGST_rate(String product_CGST_rate) {
        this.product_CGST_rate = product_CGST_rate;
    }

    public void setProduct_SGST_percentage(String product_SGST_percentage) {
        this.product_SGST_percentage = product_SGST_percentage;
    }

    public void setProduct_SGST_rate(String product_SGST_rate) {
        this.product_SGST_rate = product_SGST_rate;
    }

    public void setProduct_IGST_percentage(String product_IGST_percentage) {
        this.product_IGST_percentage = product_IGST_percentage;
    }

    public void setProduct_IGST_rate(String product_IGST_rate) {
        this.product_IGST_rate = product_IGST_rate;
    }

    public void setProduct_market_price(String product_market_price) {
        this.product_market_price = product_market_price;
    }

    public void setProduct_sell_price(String product_sell_price) {
        this.product_sell_price = product_sell_price;
    }

    public void setProduct_discount_percentage(String product_discount_percentage) {
        this.product_discount_percentage = product_discount_percentage;
    }

    public void setProduct_discount_price(String product_discount_price) {
        this.product_discount_price = product_discount_price;
    }

    public void setProduct_with_gst_Price(String product_with_gst_Price) {
        this.product_with_gst_Price = product_with_gst_Price;
    }

    public void setProduct_final_sell_price(String product_final_sell_price) {
        this.product_final_sell_price = product_final_sell_price;
    }

    public void setProduct_shipping_charge(String product_shipping_charge) {
        this.product_shipping_charge = product_shipping_charge;
    }

    public void setProduct_stock(String product_stock) {
        this.product_stock = product_stock;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    @Override
    public String toString() {
        return product_full_name;
    }
}
