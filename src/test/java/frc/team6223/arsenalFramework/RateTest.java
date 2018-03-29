package frc.team6223.arsenalFramework;

import frc.team6223.arsenalFramework.software.units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RateTest {

    private Rate<DistanceUnits, TimeUnits> rate;

    @BeforeEach
    public void init() {
        rate = new Velocity(new Distance(1.0, DistanceUnits.METERS), new Time(1.0, TimeUnits.SECONDS));
    }

    @Test
    @DisplayName("rate converts from m/s to m/ms correctly")
    public void downscaleRate() {
        assertThat(rate.rescaleBottomScalar(TimeUnits.MILLISECONDS)).isEqualTo(0.001);
    }

    @Test
    @DisplayName("rate converts from m/s to m/min correctly")
    public void upscaleRate() {
        assertThat(rate.rescaleBottomScalar(TimeUnits.MINUTE)).isEqualTo(60.0);
    }

    @Test
    @DisplayName("rate converts from non-unit rate to unit rate upon creation")
    public void unitRate() {
        assertThat(
          new Velocity(
            new Distance(1.0, DistanceUnits.METERS),
            new Time(5.0, TimeUnits.SECONDS)
          ).numericValue()).isEqualTo(0.20);
    }

    @Test
    @DisplayName("rate formats correctly via toString()")
    public void toStringTest() {
        assertThat(rate.toString()).isEqualTo("1.0 m/s");
    }

}
