package com.mumo.drone.enumeration;

public enum State {
    IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING;

    private static final State[] vals = values();
}
