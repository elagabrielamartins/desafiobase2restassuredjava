package com.javarestassuredtemplate.tests.user;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.UserDBSteps;
import com.javarestassuredtemplate.requests.user.PostUserRequest;
import com.javarestassuredtemplate.steps.IncluirUserSteps;
import com.javarestassuredtemplate.steps.User;
import com.javarestassuredtemplate.utils.CsvUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Iterator;

public class PostUserTests extends TestBase {
    PostUserRequest postUserRequest;
    SoftAssert softAssert;

    @AfterTest
//Limpando base
    public void afterTesteDeletaUser() {
        if (GlobalParameters.ENVIROMENT != "dev"){
//            UserDBSteps.deleteUser();
        }

    }


    //region Fluxos de Sucesso
    @Test
    public void cadastrarUsuariocomSucesso() {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String userName = "Teste Usuario 111 Automacao API";
        String password = "123456";
        String nameReal = "Teste Usuario 111";
        String email = "usuarioautomacao111@testusuario001.com.br";
        String accessLevelName = "updater";
        String enabled = "true";
        String isProtected = "false";
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBodyCadastroUsuarioJson(userName, password, nameReal, email, accessLevelName, enabled, isProtected);
        Response response = postUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("user.name").toString(), userName, "Validação campo: user.name");
        softAssert.assertEquals(response.body().jsonPath().get("user.real_name").toString(), nameReal, "Validação campo: user.realName");
        softAssert.assertEquals(response.body().jsonPath().get("user.email").toString(), email, "Validação campo: user.email");
        softAssert.assertEquals(response.body().jsonPath().get("user.access_level.name").toString(), "updater", "Validação campo: user.access_level.name");
        softAssert.assertAll();
    }

    @DataProvider(name = "cadastrarUsuariosDataDrivenTest")
    public Iterator<Object> dataPetProvider2() {
        return CsvUtils.csvProvider("src/test/java/resources/postAddUser.csv");
    }

    @Test(dataProvider = "cadastrarUsuariosDataDrivenTest")
    public void cadastrarUsuariosDataDrivenTest(User user) {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String userName = user.getUsername();
        String password = user.getPassword();
        String nameReal = user.getRealName();
        String email = user.getEmail();
        String accessLevelName = user.getAccessLevelName();
        String enabled = user.getEnabled();
        String isProtected = user.getIsProtected();
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBodyCadastroUsuarioJson(userName, password, nameReal, email, accessLevelName, enabled, isProtected);
        Response response = postUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("user.name").toString(), userName, "Validação campo: user.name");
        softAssert.assertEquals(response.body().jsonPath().get("user.real_name").toString(), nameReal, "Validação campo: user.realName");
        softAssert.assertEquals(response.body().jsonPath().get("user.email").toString(), email, "Validação campo: user.email");
        softAssert.assertEquals(response.body().jsonPath().get("user.access_level.name").toString(), accessLevelName, "Validação campo: user.access_level.name");
        softAssert.assertAll();
    }

    @Test
    public void cadastrarUsuarioValidacaoRegexEmail() {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String userName = "Teste Usuario 999 Automacao API";
        String password = "123456";
        String nameReal = "Teste Usuario 999";
        String email = "usuarioautomacao999@testusuario999.com.br";
        String accessLevelName = "updater";
        String enabled = "true";
        String isProtected = "false";
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Variaveis
        String regex = "^[A-Za-z0-9+_-]+@[A-Za-z0-9.-]+$";


        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBodyCadastroUsuarioJson(userName, password, nameReal, email, accessLevelName, enabled, isProtected);
        Response response = postUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("user.name").toString(), userName, "Validação name");
        softAssert.assertEquals(response.body().jsonPath().get("user.real_name").toString(), nameReal, "Validação realName");
        softAssert.assertEquals(response.body().jsonPath().get("user.email").toString(), email, "Validação email");
        softAssert.assertTrue(response.body().jsonPath().get("user.email").toString().matches(regex), "Validação email regex");
        softAssert.assertEquals(response.body().jsonPath().get("user.access_level.name").toString(), "updater", "Validação accessLevel name");
        softAssert.assertAll();
    }

    @Test
    public void permitirCadastrarUsuarioComSenhaVazia() {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String userName = "usuario senha vazio";
        String password = "";
        String nameReal = "Usuario senha vazio";
        String email = "usuarioautomacaosenhavazio@senhavazio.com.br";
        String accessLevelName = "updater";
        String enabled = "true";
        String isProtected = "false";
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBodyCadastroUsuarioJson(userName, password, nameReal, email, accessLevelName, enabled, isProtected);
        Response response = postUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("user.name").toString(), userName, "Validação campo: user.name");
        softAssert.assertEquals(response.body().jsonPath().get("user.real_name").toString(), nameReal, "Validação campo: user.realName");
        softAssert.assertEquals(response.body().jsonPath().get("user.email").toString(), email, "Validação campo: user.email");
        softAssert.assertEquals(response.body().jsonPath().get("user.access_level.name").toString(), accessLevelName, "Validação campo: user.access_level.name");
        softAssert.assertAll();
    }


