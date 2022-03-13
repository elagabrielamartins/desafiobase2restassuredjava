package com.javarestassuredtemplate.utils;

import com.javarestassuredtemplate.steps.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvUtils {
    public static Iterator<Object> csvProvider(String csvNamePath) {
        String line = "";
        String cvsSplitBy = ",";
        List<Object> testCases = new ArrayList<Object>();
        String[] data = null;

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csvNamePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            data = line.split(cvsSplitBy);

            User u = new User();
            u.setUsername(data[0]);
            u.setPassword(data[1]);
            u.setRealName(data[2]);
            u.setEmail(data[3]);
            u.setAccessLevelName(data[4]);
            u.setEnabled(data[5]);
            u.setIsProtected(data[6]);

            testCases.add(u);
        }
        return testCases.iterator();
    }
}
