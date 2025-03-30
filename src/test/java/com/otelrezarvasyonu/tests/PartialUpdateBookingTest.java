package com.otelrezarvasyonu.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PartialUpdateBookingTest extends BaseTest{


    @Test
    public void partialUpdateBookingTest(){

        JSONObject body = new JSONObject();
        body.put("firstname", "Ayse");


        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie" , "token=" + createToken())
                .body(body.toString())
                .when()
                .patch("/booking/" + createBookingId());


        //assertion/test yaz

        Assertions.assertEquals("Ayse" ,response.jsonPath().getJsonObject("firstname"));

    }
}
