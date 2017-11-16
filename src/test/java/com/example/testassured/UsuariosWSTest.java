package com.example.testassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class UsuariosWSTest {

    private Usuario mauricio;
    private Usuario guilherme;

    @Before
    public void setUp(){
        mauricio = new Usuario(1L, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");
        guilherme = new Usuario(2L, "Guilherme Silveira", "guilherme.silveira@caelum.com.br");

//        RestAssured.baseURI = "http://www.endereco-do-meu-ws.com.br";
//        RestAssured.port = 8080;

    }

    @Test
    public void deveRetornarListaDeUsuarios() {


        XmlPath path = given()
                .accept("application/xml")
                .get("/usuarios?_format=xml")
                .andReturn().xmlPath();

        List<Usuario> usuarios = path.getList("list.usuario", Usuario.class);


        assertEquals(mauricio, usuarios.get(0));
        assertEquals(guilherme, usuarios.get(1));
    }

    @Test
    public void deveRetornarUsuarioPeloId(){
        JsonPath path = given()
                .header("Accept", "application/json")
                .param("usuario.id", 1)
                .get("/usuarios/show")
                .andReturn().jsonPath();

        Usuario usuario = path.getObject("usuario", Usuario.class);

        assertEquals(usuario, mauricio );

    }

    @Test
    public void deveAdicionarUmUsuario(){
        Usuario joao = new Usuario("Joao da Silva", "joao@dasilva.com");

        XmlPath path = given()
                .accept("application/xml")
                    .contentType("application/xml")
                    .body(joao)
                .expect()
                    .statusCode(200)
                .when()
                    .post("/usuarios")
                .andReturn()
                    .xmlPath();

        Usuario resposta = path.getObject("usuario", Usuario.class);

        assertEquals("Joao da Silva",resposta.getNome());
        assertEquals("joao@dasilva.com",resposta.getEmail());

            given()
                .contentType("application/xml")
                .body(resposta)
            .when()
                .delete("/usuarios/deleta")
                .andReturn().asString();

    }


}