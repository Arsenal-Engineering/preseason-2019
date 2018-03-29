package frc.team6223.arsenalFramework;


import frc.team6223.arsenalFramework.software.units.Time;
import frc.team6223.arsenalFramework.software.units.TimeUnits;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class TimeTest {

    private Time time;

    @BeforeEach
    public void init() {
        time = new Time(1.0, TimeUnits.MILLISECONDS);
    }

    @Test
    @DisplayName("time should rescale properly")
    public void timeRescale() {
        assertThat(time.numericValue()).isEqualTo(1.0);
        assertThat(time.numericValue(TimeUnits.MICROSECONDS)).isEqualTo(1000.0);
        assertThat(time.numericValue(TimeUnits.SECONDS)).isEqualTo(.001);
    }

    @Test
    @DisplayName("time should be properly formmatted via toString()")
    public void toStringTest() {
        assertThat(time.toString()).isEqualTo("1.0 ms");
    }

}
