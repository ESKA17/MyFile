package com.company;

import java.io.File;

class MyFile {
    // выводит список всех файлов и директорий для `path` - ls
    public static void listDirectory(String path) {
        File[] listOfFiles = listMeFiles(path, 3);
        try {
            if (listOfFiles == null) {
                System.out.println("No files or folders in the directory!");
                return;
            }
            for (File fileCheck : listOfFiles) {
                if (fileCheck.isFile() || fileCheck.isDirectory()) {
                    System.out.println(fileCheck.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("ls: cannot access '" + path + "': No such file or directory");
        }
    }

    // выводит список файлов с расширением `.py` в `path` - ls_py
    public static void listPythonFiles(String path) {
        File[] listOfFiles = listMeFiles(path, 6);
        try {
            if (listOfFiles == null) {
                System.out.println("No .py files in the directory!");
                return;
            }
            for (File fileCheck : listOfFiles) {
                if (fileCheck.isFile() && fileCheck.getName().contains(".py")) {
                    System.out.println(fileCheck.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("ls_py: cannot access '" + path + "': No such file or directory");
        }
    }
    // выводит `true`, если `path` это директория, в других случаях `false` - is_dir


    private static File[] listMeFiles(String path, int subStr) {
        path = path.substring(subStr);
        if (path.isBlank()) {path = "./";}
        File folder = new File(path);
        return folder.listFiles();
    }
}
