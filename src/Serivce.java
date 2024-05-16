import java.sql.SQLException;
import java.util.*;

import dao.AppoimentDao;
import dao.ClientDao;
import dao.UserDao;
import daoServices.AppoimentServiceDao;
import daoServices.CarServiceDao;
import daoServices.ClientServiceDao;
import daoServices.UserServiceDao;
import model.*;

public class Serivce {

    user current_user = null;
    Map<client,List<invoice>> clientinvoiceMap= new HashMap<client,List<invoice>>();




    public int service_options(int role)
    {
        Scanner scanner=new Scanner(System.in);

        if (role==0){
            System.out.println("You need to be logged in to use this service");
            System.out.println("1. Log in");
            System.out.println("2. Create an account");
            System.out.println("3. Exit");
        }
        else if (role==2){
            System.out.println("Enter service number");
            System.out.println("1. Create an appoiment");
            System.out.println("2. Show all appoiments");
            System.out.println("3. Delete an appoiment");
            System.out.println("4. Latest payments");
            System.out.println("5. Purchase membership");
            System.out.println("6. Add a car in the system");
            System.out.println("7. Delete a car from the system");
            System.out.println("8. See your cars");
            System.out.println("9. Log out");
            System.out.println("0. Exit");
        }

        return scanner.nextInt();
    }

    public int command(int command, int role){
        if (role==0){
            switch (command){
                case 1:
                    try{
                        UserServiceDao userDao = new UserServiceDao();
                        current_user= userDao.login();
                        role=userDao.getRole(current_user.getUser_name());

                    }
                    catch(SQLException e){
                        System.out.println(e);
                    }
                    break;
                case 2:
                    role=2;
                    try{
                        UserServiceDao userDao = new UserServiceDao();
                        current_user= userDao.register();
                    }
                    catch(SQLException e){
                        System.out.println(e);
                    }
                    break;
            }
        }
        else if (role==2){
            switch (command){
                case 1:
                    try{
                        AppoimentServiceDao app= new AppoimentServiceDao();

                        app.createAppoiment(current_user.getUser_name());

                    }
                    catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try{
                        AppoimentServiceDao app= new AppoimentServiceDao();
                        app.showAppoiment(current_user.getUser_name());
                    }
                    catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:

                    try{
                        AppoimentServiceDao app= new AppoimentServiceDao();
                        app.deleteAppoiment(current_user.getUser_name());
                    }
                    catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    List<invoice> invoices=clientinvoiceMap.get(current_user);
                    invoices.sort(Comparator.comparing(invoice::getTotal_price));
                    for( invoice inv : invoices){
                        inv.show_invoice();
                    }
                    break;

                case 5:
                    System.out.println("Are you sure you want to buy a membership?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    Scanner scanner=new Scanner(System.in);
                    int choice=scanner.nextInt();
                    if (choice==1){
                        try{
                            ClientServiceDao cl= new ClientServiceDao();
                            cl.setMembership(current_user.getUser_name());
                        }
                        catch (SQLException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 6:
                    try{
                        CarServiceDao car= new CarServiceDao();
                        car.createCar(current_user.getUser_name());
                    }
                    catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    try{
                        CarServiceDao car= new CarServiceDao();
                        car.deleteCar(current_user.getUser_name());
                    }
                    catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
                case 8:
                    try{
                        ClientServiceDao cl = new ClientServiceDao();
                        cl.show(current_user.getUser_name());
                    }
                    catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    return 0;
                case 0:
                    return -1;

            }
        }
        return role;
    }
}
