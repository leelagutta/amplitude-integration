package com.amplitude.domain;

/**
 * Created by Leela on 1/22/17.
 */
public class Device {

    private String advertisingId;
    private String token;
    private String model;
    private String type;
    private String id;
    private String adTrackingEnabled;
    private String manufacturer;

    public String getAdvertisingId() {
        return advertisingId;
    }

    public void setAdvertisingId(String advertisingId) {
        this.advertisingId = advertisingId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdTrackingEnabled() {
        return adTrackingEnabled;
    }

    public void setAdTrackingEnabled(String adTrackingEnabled) {
        this.adTrackingEnabled = adTrackingEnabled;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
