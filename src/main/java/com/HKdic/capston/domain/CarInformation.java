package com.HKdic.capston.domain;

public class CarInformation {

    private String name; // 이름
    private String price;
    private String trim; // 외장
    private String fuel; // 연료
    private String displacement; // 배기량
    private String mileage; // 연비
    private String limit; // 정원

    public CarInformation(String name, String price, String trim, String fuel, String displacement, String mileage, String limit) {
        this.name = name;
        this.price = price;
        this.trim = trim;
        this.fuel = fuel;
        this.displacement = displacement;
        this.mileage = mileage;
        this.limit = limit;
    }

    public CarInformation() {}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
    public String getTrim() {
        return trim;
    }
    public void setTrim(String trim) {
        this.trim = trim;
    }
    public String getFuel() {
        return fuel;
    }
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
    public String getDisplacement() {
        return displacement;
    }
    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }
    public String getMileage() {
        return mileage;
    }
    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
    public String getLimit() {
        return limit;
    }
    public void setLimit(String limit) {
        this.limit = limit;
    }

}
