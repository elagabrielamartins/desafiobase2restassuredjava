package com.javarestassuredtemplate.tests.user;

import com.javarestassuredtemplate.bases.TestBase;
//import com.javarestassuredtemplate.dbsteps.UserDBSteps;
import com.javarestassuredtemplate.dbsteps.user.ConsultaUserDBSteps;
import com.javarestassuredtemplate.requests.user.GetUserRequest;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;

public class GetUserTests extends TestBase {
    GetUserRequest getUserRequest;
    SoftAssert softAssert;

    @Test
    public void buscarUsuarioAdministrador(){
        softAssert = new SoftAssert();

        //Parâmetros
        String name = "administrator";
        String email = "root@localhost";

        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getUserRequest = new GetUserRequest();
        Response response  = getUserRequest.executeRequest2();


        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("name").toString(), name, "Validação name retorno consulta");
        softAssert.assertEquals(response.body().jsonPath().get("email").toString(), email, "Validação email");
        softAssert.assertAll();

    }

    @Test
    public void buscarUsuarioADMviaQuery()  {
        softAssert = new SoftAssert();
        //Parâmetros
        ArrayList<String> consultaUsuarioADM =  ConsultaUserDBSteps.consultaUsuarioADM();
        String id = consultaUsuarioADM.get(0);
        String userName = consultaUsuarioADM.get(1);
        String email = consultaUsuarioADM.get(2);
        String acessLevel = consultaUsuarioADM.get(3);
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getUserRequest = new GetUserRequest();
        Response response  = getUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("id").toString(), id, "Validação id retorno consulta");
        softAssert.assertEquals(response.body().jsonPath().get("name").toString(), userName, "Validação name retorno consulta");
        softAssert.assertEquals(response.body().jsonPath().get("email").toString(), email, "Validação email");
        softAssert.assertEquals(response.body().jsonPath().get("access_level.id").toString(), acessLevel, "Validação nivel de acesso retorno consulta");
        softAssert.assertAll();
    }

//    @Test
//    public void buscarUsuarioADMviaQuery(){
//        SoftAssert softAssert = new SoftAssert();
//
//        //Parâmetros
////        ArrayList<String> list = UserDBSteps.selectUsuarioADM();
//        String id = list.get(0);
//        String name = list.get(1);
//        String email = list.get(2);
//        String accessLevel = list.get(3);
//        int statusCodeEsperado = HttpStatus.SC_OK;
//
//        //Fluxo
//        getUserRequest = new GetUserRequest();
//        Response response = getUserRequest.executeRequest2();
//
//        //Asserções
//        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
//        softAssert.assertEquals(response.body().jsonPath().get("id").toString(), id, "Validação campo: user.id");
//        softAssert.assertEquals(response.body().jsonPath().get("name").toString(), name, "Validação campo: user.name");
//        softAssert.assertEquals(response.body().jsonPath().get("email").toString(), email, "Validação campo: user.email");
//        softAssert.assertEquals(response.body().jsonPath().get("access_level.id").toString(), accessLevel, "Validação campo: access_level.id");
//        softAssert.assertAll();
//    }


}
