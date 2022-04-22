package com.company;

import static com.company.MyFile.listDirectory;
import static com.company.MyFile.listPythonFiles;

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        String input;
        while (true){
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            if (input.startsWith("ls ")) {
                listDirectory(input);
            } else if (input.equals("exit")) {
                return;
            } else if (input.startsWith("ls_py ")) {
                listPythonFiles(input);
            } else {
                System.out.println(input + ": not found");
            }
        }
    }
}
