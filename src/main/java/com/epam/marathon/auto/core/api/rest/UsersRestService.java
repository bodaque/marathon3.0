package com.epam.marathon.auto.core.api.rest;

import com.epam.marathon.auto.core.BASE_URI;
import com.epam.marathon.auto.core.api.dto.UserDTO;
import com.epam.marathon.auto.exception.UpamersTAException;
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
