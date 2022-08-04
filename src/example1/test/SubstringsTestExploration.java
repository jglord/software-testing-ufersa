package example1.test;

import example1.main.Substrings;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SubstringsTestExploration {
    
    @Test
    void simpleCase(){
        assertThat(Substrings.substringsBetween("abcd", "a", "d")).isEqualTo(new String[] {"bc"});
    }

    @Test
    void manySubstrings() {
        assertThat(Substrings.substringsBetween("abcdabcdab",
                "a", "d")).isEqualTo(new String[] { "bc", "bc" });
    }

    @Test
    void openAndCloseTagsThatAreLongerThan1Char() {
        assertThat(Substrings.substringsBetween("aabcddaabfddaab", "aa", "dd")).isEqualTo(new String[]
        {"bc", "bf"});
    }
}
