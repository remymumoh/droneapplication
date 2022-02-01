package com.mumo.drone.enumeration;

public enum State {
    IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING;

    private static final State[] vals = values();

    public State next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public State previous() {
        return vals[(this.ordinal() - 1) % vals.length];
    }
}
