package com.epam.marathon.api;

import com.epam.marathon.auto.core.api.dto.SocialMediaDTO;
import com.epam.marathon.auto.core.api.dto.UserDTO;
import com.epam.marathon.auto.core.api.rest.UsersRestService;
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
