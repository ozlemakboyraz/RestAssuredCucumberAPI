package com.otelrezarvasyonu.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTest extends BaseTest{


    @Test
    public void updateBookingTest(){


        //çağrı (request) yap
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + createToken())
                .body(bookingObject("Abc" , "Test" , 520 , false))
                .put("/booking/" + createBookingId());


        // assertionları yaz

       String firstName = response.jsonPath().getJsonObject("firstname");
       String lastName = response.jsonPath().getJsonObject("lastname");
       int totalPrice = response.jsonPath().getJsonObject("totalprice");
       boolean depositPaid = response.jsonPath().getJsonObject("depositpaid");

       Assertions.assertEquals("Abc" , firstName);
       Assertions.assertEquals("Test" , lastName);
       Assertions.assertEquals(520 , totalPrice);
       Assertions.assertEquals(false , depositPaid);


    }



}
