package com.javarestassuredtemplate.dbsteps;

import com.javarestassuredtemplate.utils.DBUtils;
import com.javarestassuredtemplate.utils.GeneralUtils;

public class FilterDBSteps {
    private static String queriesPath = System.getProperty("user.dir") + "/src/test/java/com/javarestassuredtemplate/queries/";

    public static void insertFilter(int idFilter, String nameFilter, int projectId) {
        String query = GeneralUtils.readFileToAString(queriesPath + "insertFilter.sql");
        query = query.replace("$IDFILTER", String.valueOf(idFilter));
        query = query.replace("$PROJECTID", String.valueOf(projectId));
        query = query.replace("$NAMEFILTER", nameFilter);

        DBUtils.executeUpdate(query);
    }

    public static void apagarFilters() {
        String query = GeneralUtils.readFileToAString(queriesPath + "deleteFilter.sql");
        DBUtils.delete(query);
    }
}