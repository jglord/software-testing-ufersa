package atividades.atividade2.test;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import atividades.atividade2.main.Fibonacci;

public class FibonacciTest {
    @Test
    void isLessThan0(){
        assertThatThrownBy(() -> {
            Fibonacci.fib(-1);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("N < 0");
    }

    @Test
    void isBiggerOrEqualThan0(){
        assertThat(Fibonacci.fib(0)).isEqualTo(0);
    }

    @Test
    void fib1is1(){
        assertThat(Fibonacci.fib(1)).isEqualTo(1);
    }

    @Test
    void fib2is1(){
        assertThat(Fibonacci.fib(2)).isEqualTo(1);
    }

    @Test
    void isN(){
        assertThat(Fibonacci.fib(10)).isEqualTo(34);
    }
}
