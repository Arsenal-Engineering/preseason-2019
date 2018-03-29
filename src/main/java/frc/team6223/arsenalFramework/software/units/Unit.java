package frc.team6223.arsenalFramework.software.units;


public abstract class Unit<T extends ScaleUnit> {

    public abstract double numericValue();
    public abstract double numericValue(T rep);
    public abstract Unit<T> unit();
    public abstract Unit<T> rescale(T rep);

    public abstract T getScale();
    public abstract T getDefaultScale();

    public abstract Unit<T> plus(Unit<T> inc);
    public abstract Unit<T> minus(Unit<T> dec);
    public abstract Unit<T> times(Unit<T> mult);
    public abstract Unit<T> div(Unit<T> div);

    public boolean lt(Unit<T> other) {
        return this.numericValue() < other.numericValue();
    }

    public boolean gt(Unit<T> other) {
        return this.numericValue() > other.numericValue();
    }

    public boolean lte(Unit<T> other) {
        return this.numericValue() <= other.numericValue();
    }

    public boolean gte(Unit<T> other) {
        return this.numericValue() >= other.numericValue();
    }

    @Override
    public int hashCode() {
        int result = this.getScale().hashCode();
        result = 31 * result + this.getDefaultScale().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.numericValue(getScale()) + " " + this.getScale().getAbbreviation();
    }
}
