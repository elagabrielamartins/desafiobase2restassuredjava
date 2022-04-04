package com.javarestassuredtemplate.dbsteps;

import com.javarestassuredtemplate.utils.DBUtils;
import com.javarestassuredtemplate.utils.GeneralUtils;

import java.util.ArrayList;

public class ProjectsDBSteps {
    private static String queriesPath = System.getProperty("user.dir") + "/src/test/java/com/javarestassuredtemplate/queries/";

    public static void apagarProjetos() {
        String query = GeneralUtils.readFileToAString(queriesPath + "apagarProjetos.sql");
        DBUtils.delete(query);
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

//    public static ArrayList<String> ConsultaProjetoID() {
//        ArrayList<String> dados;
//        String query = GeneralUtils.readFileToAString(queriesPath + "consultaProjeto.sql");
//        dados = DBUtils.getQueryResult(query);
//        return dados;
//    }

    public static ArrayList<String> ConsultaProjetoID(Integer id, Integer limit) {
        ArrayList<String> dados;
        String query = (GeneralUtils.readFileToAString(queriesPath + "consultaProjeto.sql"));
        if (id != null) {
            query = query + " WHERE ID" + id;
        }

        if (limit != null) {
            query = query + " LIMIT " + limit;
        } else {
            query = query + " LIMIT 1 ";
        }

        dados = DBUtils.getQueryResult(query);
        return dados;
    }
}