package com.HKdic.capston.domain;

import java.util.ArrayList;

public class CarInformation {

    private String name; // 이름
    private String price;
    private String trim; // 외장
    private String fuel; // 연료
    private String displacement; // 배기량
    private String mileage; // 연비
    private String limit; // 정원

    public CarInformation(ArrayList<String> infos){
        this.name = infos.get(0);
        this.price = infos.get(1);
        this.trim = infos.get(2);
        this.fuel = infos.get(3);
        this.displacement = infos.get(4);
        this.mileage = infos.get(5);
        this.limit = infos.get(6);
    }

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

    @Override
    public String toString(){
        return "\n" + name + "\n" + price + "\n" + trim + "\n" + fuel + "\n" + displacement + "\n" + mileage + "\n" + limit + "\n";
    }

}