//endregion

    //region Fluxos de exceção
    @Test
    public void cadastroUsuarioRepetido() {
        //Chamadas
        softAssert = new SoftAssert();

        IncluirUserSteps.cadastrarUserNameRepetido(1);

        //Parâmetros
        String userName = "Usuario Cadastro repetido 1";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        String mensagem = "Username '" + userName + "' already used.";
        int codigo = 800;
        String localized = "That username is already being used. Please go back and select another one.";

        //Fluxo
        Response response = IncluirUserSteps.cadastrarUserNameRepetido(2);

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), mensagem, "Validação menssagem username repetido");
        softAssert.assertEquals(response.body().jsonPath().get("code").toString(), String.valueOf(codigo), "Validação codigo da menssagem username repetido");
        softAssert.assertEquals(response.body().jsonPath().get("localized").toString(), localized, "Validação detalhes da  menssagem username repetido");
        softAssert.assertAll();

    }

    @Test
    public void cadastroEmailRepetido() {
        //Chamadas
        softAssert = new SoftAssert();

        IncluirUserSteps.cadastrarEmailRepetido(1);

        //Parâmetros
        String email = "usuarioautomacao551@testusuario551.com.br";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        String mensagem = "Email '" + email + "' already used.";
        int codigo = 813;
        String localized = "That email is already being used. Please go back and select another one.";

        //Fluxo
        Response response = IncluirUserSteps.cadastrarEmailRepetido(2);

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), mensagem, "Validação menssagem email repetido");
        softAssert.assertEquals(response.body().jsonPath().get("code").toString(), String.valueOf(codigo), "Validação codigo da menssagem email repetido");
        softAssert.assertEquals(response.body().jsonPath().get("localized").toString(), localized, "Validação detalhes da menssagem email repetido");
        softAssert.assertAll();

    }

    @Test
    public void cadastrarUsuarioVazio() {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String userName = "";
        String password = "123456";
        String nameReal = "Usuario Cadastro sem informar vazio ";
        String email = "usuarioautomacaovazio@testusuariovazio.com.br";
        String accessLevelName = "reporter";
        String enabled = "true";
        String isProtected = "false";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        String mensagem = "Invalid username '" + userName + "'";
        int codigo = 805;
        String localized = "The username is invalid. Usernames may only contain Latin letters, numbers, spaces, hyphens, dots, plus signs and underscores.";

        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBodyCadastroUsuarioJson(userName, password, nameReal, email, accessLevelName, enabled, isProtected);
        Response response = postUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), mensagem, "Validação menssagem userName vazio");
        softAssert.assertEquals(response.body().jsonPath().get("code").toString(), codigo, "Validação codigo da menssagem userName vazio");
        softAssert.assertEquals(response.body().jsonPath().get("localized").toString(), localized, "Validação detalhes da menssagem userName vazio");
//      softAssert.assertAll();

    }

    @Test
    public void cadastrarUsuarioComAccessLevelInexistente() {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String userName = "usuario Access Level Inexistente ";
        String password = "123456";
        String nameReal = "Usuario Access Level Inexistente";
        String email = "poderosa@poderosa.com.br";
        String accessLevelName = "Poderosa";
        String enabled = "true";
        String isProtected = "false";

        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        String mensagem = "Invalid access level";
        int codigo = 29;
        String localized = "Invalid value for 'access_level'";

        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBodyCadastroUsuarioJson(userName, password, nameReal, email, accessLevelName, enabled, isProtected);
        Response response = postUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), mensagem, "Validação menssagem email vazio");
        softAssert.assertEquals(response.body().jsonPath().get("code").toString(), String.valueOf(codigo), "Validação codigo da menssagem emai vazio");
        softAssert.assertEquals(response.body().jsonPath().get("localized").toString(), localized, "Validação detalhes da menssagem email vazio");
        softAssert.assertAll();

    }
//endregion
}
