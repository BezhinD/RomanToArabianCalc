package com.kataacademy.calculator;
import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

public class RomanToArabianCalc {
    private static final String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV",
            "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII",
            "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV",
            "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI",
            "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII",
            "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };

    public static void main(String[] args) throws Exception {
        System.out.println("Введите арифметическое выражение из 2х числе от 0 до 10 или от I до X");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        //      Заполняем символьный массив символами строки которую ввел пользователь и по ходу ловим знак операции
        char[] under_char = new char[10];
        char operation = ' ';
        for (int i = 0; i < userInput.length(); i++) {
            under_char[i] = userInput.charAt(i);
            if (under_char[i] == '+') {
                operation = '+';
            }
            if (under_char[i] == '-') {
                operation = '-';
            }
            if (under_char[i] == '*') {
                operation = '*';
            }
            if (under_char[i] == '/') {
                operation = '/';
            }
        }
        String under_charString = String.valueOf(under_char);
        String[] terms = under_charString.split("[+-/*]");
        if (terms.length < 2) {
            throw new Exception("Строка не является математической операцией");
        }
        if (terms.length > 2) {
            throw new Exception("т.к. формат математической операции не удовлетворяет заданию" +
                    " - два операнда и один оператор (+, -, /, *)");
        }
        String term1 = terms[0].trim();
        String term2 = terms[1].trim();
        int numb1 = convert(term1);
        int numb2 = convert(term2);
        int result = calculated(numb1, numb2, operation);
        if (isRoman(term1) && isRoman(term2)) {
            if (operation == '-' && numb1 < numb2){
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            System.out.println("---Результат для римских цифр----");
            String resultRoman = convertNumToRoman(result);
            System.out.println(term1 + " " + operation + " " + term2 + " = " + resultRoman);
        } else  {
            if (isRoman(term1) || isRoman(term2)) {
                throw new Exception("Используются одновременно разные системы счисления");
            }
            System.out.println("--Результат для арабских цифр----");
            System.out.println(numb1 + " " + operation + " " + numb2 + " = " + result);
        }
    }

    private static int convert(String number) {
        return isRoman(number) ? romanToNumber(number) : Integer.parseInt(number);
    }

    private static boolean isRoman(String number) {
        return Arrays.asList(roman).contains(number);
    }
    private static String convertNumToRoman (int numArabian) {
        return roman[numArabian];
    }


    private static int romanToNumber (String roman) {
        try {
            switch (roman) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
                default:
                    return 0;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат данных");
        }
    }

    public static int calculated (int num1, int num2, char op) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
    }
}
