package trabalho.trabalho1.test;

import org.junit.jupiter.api.Test;
import trabalho.trabalho1.main.Trabalho;

import static org.assertj.core.api.Assertions.assertThat;

public class TrabalhoTest {


    // T1: shift == 0
    @Test
    void shiftIsZero(){
        assertThat(Trabalho.rotate("abc", 0)).isEqualTo("abc");
    }

    // T2: str == null
    @Test
    void strIsNull(){
        assertThat(Trabalho.rotate(null, 1)).isEqualTo(null);
    }

    // T3: str is empty ""
    @Test
    void strIsEmpty(){

        assertThat(Trabalho.rotate("", 2)).isEqualTo("");
    }

}
