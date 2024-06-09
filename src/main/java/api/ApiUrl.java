package api;

import io.restassured.RestAssured;

public class ApiUrl {
    private String url;

    public void baseUrl() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    }
}
