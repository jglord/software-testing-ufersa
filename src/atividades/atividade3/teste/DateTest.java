package atividades.atividade3.teste;

import atividades.atividade2.main.Fibonacci;
import atividades.atividade3.main.Date;
import atividades.atividade3.main.Date;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTest {

    @Test
    void exampleInvalidArgumentTestCase() {
        assertThatThrownBy(() -> new Date(13,31,2022)) //invalid day
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void exampleTestCase() {
        Date date = new Date(2, 29, 2028);
        assertThat(date.toString()).isEqualTo("2/29/2028");
    }

    @Test
    void invalidDay(){
        int day = 50; // Invalid day
        assertThatThrownBy(() -> {
            new Date(10, day, 2020 );
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void invalidMonth(){
        int month = 50; // Invalid month
        assertThatThrownBy(() -> {
            new Date(month, 10, 2020 );
        }).isInstanceOf(IllegalArgumentException.class);
    }

    // Testar dia 31  para mes de 30 dias
    @Test
    void invalidDayToMonth(){
        int month = 4;
        int day = 31; // Invalid day to month 4
        assertThrows(
                IllegalArgumentException.class,
                () -> { new Date(month, day, 2020 ); },
                "day (" + day + ") out-of-range for the specified month and year"
        );
    }

    // Testar se o ano é bissexto, o mês é 2 e o dia = 29
    @Test
    void leapYearDayEqual29(){
        int month = 2;
        int day = 29;
        int year = 2024;
        Date date = new Date(month, day, year);
        assertThat(date.toString()).isEqualTo("2/29/2024");
    }


    // Testar se o ano é bissexto, o mês é 2 e o dia > 29
    @Test
    void leapYearDayMore29(){
        int month = 2;
        int day = 30;
        int year = 2024;

        assertThatThrownBy(() -> {
            new Date(month, day, year);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    // Testar se o ano não é bissexto e dia > 28 para mes 2
    @Test
    void month2DayMore28(){
        int month = 2;
        int day = 29;
        int year = 2023;

        assertThatThrownBy(() -> {
            new Date(month, day, year);
        }).isInstanceOf(IllegalArgumentException.class);
    }


    // Testar se o mes é 2
    // Testar se o mês é 2 e o dia > 29



}
