package com.javarestassuredtemplate.dbsteps;

import com.javarestassuredtemplate.utils.DBUtils;
import com.javarestassuredtemplate.utils.GeneralUtils;

import java.util.ArrayList;

public class UserDBSteps {
    private static String queriesPath = System.getProperty("user.dir")+"/src/test/java/com/javarestassuredtemplate/queries/";

    public static ArrayList<String> consultaUsuarioADM(){
        ArrayList <String> dados;
        String query = GeneralUtils.readFileToAString(queriesPath + "consultaUsuarioAdm.sql");
        dados = DBUtils.getQueryResult(query);
        return dados;
    }
    public static void deleteUser() {
        String query = GeneralUtils.readFileToAString(queriesPath + "deleteUser.sql");
        DBUtils.executeUpdate(query);
    }

}
