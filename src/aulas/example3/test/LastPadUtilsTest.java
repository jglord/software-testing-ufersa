package aulas.example3.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static aulas.example3.main.LeftPadUtils.leftPad;
import static org.junit.jupiter.params.provider.Arguments.of;


public class LastPadUtilsTest {
    @ParameterizedTest
    @MethodSource("generator")
    void test(String originalStr, int size, String padString, String expectedStr){
        assertThat(leftPad(originalStr, size, padString)).isEqualTo(expectedStr);
    }

    static Stream<Arguments> generator(){
        return Stream.of(
                of(null, 10, "-", null),
                of("", 5, "-", "-----"),
                of("abc", -1, "-", "abc"),
                of("abc", 5, null, "  abc"),
                of("abc", 5, "", "  abc"),
                of("abc", 5, "-", "--abc"),
                of("abc", 3, "-", "abc"),
                of("abc", 0, "-", "abc"),
                of("abc", 2, "-", "abc"),
                of("abc", 5, "--", "--abc"),
                of("abc", 5, "---", "--abc"),
                of("abc", 5, "-", "--abc")
        );
    }
}
