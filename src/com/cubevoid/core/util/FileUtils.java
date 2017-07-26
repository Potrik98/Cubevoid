package com.cubevoid.core.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class FileUtils {
    public static String loadResource(String fileName)  {
        String result;
        try (InputStream in = new FileInputStream(fileName)) {
            result = new Scanner(in, "UTF-8").useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to locate resource " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to read resource " + fileName);
        }
        return result;
    }
}