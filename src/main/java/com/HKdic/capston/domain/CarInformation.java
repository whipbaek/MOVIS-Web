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

    public String makeTTS() {
        String intro = "찾으신 정보는 다음과 같습니다 ";
        return intro +", " + name + ", " + splitPrice() + ", " + trim + ", " + fuel + ", "  + splitDisplacement() + ", " + splitMileage()+ ", " + limit;
    }

    private String splitPrice() {
        String[] splits = price.split("~");
        return splits[0] + "만원부터 " + splits[1];
    }

    private String splitDisplacement() {
        String[] splits = displacement.split("~");
        for (int i = 0; i < splits[1].length(); i++) {
            if(splits[1].charAt(i) == 'c'){
                splits[1] = splits[1].substring(0, i) + "씨씨";
                break;
            }
        }

        return splits[0] + "에서 " + splits[1];
    }

    private String splitMileage() {
        String[] splits = mileage.split("~");
        for (int i = 0; i < splits[1].length(); i++) {
            if(splits[1].charAt(i) == 'k'){
                splits[1] = splits[1].substring(0, i) + "킬로미터퍼리터";
                break;
            }
        }

        return splits[0] + "에서" + splits[1];
    }

}
