package com.example.testassured;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

import org.junit.Test;

import java.io.File;

public class OutrosTest {

    @Test
    public void deveGerarUmCookie(){
        expect()
                .cookie("rest-assured","funciona")
        .when()
            .get("/cookie/test");
    }

    @Test
    public void deveGerarUmHeader(){
        expect()
            .header("novo-header","abc")
        .when()
            .get("/cookie/test");
    }

    @Test
    public void enviaArquivo(){
        given()
                .multiPart(new File("/path/para/arquivo"))
        .when()
                .post("/upload");
    }
}
