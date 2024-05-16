package model;

import java.sql.SQLException;
import java.util.Scanner;

import dao.CarDao;

public class car {;
    private String manufacturer;
    private String plate_number;
    private int year;
    private String engine;

    private CarDao carDao;

    public car() {

    }

    public car(String manufacturer,String plate_number, int year, String engine) {
        this.manufacturer = manufacturer;
        this.plate_number = plate_number;
        this.year = year;
        this.engine = engine;
    }

    public String getPlate_number() {
        return plate_number;
    }
    public int getYear() {
        return year;
    }
    public String getEngine() {
        return engine;
    }
    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public car create_car() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the manufacturer: ");
        String manufacturer = sc.nextLine();
        System.out.println("Enter the plate number : ");
        String plate_number = sc.nextLine();
        System.out.println("Enter the year : ");
        int year = sc.nextInt();
        System.out.println("Select the engine : ");
        System.out.println("1.Gas");
        System.out.println("2.Diesel");
        System.out.println("3.Electric");

        int choice = sc.nextInt();
        String engine="Not speciefied";

        switch (choice){
            case 1:
                engine="Gas";
                break;
            case 2:
                engine="Diesel";
                break;
            case 3:
                engine="Electric";
                break;
        }
        car car_= new car(manufacturer,plate_number,year,engine);
        return car_;
    }

    public void show_car(){
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Plate number: " + plate_number);
        System.out.println("Year: " + year);
        System.out.println("Engine: "+ engine);
    }



}
