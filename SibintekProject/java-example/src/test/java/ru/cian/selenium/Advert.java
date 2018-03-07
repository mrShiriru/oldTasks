package ru.cian.selenium;

/**
 * Created by k.grigorchuk on 07.03.2018.
 */
public class Advert {


    private String metro;
    private String price;
    private String countRooms;
    private String areaRooms;
    private String floors;


    public Advert(String metro , String price , String countRooms, String areaRooms, String floors){
        this.metro = metro;
        this.price = price;
        this.countRooms = countRooms;
        this.areaRooms = areaRooms;
        this.floors = floors;
    }


    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getCountRooms() {
        return countRooms;
    }

    public void setCountRooms(String countRooms) {
        this.countRooms = countRooms;
    }


    public String getAreaRooms() {
        return areaRooms;
    }

    public void setAreaRooms(String areaRooms) {
        this.areaRooms = areaRooms;
    }


    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }


}
