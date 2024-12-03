package aoc.day03;

import aoc.Day;


public class Day03 implements Day {

    enum CharNext {
        M,
        U,
        L,
        LParen,
        FirstDigitA,
        DigitOrComma,
        Comma,
        FirstDigitB,
        DigitOrRParen,
        RParen;
    }

    @Override
    public String part1(String input) {
        CharNext expect_next = CharNext.M;
        String numA = "";
        String numB = "";
        int sum = 0;
        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            Boolean valid = false;
            switch (expect_next) {
                case M:
                    valid = c == 'm';
                    expect_next = CharNext.U;
                    break;
                case U:
                    valid = c == 'u';
                    expect_next = CharNext.L;
                    break;
                case L:
                    valid = c == 'l';
                    expect_next = CharNext.LParen;
                    break;
                case LParen:
                    valid = c == '(';
                    expect_next = CharNext.FirstDigitA;
                    break;
                case FirstDigitA:
                    valid = Character.isDigit(c);
                    numA += c;
                    expect_next = CharNext.DigitOrComma;
                    break;
                case DigitOrComma:
                    valid = Character.isDigit(c) || c == ',';
                    if (Character.isDigit(c)) {
                        numA += c;
                        if (numA.length() == 3) {
                            expect_next = CharNext.Comma;
                        } else {
                            expect_next = CharNext.DigitOrComma; 
                        }
                    } else if (c == ',') {
                        expect_next = CharNext.FirstDigitB;
                    }
                    break;
                case Comma:
                    valid = c == ',';
                    if (valid) {
                        expect_next = CharNext.FirstDigitB;
                    }
                    break;
                case CharNext.FirstDigitB:
                    valid = Character.isDigit(c);
                    numB += c;
                    expect_next = CharNext.DigitOrRParen; 
                    break;
                case DigitOrRParen:
                    valid = Character.isDigit(c) || c == ')';
                    if (Character.isDigit(c)) {
                        numB += c;
                        if (numB.length() == 3) {
                            expect_next = CharNext.RParen;
                        } else {
                            expect_next = CharNext.DigitOrRParen; 
                        }
                    } else if (c == ')') {
                        int a = Integer.parseInt(numA);
                        int b = Integer.parseInt(numB);
                        sum += a * b;
                        numA = "";
                        numB = "";
                        expect_next = CharNext.M;
                    }
                    break;
                case RParen:
                    valid = c == ')';
                    if (valid) {
                        int a = Integer.parseInt(numA);
                        int b = Integer.parseInt(numB);
                        sum += a * b;
                        numA = "";
                        numB = "";
                        expect_next = CharNext.M;
                    }
                    break;
            }
            if (!valid) {
                numA = "";
                numB = "";
                expect_next = CharNext.M;
            }
        }

        return String.valueOf(sum);
    }

    enum DoCharNext {
        D,
        O,
        LParen,
        RParen;
    }
    
    enum DontCharNext {
        D,
        O,
        N,
        Apostrophe,
        T,
        LParen,
        RParen;
    }


    @Override
    public String part2(String input) {
        CharNext expect_next = CharNext.M;
        DoCharNext do_next = DoCharNext.D;
        DontCharNext dont_next = DontCharNext.D;
        String numA = "";
        String numB = "";
        int sum = 0;
        Boolean do_mul = true;
        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            Boolean valid = false;

            switch (do_next) {
                case D:
                    if (c == 'd') {
                        do_next = DoCharNext.O;
                    } else {
                        do_next = DoCharNext.D;
                    }
                    break;
                case O:
                    if (c == 'o') {
                        do_next = DoCharNext.LParen;
                    } else {
                        do_next = DoCharNext.D;
                    }
                    break;
                case LParen:
                    if (c == '(') {
                        do_next = DoCharNext.RParen;
                    } else {
                        do_next = DoCharNext.D;
                    }
                    break;
                case RParen:
                    if (c == ')') {
                        do_mul = true;
                    } else {
                        do_next = DoCharNext.D;
                    }
                    break;
            }

            switch (dont_next) {
                case D:
                    if (c == 'd') {
                        dont_next = DontCharNext.O;
                    } else {
                        dont_next = DontCharNext.D;
                    }
                    break;
                case O:
                    if (c == 'o') {
                        dont_next = DontCharNext.N;
                    } else {
                        dont_next = DontCharNext.D;
                    }
                    break;
                case N:
                    if (c == 'n') {
                        dont_next = DontCharNext.Apostrophe;
                    } else {
                        dont_next = DontCharNext.D;
                    }
                    break;
                case Apostrophe:
                    if (c == '\'') {
                        dont_next = DontCharNext.T;
                    } else {
                        dont_next = DontCharNext.D;
                    }
                    break;
                case T:
                    if (c == 't') {
                        dont_next = DontCharNext.LParen;
                    } else {
                        dont_next = DontCharNext.D;
                    }
                    break;
                case LParen:
                    if (c == '(') {
                        dont_next = DontCharNext.RParen;
                    } else {
                        dont_next = DontCharNext.D;
                    }
                    break;
                case RParen:
                    if (c == ')') {
                        do_mul = false;
                    } else {
                        dont_next = DontCharNext.D;
                    }
                    break;
            }

            switch (expect_next) {
                case M:
                    valid = c == 'm';
                    expect_next = CharNext.U;
                    break;
                case U:
                    valid = c == 'u';
                    expect_next = CharNext.L;
                    break;
                case L:
                    valid = c == 'l';
                    expect_next = CharNext.LParen;
                    break;
                case LParen:
                    valid = c == '(';
                    expect_next = CharNext.FirstDigitA;
                    break;
                case FirstDigitA:
                    valid = Character.isDigit(c);
                    numA += c;
                    expect_next = CharNext.DigitOrComma;
                    break;
                case DigitOrComma:
                    valid = Character.isDigit(c) || c == ',';
                    if (Character.isDigit(c)) {
                        numA += c;
                        if (numA.length() == 3) {
                            expect_next = CharNext.Comma;
                        } else {
                            expect_next = CharNext.DigitOrComma; 
                        }
                    } else if (c == ',') {
                        expect_next = CharNext.FirstDigitB;
                    }
                    break;
                case Comma:
                    valid = c == ',';
                    if (valid) {
                        expect_next = CharNext.FirstDigitB;
                    }
                    break;
                case CharNext.FirstDigitB:
                    valid = Character.isDigit(c);
                    numB += c;
                    expect_next = CharNext.DigitOrRParen; 
                    break;
                case DigitOrRParen:
                    valid = Character.isDigit(c) || c == ')';
                    if (Character.isDigit(c)) {
                        numB += c;
                        if (numB.length() == 3) {
                            expect_next = CharNext.RParen;
                        } else {
                            expect_next = CharNext.DigitOrRParen; 
                        }
                    } else if (c == ')') {
                        if (do_mul) {
                            int a = Integer.parseInt(numA);
                            int b = Integer.parseInt(numB);
                            sum += a * b;
                        }
                        numA = "";
                        numB = "";
                        expect_next = CharNext.M;
                    }
                    break;
                case RParen:
                    valid = c == ')';
                    if (valid) {
                        if (do_mul) {
                            int a = Integer.parseInt(numA);
                            int b = Integer.parseInt(numB);
                            sum += a * b;
                        }
                        numA = "";
                        numB = "";
                        expect_next = CharNext.M;
                    }
                    break;
            }
            if (!valid) {
                numA = "";
                numB = "";
                expect_next = CharNext.M;
            }
        }

        return String.valueOf(sum);
    }

}
