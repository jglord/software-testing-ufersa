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

    // T3: str is empty, length = 0 -> ""
    @Test
    void strIsEmpty(){
        assertThat(Trabalho.rotate("", 2)).isEqualTo("");
    }
    
    // T4: Right rotate / "abc", 2 ->  "bca"
    @Test
    void rightRotate(){
        assertThat(Trabalho.rotate("abc", 2)).isEqualTo("bca");
    }

    // T5: Left  rotate / "abc", -2 ->  "cab"
    @Test
    void leftRotate(){
        assertThat(Trabalho.rotate("abc", -2)).isEqualTo("cab");
    }

    // T6: shift % str.length = 0 / shift = 6, str.length = 3
    @Test
    void shiftModLen(){
        assertThat(Trabalho.rotate("abc", 6)).isEqualTo("abc");
    }

    // T7: Shift > strlen && shift % strlen != zero
    @Test
    void shiftBiggerStrlen(){
        assertThat(Trabalho.rotate("abc", 5)).isEqualTo("bca");
    }
}
