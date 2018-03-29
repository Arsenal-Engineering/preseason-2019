package frc.team6223.arsenalFramework;


import frc.team6223.arsenalFramework.software.units.Distance;
import frc.team6223.arsenalFramework.software.units.DistanceUnits;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;



public class DistanceTest {
    private Distance dist;

    @BeforeEach
    public void init() {
        dist = new Distance(1.0, DistanceUnits.METERS);
    }

    @Test
    @DisplayName("distance should scale correcly")
    public void distanceScaling() {
        assertThat(dist.numericValue()).isEqualTo(3.28084, Offset.offset(0.001));
        assertThat(dist.numericValue(DistanceUnits.INCHES)).isEqualTo(39.370, Offset.offset(0.001));
    }

    @Test
    @DisplayName("distance should input correctly as inches")
    public void distanceInches() {
        assertThat(new Distance(1.0, DistanceUnits.INCHES).numericValue()).isEqualTo(0.083, Offset.offset(0.001));
    }

    @Test
    @DisplayName("distance should input correctly as feet")
    public void distanceFeet() {
        assertThat(new Distance(1.0, DistanceUnits.FEET).numericValue()).isEqualTo(1.0);
    }

    @Test
    @DisplayName("distance should properly output via toString()")
    public void toStringTest() {
        assertThat(dist.toString()).isEqualTo("1.0 m");
    }



}
