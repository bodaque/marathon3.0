package com.epam.marathon.auto.core.api.dto;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement
public class UserDTO {

    @XmlAttribute(name = "email")
    private String email;

    @XmlAttribute(name = "country")
    private String country;

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "location")
    private String location;

    @XmlAttribute(name = "birthday")
    private long birthday;

    @XmlAttribute(name = "registration")
    private long registration;

    @XmlAttribute(name = "avatar")
    private String avatar;

    @XmlAttribute(name = "about")
    private String about;

    @XmlAttribute(name = "interests")
    private String[] interests;

    @XmlAttribute(name = "socialMedia")
    private SocialMediaDTO socialMedia;

    @XmlAttribute(name = "privacy")
    private PrivacyDTO privacy;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public long getRegistration() {
        return registration;
    }

    public void setRegistration(long registration) {
        this.registration = registration;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public SocialMediaDTO getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMediaDTO socialMedia) {
        this.socialMedia = socialMedia;
    }

    public PrivacyDTO getPrivacy() {
        return privacy;
    }

    public void setPrivacy(PrivacyDTO privacy) {
        this.privacy = privacy;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", birthday=" + birthday +
                ", registration=" + registration +
                ", avatar='" + avatar + '\'' +
                ", about='" + about + '\'' +
                ", interests=" + Arrays.toString(interests) +
                ", socialMedia=" + socialMedia +
                ", privacy=" + privacy +
                '}';
    }
}
