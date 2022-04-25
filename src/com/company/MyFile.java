package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

class MyFile {
    // выводит список всех файлов и директорий для `path` - ls
    public static void listDirectory(String path) {
        try {
            File folder = fileObject(path , 2);
            if (!folder.exists()) {
                System.out.println("ls: cannot access '" + path + "': No such file or directory");
                return;
            }
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            int counter = 0;
            for (File fileCheck : listOfFiles) {
                BasicFileAttributes basicFileAttributes = Files.readAttributes(fileCheck.toPath(),
                        BasicFileAttributes.class);
                if (basicFileAttributes.isRegularFile() || basicFileAttributes.isDirectory()) {
                    System.out.println(fileCheck.getName()); counter++;
                }
            }
            if (counter == 0) {System.out.println("No files or folders in the directory!");}
        } catch (Exception e) {
            System.out.printf("%s", e);
        }
    }

    // выводит список файлов с расширением `.py` в `path` - ls_py
    public static void listPythonFiles(String path) {
        try{
            File folder = fileObject(path , 5);
            if (!folder.exists()) {
                System.out.println("ls_py: cannot access '" + path + "': No such file or directory");
                return;
            }
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            int counter = 0;
            for (File fileCheck : listOfFiles) {
                if (fileCheck.isFile() && fileCheck.getName().contains(".py")) {
                    System.out.println(fileCheck.getName()); counter++;
                }
            }
            if (counter == 0) {System.out.println("No .py files in the directory!");}
        } catch (Exception e) {
            System.out.printf("%s", e);
        }
    }

    // выводит `true`, если `path` это директория, в других случаях `false` - is_dir
    public static void isDirectory(String path) {
        try {
            File folder = fileObject(path, 6);
            if (!folder.exists()) {
                System.out.println("is_dir: cannot access '" + path + "': No such file or directory");
                return;
            }
            System.out.println(folder.isDirectory());
        } catch (Exception e) {
            System.out.printf("%s", e);
        }
    }

    // выводит `директория` или `файл` в зависимости от типа `path` - define
    public static void define(String path) {
        try {
            File file = fileObject(path, 6);
            if (!file.exists()) {
                System.out.println("define: cannot access '" + path + "': No such file or directory");
                return;
            }
            if (file.isDirectory()) {
                System.out.println("directory");
            } else {
                System.out.println("file");
            }
        } catch (Exception e) {
            System.out.printf("%s", e);
        }
    }

    // выводит права для файла в формате `rwx` для текущего пользователя - readmod
    public static void printPermissions(String path) {
        String outString = "";
        try {
            File file = fileObject(path, 7);
            if (!file.exists()) {
                System.out.println("readmod: cannot access '" + path + "': No such file or directory");
                return;
            }
            if (file.canRead()) {
                outString = outString + "r";
            } else {
                outString = outString + "-";
            }
            if (file.canWrite()) {
                outString = outString + "w";
            } else {
                outString = outString + "-";
            }
            if (file.canExecute()) {
                outString = outString + "x";
            } else {
                outString = outString + "-";
            }
            System.out.println(outString);
        } catch (Exception e) {
            System.out.printf("%s", e);
        }
    }

    // устанавливает права для файла `path` - setmod
    public static void setPermissions(String path, String permissions) {
        try {
            File file = fileObject(path, 6);
            if (!file.exists()) {
                System.out.println("setmod: cannot access '" + path + "': No such file or directory");
                return;
            }
            if (permissions.charAt(0) == 'r' ) {
               if (!file.setReadable(true)) {
                   System.out.println("Read permission denied!");
               } else {
                   System.out.println("Read permission is set");
               }
            }
            if (permissions.charAt(1) == 'w') {
                if(!file.setWritable(true)) System.out.println("Write permission denied!");
            } else {
                System.out.println("Write permission is set");
            }
            if (permissions.charAt(2) == 'x') {
                if (!file.setExecutable(true)) System.out.println("Execute permission denied!");
            } else {
                System.out.println("Execute permission is set");
            }
        } catch (Exception e) {
            System.out.printf("%s", e);
        }
    }

    // выводит контент файла - cat
    public static void printContent(String path) {
        File file = fileObject(path, 3);
        if (!file.exists()) {
            System.out.println("cat: cannot access '" + path + "': No such file or directory");
            return;
        }
        Scanner input;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (input.hasNextLine())
        {
            System.out.println(input.nextLine());
        }
    }

    private static File fileObject(String path, int subStr) {
        path = path.substring(subStr).stripLeading().stripTrailing();
        if (path.isBlank()) {path = "./";}
        return new File(path);
    }

    // добавляет строке `# Autogenerated line` в конец `path` - append
    public static void appendFooter(String path) {

    }
    public static void main(String[] args) {
        String input;
        String helpText = """
                MyFS 1.0 команды:
                ls <path>               выводит список всех файлов и директорий для `path`
                ls_py <path>            выводит список файлов с расширением `.py` в `path`
                is_dir <path>           выводит `true`, если `path` это директория, в других случаях `false`
                define <path>           выводит `директория` или `файл` в зависимости от типа `path`
                readmod <path>          выводит права для файла в формате `rwx` для текущего пользователя
                setmod <path> <perm>    устанавливает права для файла `path`
                cat <path>              выводит контент файла
                append <path>           добавляет строку `# Autogenerated line` в конец `path`
                bc <path>               создает копию `path` в директорию `/tmp/${date}.backup` где, date - это дата в формате `dd-mm-yyyy`
                greplong <path>         выводит самое длинное слово в файле
                help                    выводит список команд и их описание
                exit                    завершает работу программы""";
        System.out.println(helpText);
        while (true){
            System.out.print("> ");
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            if (input.equals("exit")) {
                return;
            } else if (input.equals("help")) {
                System.out.println(helpText);
            } else if (input.startsWith("ls_py")) {
                listPythonFiles(input);
            } else if (input.startsWith("ls")) {
                listDirectory(input);
            } else if (input.startsWith("is_dir")) {
                isDirectory(input);
            } else if (input.startsWith("define")) {
                define(input);
            } else if (input.startsWith("readmod")) {
                printPermissions(input);
            } else if (input.startsWith("setmod")) {
                if (!input.contains(" ")) {
                    System.out.println(input + ":not found"); continue;
                }
                String permission = input.substring(input.length() - 3);
                input = input.substring(0, input.length() - 3);
                setPermissions(input, permission);
            } else if (input.startsWith("setmod")) {
                printContent(input);
            } else {
                System.out.println(input + ": not found");
            }
        }
    }
}
