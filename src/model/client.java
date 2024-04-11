package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class client extends persoana{
    private String payment_method;
    private boolean membership;
    private List<car> client_car;

    public client(persoana Persoana){
        super(Persoana.getFirst_name(),Persoana.getLast_name(),Persoana.getPhone_number(),Persoana.getUser_name(),Persoana.getPassword());
        client_car = new ArrayList<>();
    }


    public String getPayment_method() {
        return payment_method;
    }

    public boolean isMembership() {
        return membership;
    }

    public void setMembership(boolean membership) {
        this.membership = membership;
    }
    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public List<car> getClient_cars() {
        return client_car;
    }

    public static void setClient_car(List<car> client_car) {
        client_car = client_car;
    }

    public void show_cars(){
        int count=1;
        for(car car_: client_car){
            System.out.println(count+".");
            car_.show_car();
            count++;
        }
    }

    public void add_car(car car_){
        client_car.add(car_);
    }

    public void delete_car(){
        show_cars();
        System.out.println("Select a car to delete: ");
        Scanner sc = new Scanner(System.in);
        int choice;
        choice = sc.nextInt();
        client_car.remove(choice-1);
    }



}
