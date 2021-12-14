package com.kata.calculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import static org.junit.Assert.*;

public class StringCalculatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_be_O_when_string_numbers_is_blank() {
        int result = StringCalculator.add("");
        assertEquals(result, 0);

    }

    @Test
    public void should_sum_numbers_when_they_are_splatted_by_one_commas() {
        int result = StringCalculator.add("8,2");
        assertEquals(result, 10);
    }

    @Test
    public void should_sum_unknown_amount_of_numbers() {
        int result = StringCalculator.add("18,2,7,3,3,5,8");
        assertEquals(result, 46);
    }

    @Test
    public void should_sum_with_new_lines_between_numbers() {
        int result = StringCalculator.add("1\\n2,3");
        assertEquals(result, 6);
    }

    @Test
    public void should_sum_with_many_delimiter_between_numbers() {
        int result = StringCalculator.add("//[*][%]\\n1*2%3;::m,2");
        assertEquals(result, 8);
    }

    @Test
    public void should_one_negative_input_number_throw_exception() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("negatives not allowed: [-2]");
        StringCalculator.add("//[*][%]\\n:18;::m5!,-2");
    }

    @Test
    public void should_two_negative_input_number_throw_exception() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("negatives not allowed: [-5, -2]");
        StringCalculator.add("//[*][%]\\n:18;::m-5!,-2");
    }

    @Test
    public void should_more_than_three_negative_input_number_throw_exception() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("negatives not allowed: [-18, -5, -2]");
        StringCalculator.add("//[*][%]\\n:-18;::m-5!,-2, 9");
    }
}
