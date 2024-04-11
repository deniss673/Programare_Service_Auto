package model;

import java.util.Date;
import java.util.Scanner;

public class appoiment {
    private Date date;
    private car car_toRepair;
    private int expected_days;
    private String problems;


    public appoiment(Date date, car car_toRepair, String problems) {
        this.date = date;
        this.car_toRepair = car_toRepair;
        this.problems = problems;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public car getCar() {
        return car_toRepair;
    }
    public void setCar(car car) {
        this.car_toRepair = car;
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

    public static appoiment create_appoiment(client client_){
        if (client_.getClient_cars().size()==0){
            System.out.println("No cars found");
            return null;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Select your car:");
        client_.show_cars();
        int choice= sc.nextInt();

        car car_;

        while(choice >client_.getClient_cars().size() || choice <1){
            System.out.println("Select a valid car!");
            choice= sc.nextInt();
        }

        car_=client_.getClient_cars().get(choice-1);
        Date date=new Date();
        System.out.println("Sumbit the car problems: ");
        String problems=sc.nextLine();

        return new appoiment(date,car_,problems);

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

                this.car_toRepair=client_.getClient_cars().get(car_choice-1);
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
