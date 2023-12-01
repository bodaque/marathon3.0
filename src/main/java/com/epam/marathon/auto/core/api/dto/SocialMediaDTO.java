package com.epam.marathon.auto.core.api.dto;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SocialMediaDTO {

    @XmlAttribute(name = "linkedin")
    private String linkedin;

    @XmlAttribute(name = "instagram")
    private String instagram;

    @XmlAttribute(name = "telegram")
    private String telegram;

    @XmlAttribute(name = "facebook")
    private String facebook;

    @XmlAttribute(name = "skype")
    private String skype;

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Override
    public String toString() {
        return "SocialMediaDTO{" +
                "linkedin='" + linkedin + '\'' +
                ", instagram='" + instagram + '\'' +
                ", telegram='" + telegram + '\'' +
                ", facebook='" + facebook + '\'' +
                ", skype='" + skype + '\'' +
                '}';
    }
}
