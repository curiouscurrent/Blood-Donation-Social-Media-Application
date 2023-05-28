package com.anusha.coffee;



public class BloodRequest {
    private String bloodType;
    private String location;
    private String contact;
    private String quantity;
    private String userId;
    private double latitude;
    private  double longitude;

    public BloodRequest() {
    }

    public BloodRequest(String bloodType, String location, String contact, String quantity, String userId) {
        this.bloodType = bloodType;
        this.location = location;
        this.contact = contact;
        this.quantity = quantity;
        this.userId = userId;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}


