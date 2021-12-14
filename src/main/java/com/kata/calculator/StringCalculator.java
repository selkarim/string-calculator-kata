package com.kata.calculator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class StringCalculator {

    static int add(String numbers) {
        if (numbers.isBlank()) return 0;
        traitNegativeNumbersInput(stringInputToListOfNumber(numbers));
        return sum(stringInputToListOfNumber(numbers));
    }

    private static void traitNegativeNumbersInput(List<Integer> numbers) {
        List<Integer> negativeNumbers = numbers.stream().filter(num -> num < 0).collect(Collectors.toList());
        ;
        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed: " + negativeNumbers.toString());
        }
    }

    private static List<Integer> stringInputToListOfNumber(String input) {
        Pattern pattern = Pattern.compile("([-\\d]+)");
        Matcher matcher = pattern.matcher(input);
        List<Integer> listOfNumber = new ArrayList<>();
        while (matcher.find()) {
            listOfNumber.add(Integer.parseInt(matcher.group()));
        }
        return listOfNumber;
    }

    private static int sum(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }
}
