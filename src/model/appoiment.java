package model;

import dao.AppoimentDao;

import java.util.Date;
import java.util.Scanner;

public class appoiment {
    private Date date;
    private car carToRepair;
    private int expected_days;
    private String problems;



    public appoiment(Date date, car car_toRepair, String problems) {
        this.date = date;
        this.carToRepair = car_toRepair;
        this.problems = problems;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public car getCar() {
        return carToRepair;
    }
    public void setCar(car car) {
        this.carToRepair = car;
    }
    public int getExpected_days() {
        return expected_days;
    }
    public void setExpected_days(int expected_days) {
        this.expected_days = expected_days;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }



    public void modify(client client_){
        System.out.println("1.Select another car");
        System.out.println("2.Change problem");
        System.out.println("3.Exit");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                System.out.println("Select your car:");
                client_.show_cars();
                int car_choice= sc.nextInt();

                while(choice >client_.getClient_cars().size() || choice <1){
                    System.out.println("Select a valid car!");
                    car_choice= sc.nextInt();
                }

                this.carToRepair=client_.getClient_cars().get(car_choice-1);
                break;
            case 2:
                System.out.println("Sumbit the car problems: ");
                String problems=sc.nextLine();
                this.problems=problems;
                break;
            case 3:
                break;
        }
    }

}
