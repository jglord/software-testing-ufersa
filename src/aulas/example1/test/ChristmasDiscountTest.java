package aulas.example1.test;

import aulas.example1.main.ChristmasDiscount;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChristmasDiscountTest {
    private final Clock clock = mock(Clock.class);
    private final ChristmasDiscount cd = new ChristmasDiscount(clock);
    @Test
    public void christmas() {
        LocalDate christmas = LocalDate.of(2015, Month.DECEMBER, 25);
       // when(clock.now()).thenReturn(christmas);

        double finalValue = cd.applyDiscount(100);
        assertThat(finalValue).isCloseTo(85, offset(0.001));
    }

    @Test
    public void notChristmas() {
        LocalDate notChristmas = LocalDate.of(2015, Month.DECEMBER, 26);
       // when(clock.now()).thenReturn(notChristmas);

        double finalValue = cd.applyDiscount(100);
        assertThat(finalValue).isCloseTo(85, offset(0.001));
    }

}
