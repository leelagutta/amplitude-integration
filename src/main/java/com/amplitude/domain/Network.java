package com.amplitude.domain;

/**
 * Created by Leela on 1/22/17.
 */
public class Network {
    private boolean wifi;
    private String carrier;
    private boolean cellular;
    private boolean bluetooth;

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public boolean isCellular() {
        return cellular;
    }

    public void setCellular(boolean cellular) {
        this.cellular = cellular;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }
}
