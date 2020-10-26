//package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Lexer {
    private final StringBuilder input = new StringBuilder();
    private Token token;
    private String lexema;
    private boolean exhausted = false;
    private String errorMessage = "";
    private final Set<Character> blankChars = new HashSet<Character>();

    public Lexer(String filePath) {
        try {
            File file = new File("1.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            input.append(line);

            while (line != null) {
                line = reader.readLine();
                if(line == null || line.toString().equals(""))
                    continue;
                input.append('\n');
                input.append(line);
            }

        } catch (IOException ex) {
            exhausted = true;
            errorMessage = "Could not read file: " + filePath;
            return;
        }

        blankChars.add('\r');
        blankChars.add('\n');
        blankChars.add((char) 8);
        blankChars.add((char) 9);
        blankChars.add((char) 11);
        blankChars.add((char) 12);
        blankChars.add((char) 32);

        moveAhead();
    }

    public void moveAhead() {

        if (exhausted) {
            return;
        }

        if (input.length() == 0) {
            exhausted = true;
            return;
        }

        ignoreWhiteSpaces();

        if (findNextToken()) {
            return;
        }

        exhausted = true;

        if (input.length() > 0) {
            errorMessage += "Unexpected symbol: '" + input.charAt(0) + "'\n";
        }
    }

    private void ignoreWhiteSpaces() {
        int charsToDelete = 0;

        while (blankChars.contains(input.charAt(charsToDelete))) {
            charsToDelete++;
        }

        if (charsToDelete > 0) {
            input.delete(0, charsToDelete);
        }
    }

    private boolean findNextToken() {
        for (Token t : Token.values()) {
            int end = t.endOfMatch(input.toString());


            if (end != -1) {
                token = t;
                lexema = input.substring(0, end);
                input.delete(0, end);
                return true;
            }
        }

        return false;
    }

    public StringBuilder getInput(){
        return input;
    }

    public Token currentToken() {
        return token;
    }

    public String currentLexema() {
        return lexema;
    }

    public boolean isSuccessful() {
        return errorMessage.isEmpty();
    }

    public String errorMessage() {
        return errorMessage;
    }

    public boolean isExhausted() {
        return exhausted;
    }
}
