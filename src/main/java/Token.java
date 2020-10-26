//package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Token {
    GLOBALS("$GLOBALS"),

    OPERATOR_PLUS_ASSIGN("\\+="),
    OPERATOR_MINUS_ASSIGN("-="),
    OPERATOR_MUL_ASSIGN("\\*="),
    OPERATOR_DIV_ASSIGN("/="),
    OPERATOR_MOD_ASSIGN("%="),
    OPERATOR_LeftShift_ASSIGN("<<="),
    OPERATOR_RightShift_ASSIGN(">>="),
    OPERATOR_CONCAT_ASSIGN("\\.="),

    OPERATOR_INC("\\+\\+"),
    OPERATOR_DEC("--"),

    COMMENT_1("(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/)"),
    COMMENT_2("//[^\\n\\r]+"),

    OPERATOR_MINUS ("-"),
    OPERATOR_PLUS ("\\+"),
    OPERATOR_MUL ("\\*"),
    OPERATOR_DIV ("/"),
    OPERATOR_MOD("%"),
    OPERATOR_CONCAT("\\."),

    OPERATOR_LEG ("<="),
    OPERATOR_GEQ (">="),
    OPERATOR_EQ ("=="),
    OPERATOR_NOT_EQ ("!="),
    OPERATOR_EqVal_OR_EqType ("==="),
    OPERATOR_NotEqVal_OR_NotEqType ("!=="),

    OPERATOR_LESS ("<"),
    OPERATOR_GT (">"),

    OPERATOR_ASSIGN ("="),

    TK_OPEN ("\\("),
    TK_CLOSE ("\\)"),
    TK_SEMI (";"),
    TK_COMMA (","),

    SYS_WORD_IF ("if"),
    SYS_WORD_ELSE ("else"),
    SYS_WORD_ELSEIF ("elseif"),
    SYS_WORD_FOR ("for"),
    SYS_WORD_WHILE ("while"),
    SYS_WORD_DO ("do"),
    SYS_WORD_BREAK ("break"),
    SYS_WORD_CONT ("continue"),
    SYS_WORD_VAR ("\\$[a-zA-Z_]+[0-9a-zA-Z_]*"),
    SYS_WORD_CONST ("const"),
    SYS_WORD_NULL ("null"),
    SYS_WORD_TRUE ("true"),
    SYS_WORD_FALSE ("false"),
    SYS_WORD_CASE ("case"),
    SYS_WORD_FUNC ("function"),
    SYS_WORD_RETURN ("return"),
    SYS_WORD_PRINT ("print"),
    SYS_WORD_ECHO ("echo"),



    TK_OPEN_BRACKET ("\\{"),
    TK_CLOSE_BRACKET ("\\}"),

    TYPE_STRING ("(\"[^\"]+\")"),
    TYPE_EMPTY_STRING("(\"\")"),
    TYPE_CHAR ("'.'"),
    TYPE_REAL("(\\d*)\\.\\d+"),
    TYPE_HEX("0[xX][0-9]*[a-fA-F]*"),
    TYPE_INTEGER ("\\d+");
    //IDENTIFIER ("\\w+");

    private final Pattern pattern;

    Token(String regex) {
        pattern = Pattern.compile("^" + regex);
    }

    int endOfMatch(String s) {
        Matcher m = pattern.matcher(s);

        if (m.find()) {
            return m.end();
        }
        return -1;
    }
}
