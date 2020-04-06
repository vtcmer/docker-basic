package com.ztt.opendata.extractor.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Photo implements Serializable {

    String name;
    BigDecimal latitude;
    BigDecimal longitude;
    String url;
    Date date;

    public Photo(){}



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
