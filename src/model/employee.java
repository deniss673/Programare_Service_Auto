package model;

public class employee extends user {
    private String position;
    private int salary;


    public employee(user user){
        super(user.getFirst_name(), user.getLast_name(), user.getPhone_number(), user.getUser_name(), user.getPassword());
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
