package com.epam.marathon.api;

import com.epam.marathon.auto.core.api.dto.SocialMediaDTO;

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

import com.epam.marathon.auto.core.api.rest.UsersRestService;

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

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@Test
public class UsersAPITest {

    private UsersRestService usersRestService;

    @BeforeTest
    public void serviceInit() {
        usersRestService = new UsersRestService();
    }

    @Test(description = "Get all users test")
    public void getAllUsersTest() {
        Response getResponse = usersRestService.getAllUsers();
        Assert.assertEquals(getResponse.getStatusCode(), HttpStatus.SC_OK, "Incorrect status code");

        List<UserDTO> userDTOList = getResponse.then().extract().body().jsonPath().getList(".", UserDTO.class);
        Assert.assertFalse(userDTOList.isEmpty(), "At least one contact is expected");
        userDTOList.forEach(el -> Assert.assertTrue(el.getEmail().contains("@"), "This account has an invalid e-mail: %s".formatted(el)));
    }

    @Test(description = "Verify Test Automation User data")
    public void verifyTAUserDataTest() {
        String username = "epamtestmail3@gmail.com";
        String country = "Ukraine";

        Response getResponse = usersRestService.getUser(username);
        Assert.assertEquals(getResponse.getStatusCode(), HttpStatus.SC_OK, "Incorrect status code");

        UserDTO userDTO = getResponse.then().extract().as(UserDTO.class);
        Assert.assertEquals(userDTO.getEmail(), username, "User %s email is incorrect".formatted(username));
        Assert.assertEquals(userDTO.getCountry(), country, "User %s country is incorrect".formatted(username));
    }

    @Test
    public void createUserTest() {
        Random random = new Random();
        String email = "auto3.0%s@mail.com".formatted(random.nextInt());
        String name = "Viktoriia";
        String linkedInId = "abc";

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setName(name);
        userDTO.setBirthday(LocalDate.of(2023, Month.APRIL, 1).toEpochDay());
        userDTO.setRegistration(new Date().getTime());

        SocialMediaDTO socialMediaDTO = new SocialMediaDTO();
        socialMediaDTO.setLinkedin(linkedInId);

        userDTO.setSocialMedia(socialMediaDTO);

        Response postResponse = usersRestService.postUser(userDTO);
        Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.SC_CREATED, "Incorrect status code");
    }

    @AfterTest
    public void cleanUpCreatedUsers() {
        List<UserDTO> allUsersList = usersRestService.getAllUsers().then().extract().body().jsonPath().getList(".", UserDTO.class);
        allUsersList.stream().filter(user -> user.getEmail().startsWith("auto3.0")).forEach(u -> usersRestService.deleteUser(u.getEmail()));
    }

}
