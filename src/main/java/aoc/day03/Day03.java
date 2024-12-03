package aoc.day03;

import aoc.Day;


public class Day03 implements Day {

    enum CharNext {
        M,
        U,
        L,
        LParen,
        DigitA,
        DigitOrComma,
        Comma,
        DigitB,
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
                    expect_next = CharNext.DigitA;
                    break;
                case DigitA:
                    valid = Character.isDigit(c);
                    numA += c;
                    if (numA.length() == 3) {
                        expect_next = CharNext.Comma;
                    } else {
                        expect_next = CharNext.DigitOrComma; 
                    }
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
                        expect_next = CharNext.DigitB;
                    }
                    break;
                case Comma:
                    valid = c == ',';
                    if (c == ',') {
                        expect_next = CharNext.DigitB;
                    }
                case DigitB:
                    valid = Character.isDigit(c);
                    numB += c;
                    if (numB.length() == 3) {
                        expect_next = CharNext.RParen;
                    } else {
                        expect_next = CharNext.DigitOrRParen; 
                    }
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
                    if (c == ')') {
                        int a = Integer.parseInt(numA);
                        int b = Integer.parseInt(numB);
                        sum += a * b;
                        numA = "";
                        numB = "";
                        expect_next = CharNext.M;
                    }
            }
            if (!valid) {
                numA = "";
                numB = "";
                expect_next = CharNext.M;
            }
        }

        return String.valueOf(sum);
    }


    @Override
    public String part2(String input) {

        return String.valueOf(2);
    }

}
