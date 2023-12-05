package com.epam.marathon.auto.core.api.rest;

import com.epam.marathon.auto.core.BASE_URI;

public enum BASE_URI {
    UI(""),
    API("");

    private String uri;
    BASE_URI(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

import com.epam.marathon.auto.core.api.dto.UserDTO;

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

import com.epam.marathon.auto.exception.UpamersTAException;


@Slf4j
public class UpamersTAException extends RuntimeException {

    public UpamersTAException() {
    }

    public UpamersTAException(String message) {
        super(message);
        log.error(message);
    }

    public UpamersTAException(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }
}

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import static io.restassured.RestAssured.given;

@Slf4j
public class UsersRestService {

    private static final String BASE_URI;

    private static final String BASE_PATH = "/users";

    static {
        BASE_URI = com.epam.marathon.auto.core.BASE_URI.API.getUri();
    }

    public Response getAllUsers() {
        log.info("get all users");
        return given().baseUri(BASE_URI).basePath(BASE_PATH)
                .header("Content-Type", "application/json")
                .then().log().ifError()
                .then().request().get()
                .then().extract().response();
    }

    public Response getUser(String username) {
        log.info("get user: {}", username);
        return given().baseUri(BASE_URI).basePath(BASE_PATH)
                .header("Content-Type", "application/json")
                .urlEncodingEnabled(false)
                .then().log().ifError()
                .then().request().get("/" + username)
                .then().extract().response();
    }

    public Response postUser(UserDTO userDTO) {
        log.info("create user: {}", userDTO);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String body = objectMapper.writeValueAsString(userDTO);
            return given().baseUri(BASE_URI).basePath(BASE_PATH)
                    .header("Content-Type", "application/json")
                    .body(body)
                    .log().body()
                    .then().request().post()
                    .then().extract().response();
        } catch (JsonProcessingException e) {
            throw new UpamersTAException("Failed to map " + userDTO + " to JSON String");
        }
    }

    public Response deleteUser(String username) {
        log.info("delete user: {}", username);
        return given().baseUri(BASE_URI).basePath(BASE_PATH)
                .urlEncodingEnabled(false)
                .then().log().everything()
                .then().request().delete("/" + username)
                .then().extract().response();
    }
}
