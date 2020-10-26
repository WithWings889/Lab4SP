//package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static String readFromFile(String file) throws IOException {
        FileReader reader = new FileReader(file);
        int symbol;
        StringBuilder text = new StringBuilder();

        while ((symbol = reader.read()) != -1) {
            text.append((char) symbol);
        }
        reader.close();
        return text.toString();
    }

    public static void main(String[] args) {

        Lexer lexer = new Lexer("1.txt");

        try (FileWriter writer = new FileWriter("res.txt")){
            while (!lexer.isExhausted()) {

                System.out.printf("%-18s :  %s \n",lexer.currentLexema() , lexer.currentToken());
                lexer.moveAhead();
            }

            if (lexer.isSuccessful()) {
                System.out.println("END OF WORK!");
            } else {
                System.out.println(lexer.errorMessage());
            }

            writer.close();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
