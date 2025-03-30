package com.otelrezarvasyonu.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllBookingsTest extends  BaseTest{


    @Test
    public void getAllBookingTest(){

        given(spec)
                .when()
                .get("/booking")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getBookings_with_firstname_filter_test(){

        //yeni rezervasyon oluştur
        int bookingId = createBookingId();

        // çağrıya query parametresi ekle
        //çağrıyı yap

        Response response = given(spec)
                .queryParam("firstname", "Ozl")
                .queryParam("lastname", "Asd")
                .when()
                .get("/booking");



        //assertion/test  yaz
        response
                .then()
                .statusCode(200);

        List<Integer> filterList = response.jsonPath().getList("bookingid");
        Assertions.assertTrue(filterList.contains(bookingId));

    }
}
