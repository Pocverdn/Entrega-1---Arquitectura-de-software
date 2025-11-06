package com.entrega1.trabajo.DTOs;

public class ApiDTO {

    private Location location;
    private Current current;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public static class Location {
        private String name;
        private String region;
        private String localtime;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getRegion() {
            return region;
        }
        public void setRegion(String region) {
            this.region = region;
        }
        public String getLocaltime() {
            return localtime;
        }
        public void setLocaltime(String localtime) {
            this.localtime = localtime;
        }
    }

    public static class Current {
        private double temp_c;
        private double temp_f;
        private int humidity;

        public double getTemp_c() {
            return temp_c;
        }
        public void setTemp_c(double temp_c) {
            this.temp_c = temp_c;
        }
        public double getTemp_f() {
            return temp_f;
        }
        public void setTemp_f(double temp_f) {
            this.temp_f = temp_f;
        }
        public int getHumidity() {
            return humidity;
        }
        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }
    }

}
