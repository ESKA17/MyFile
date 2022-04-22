package com.company;

import static com.company.MyFile.listDirectory;

import java.util.Objects;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        String input = null;
        while (!Objects.equals(input, "exit")) {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            if (input.startsWith("ls ")) {
                listDirectory(input);
            } else {
                System.out.println(input + ": not found");
            }
        }
    }
}
