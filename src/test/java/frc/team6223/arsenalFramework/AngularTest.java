package frc.team6223.arsenalFramework;


import frc.team6223.arsenalFramework.software.units.Angle;
import frc.team6223.arsenalFramework.software.units.AngleUnits;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


public class AngularTest {

    private Angle angleDeg;
    private Angle angleRad;

    @BeforeEach
    public void init() {
        angleDeg = new Angle(60.0, AngleUnits.DEGREES);
        angleRad = new Angle(1.0, AngleUnits.RADIANS);
    }

    @Test
    @DisplayName("angle in degree formats correctly via toString()")
    public void angleDegFormattedProperly() {
        assertThat(angleDeg.toString()).isEqualTo("60.0 deg");
    }

    @Test
    @DisplayName("angle in radians formats correctly via toString()")
    public void angleRadFormattedProperly() {
        assertThat(angleRad.toString()).isEqualTo("1.0 rad");
    }

    @Test
    @DisplayName("angle in degrees correctly transforms to radians")
    public void angleDegToRad() {
        assertThat(angleDeg.numericValue(AngleUnits.RADIANS)).isEqualTo(Math.PI/3);
    }

    @Test
    @DisplayName("angle in radians correctly transforms to degrees")
    public void angleRadToDeg() {
        assertThat(angleRad.numericValue(AngleUnits.DEGREES)).isEqualTo(57.2958, Offset.offset(0.001));
    }

}
