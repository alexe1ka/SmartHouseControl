package com.example.alexe1ka.iotalexe1ka.model;

/**
 * Created by alexe1ka on 19.12.2016.
 */

public class ReplyToRequest {
    private String mTemperature;
    private String mHumidity;
    private String mId;
    private String mName;
    private String mHardware;
    private String mConnectedStatus;

    public String getTemperature() {
        return mTemperature;
    }

    public void setTemperature(String mTemperature) {
        this.mTemperature = mTemperature;
    }

    public String getHumidity() {
        return mHumidity;
    }

    public void setHumidity(String mHumidity) {
        this.mHumidity = mHumidity;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getHardware() {
        return mHardware;
    }

    public void setHardware(String mHardware) {
        this.mHardware = mHardware;
    }

    public String getConnectedStatus() {
        return mConnectedStatus;
    }

    public void setConnectedStatus(String mConnectedStatus) {
        this.mConnectedStatus = mConnectedStatus;
    }
}
