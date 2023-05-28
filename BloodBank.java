package com.anusha.coffee;

import java.util.List;

public class BloodBank {
    private String name;
    private String location;
    private List<String> availableBloodTypes;

    public BloodBank(String name, String location, List<String> availableBloodTypes) {
        this.name = name;
        this.location = location;
        this.availableBloodTypes = availableBloodTypes;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getAvailableBloodTypes() {
        return availableBloodTypes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAvailableBloodTypes(List<String> availableBloodTypes) {
        this.availableBloodTypes = availableBloodTypes;
    }

    @Override
    public String toString() {
        return "BloodBank{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", availableBloodTypes=" + availableBloodTypes +
                '}';
    }
}

