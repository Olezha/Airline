package ua.olezha.airline.model.aircraft;

public enum AircraftType {
    COMMUTERLINER, HELICOPTER, WIDE_BODY_AIRLINER;

    static {
        COMMUTERLINER.clazz = Commuterliner.class;
        HELICOPTER.clazz = Helicopter.class;
        WIDE_BODY_AIRLINER.clazz = WideBodyAirliner.class;
    }

    private Class clazz;

    public Class getAircraftClass() {
        return clazz;
    }

    public String toString() {
        return clazz.getSimpleName();
    }
}
