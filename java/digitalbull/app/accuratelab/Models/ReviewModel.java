package digitalbull.app.accuratelab.Models;

import java.io.Serializable;

public class ReviewModel implements Serializable {
    private float id;
    private String product_id;
    private String reviwer_id;
    private String seller_id = null;
    private String reviwer_name;
    private String reviewer_message;
    private float review_star;
    private String review_date;
    private String review_status;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getReviwer_id() {
        return reviwer_id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public String getReviwer_name() {
        return reviwer_name;
    }

    public String getReviewer_message() {
        return reviewer_message;
    }

    public float getReview_star() {
        return review_star;
    }

    public String getReview_date() {
        return review_date;
    }

    public String getReview_status() {
        return review_status;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setReviwer_id(String reviwer_id) {
        this.reviwer_id = reviwer_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public void setReviwer_name(String reviwer_name) {
        this.reviwer_name = reviwer_name;
    }

    public void setReviewer_message(String reviewer_message) {
        this.reviewer_message = reviewer_message;
    }

    public void setReview_star(float review_star) {
        this.review_star = review_star;
    }

    public void setReview_date(String review_date) {
        this.review_date = review_date;
    }

    public void setReview_status(String review_status) {
        this.review_status = review_status;
    }
}