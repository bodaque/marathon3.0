package com.epam.marathon.auto.core.api.dto;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement
public class PrivacyDTO {

    @XmlAttribute(name = "description")
    private Boolean description;

    @XmlAttribute(name = "location")
    private Boolean location;

    @XmlAttribute(name = "account")
    private Boolean account;

    @XmlAttribute(name = "age")
    private Boolean age;

    public Boolean isDescription() {
        return description;
    }

    public void setDescription(Boolean description) {
        this.description = description;
    }

    public Boolean isLocation() {
        return location;
    }

    public void setLocation(Boolean location) {
        this.location = location;
    }

    public Boolean isAccount() {
        return account;
    }

    public void setAccount(Boolean account) {
        this.account = account;
    }

    public Boolean isAge() {
        return age;
    }

    public void setAge(Boolean age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PrivacyDTO{" +
                "description=" + description +
                ", location=" + location +
                ", account=" + account +
                ", age=" + age +
                '}';
    }
}