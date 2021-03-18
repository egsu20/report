package com.example.r2;

public class Car {
    String color;
    int speed;

    // 메소드 오버라이딩
    public Car(){
        this.color="";
        this.speed = 0;
    }
    public Car(int speed) {
        this.speed = speed;
    }
    public Car(String color) {
        this.color = color;
    }

    public Car(String color, int speed) {
        this.color = color;
        this.speed = speed;
    }

    // color와 speed의 getter와 setter
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    // 정보 얻기
    public String getInformation(){
        return "색상 : " + getColor() + ", 속도 : " + getSpeed();
    }

    // 속도 설정
    public void upSpeed(int value){
        if(speed + value >= 200)
            speed = 200;
        else
            speed = speed + value;
    }
    public void downSpeed(int value){
        if (speed-value <= 0 )
            speed = 0;
        else
            speed = speed - value;
    }
}
