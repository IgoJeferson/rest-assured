package com.example.testassured;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class LeiloesWSTest {

    private Usuario mauricio;
    private Usuario guilherme;

    @Before
    public void setUp(){
        mauricio = new Usuario(1L, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");
        guilherme = new Usuario(2L, "Guilherme Silveira", "guilherme.silveira@caelum.com.br");
    }

    @Test
    public void deveRetornarLeilaoPeloId() {
        JsonPath path = given()
                .header("Accept", "application/json")
                .param("leilao.id", 1)
                .get("/leiloes/show")
                .andReturn()
                .jsonPath();

        Leilao leilao = path.getObject("leilao", Leilao.class);

        Leilao geladeira = new Leilao(1L, "Geladeira", 800, mauricio, false);

        assertEquals(leilao, geladeira);

    }

//    @Test
//    public void deveRetornarTotalDeLeiloes(){
//        int total = given()
//                .header("Accept", "application/json")
//                .get("/leiloes/total")
//                .andReturn()
//                .jsonPath()
//                .getInt("int");
//
//        assertEquals(5, total);
//
//    }

    @Test
    public void deveAdicionarUmLeilao(){

        Leilao fogao = new Leilao(1L, "Fogao", 650, mauricio, true);

        XmlPath path = given()
                .accept("application/xml")
                    .contentType("application/xml")
                    .body(fogao)
                .expect()
                 .statusCode(200)
                .when()
                    .post("/leiloes")
                .andReturn()
                    .xmlPath();

        Leilao resposta = path.getObject("leilao", Leilao.class);

        assertEquals("Fogao", resposta.getNome());
        assertEquals(true, resposta.isUsado());

        given()
                .contentType("application/xml")
                .body(resposta)
                .when()
                .delete("/leiloes/deleta")
                .andReturn().asString();


    }
}
