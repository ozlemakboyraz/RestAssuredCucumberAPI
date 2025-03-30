package com.otelrezarvasyonu.tests;


import com.otelrezarvasyonu.models.Booking;
import com.otelrezarvasyonu.models.BookingDates;
import com.otelrezarvasyonu.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateBookingTest extends BaseTest{


    @Test
    public void createBookingTest(){

        // çağrı gerçekleştir

        Response response =  createBooking();

        // assertionları yaz

        Assertions.assertEquals("Ozl" , response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Asd" , response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(111,(Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true , response.jsonPath().getJsonObject("booking.depositpaid"));
    }


    @Test
    public void createBookingWithPojo(){

        BookingDates bookingDates = new BookingDates("2025-01-02" , "2025-3-21");
        Booking booking = new Booking("Deneme" , "Eğitim" , 520 , false , bookingDates, "Airport Transfer");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");


        response
                .then()
                .statusCode(200);

        BookingResponse bookingResponse = response.as(BookingResponse.class); // gelen response u BookingResponse class içine yazılıyor. bunu da bookingResponse pojo objesinde tutacağız
        System.out.println(bookingResponse + " Booking response kaydedildi");

        Assertions.assertEquals("Deneme", bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Eğitim" , bookingResponse.getBooking().getLastname());
        Assertions.assertEquals("Airport transfer" , bookingResponse.getBooking().getAdditionalneeds());

    }


}
