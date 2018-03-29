package frc.team6223.arsenalFramework.software.pid;


import java.util.Objects;


public class PIDFConstants {

    private final double kP;
    private final double kI;
    private final double kD;
    private final double kF;

    public PIDFConstants(double kP, double kI, double kD, double kF) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
    }

    public double getkP() {
        return kP;
    }

    public double getkI() {
        return kI;
    }

    public double getkD() {
        return kD;
    }

    public double getkF() {
        return kF;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        PIDFConstants that = (PIDFConstants) o;
        return Double.compare(that.getkP(), getkP()) == 0 && Double.compare(that.getkI(), getkI()) == 0 &&
          Double.compare(that.getkD(), getkD()) == 0 && Double.compare(that.getkF(), getkF()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getkP(), getkI(), getkD(), getkF());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PIDFConstants{");
        sb.append("kP=").append(kP);
        sb.append(", kI=").append(kI);
        sb.append(", kD=").append(kD);
        sb.append(", kF=").append(kF);
        sb.append('}');
        return sb.toString();
    }
}
