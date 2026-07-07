package digitalbull.app.accuratelab.Models;

import java.io.Serializable;

public class PolicyModel implements Serializable {
    public String terms_condition_information;
    public String return_information;
    public String privacy_information;

    public String getTerms_condition_information() {
        return terms_condition_information;
    }

    public void setTerms_condition_information(String terms_condition_information) {
        this.terms_condition_information = terms_condition_information;
    }

    public String getReturn_information() {
        return return_information;
    }

    public void setReturn_information(String return_information) {
        this.return_information = return_information;
    }

    public String getPrivacy_information() {
        return privacy_information;
    }

    public void setPrivacy_information(String privacy_information) {
        this.privacy_information = privacy_information;
    }

    public String getAbout_information() {
        return about_information;
    }

    public void setAbout_information(String about_information) {
        this.about_information = about_information;
    }

    public String getFaq_question() {
        return faq_question;
    }

    public void setFaq_question(String faq_question) {
        this.faq_question = faq_question;
    }

    public String getFaq_description() {
        return faq_description;
    }

    public void setFaq_description(String faq_description) {
        this.faq_description = faq_description;
    }

    public String about_information;
    public String faq_question;
    public String faq_description;


}
