package atividades.atividade3.teste;

import atividades.atividade3.main.Date;
import atividades.atividade3.main.Date;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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




}
