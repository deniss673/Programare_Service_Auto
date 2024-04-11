package model;

public class employee extends persoana{
    private String position;
    private int salary;


    public employee(persoana Persoana){
        super(Persoana.getFirst_name(),Persoana.getLast_name(),Persoana.getPhone_number(), Persoana.getUser_name(), Persoana.getPassword());
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
}
