package model;

public class invoice {
    private car repaired_car;
    private int total_price;

    public invoice(car repaired_car, int total_price) {
        this.repaired_car = repaired_car;
        this.total_price = total_price;
    }
    public car getRepaired_car() {
        return repaired_car;
    }
    public void setRepaired_car(car repaired_car) {
        this.repaired_car = repaired_car;
    }
    public int getTotal_price() {
        return total_price;
    }
    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public void show_invoice(){
        System.out.println("Car:");
        this.repaired_car.show_car();
        System.out.println("Total price:" + this.total_price);
    }
}
