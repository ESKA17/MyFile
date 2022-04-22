package com.company;

import java.io.File;

class MyFile {
    // выводит список всех файлов и директорий для `path` - ls
    public static void listDirectory(String path) {
        path = path.substring(3);
        if (path.isBlank()) {path = "./";}
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        try {
            assert listOfFiles != null;
            for (File fileCheck : listOfFiles) {
                if (fileCheck.isFile() || fileCheck.isDirectory()) {
                    System.out.println(fileCheck.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("ls: cannot access '" + path + "': No such file or directory");
        }
    }
}
