package com.javarestassuredtemplate.dbsteps;

import com.javarestassuredtemplate.utils.DBUtils;
import com.javarestassuredtemplate.utils.GeneralUtils;

import java.util.ArrayList;

public class ProjectsDBSteps {
    private static String queriesPath = System.getProperty("user.dir") + "/src/test/java/com/javarestassuredtemplate/queries/";

    public static void apagarProjetos() {
        String query = GeneralUtils.readFileToAString(queriesPath + "deleteProjetosQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static void cadastrarProjeto() {
        String query = GeneralUtils.readFileToAString(queriesPath + "insertProjetosQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static void apagarProjetosVersion() {
        String query = GeneralUtils.readFileToAString(queriesPath + "deleteProjetosVersionQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static void cadastrarProjetoVersion() {
        String query = GeneralUtils.readFileToAString(queriesPath + "insertProjetosVersionQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static void apagarSubProjetos() {
        String query = GeneralUtils.readFileToAString(queriesPath + "deleteSubProjetosQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static void cadastrarSubProjetos() {
        String query = GeneralUtils.readFileToAString(queriesPath + "insertSubProjetosQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static ArrayList<String> ConsultaProjetoID() {
        ArrayList<String> dados;
        String query = GeneralUtils.readFileToAString(queriesPath + "ConsultaProjeto.sql");
        dados = DBUtils.getQueryResult(query);
        return dados;
    }
}