package ru.otus.tests;

import ru.otus.junit.After;
import ru.otus.junit.Before;
import ru.otus.junit.Test;
import ru.otus.tests.entities.Calc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class CalcTest {

    private Calc calc;

    @Before
    public void setUp() {
        calc = new Calc(10, 2);
    }

    @Test
    public void additionTest() {
        assertThat(calc.add())
                .isEqualTo(121);
    }

    @Test
    public void subtractionTest() {
        assertThat(calc.sub())
                .isEqualTo(8);
    }

    @Test
    public void multiplicationTest() {
        assertThat(calc.mul())
                .isEqualTo(20);
    }

    @Test
    public void divisionTest() {
        assertThat(calc.div())
                .isEqualTo(55);
    }

    @Test
    public void divisionByZeroTest() {
        Throwable thrown = catchThrowable(() -> calc.setNum2(0).div());
        assertThat(thrown)
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("/ by zero");
    }

    @After
    public void clean() {
        calc.clean();
    }
}
