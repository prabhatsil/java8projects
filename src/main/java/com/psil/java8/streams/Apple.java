package com.psil.java8.streams;

public class Apple {

    public String color;
    public int weight;
    public String country;
    public String type;

    public Apple(){}

    public Apple(String color, int weight){
        this.color = color;
        this.weight = weight;
    }

    public Apple(String color, int weight, String country, String type){
        this.color = color;
        this.weight = weight;
        this.country = country;
        this.type = type;

    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }



    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


}
