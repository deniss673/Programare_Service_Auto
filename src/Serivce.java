import java.util.*;

import model.*;

public class Serivce {

    List<persoana> users = new ArrayList<persoana>();
    Map<client,appoiment> hasmap = new HashMap<client,appoiment>();
    persoana current_user = null;
    Map<client,List<invoice>> clientinvoiceMap= new HashMap<client,List<invoice>>();

    public void set_user(String username, String password) {
        for(persoana usr : users) {
            if(Objects.equals(username,usr.getUser_name()) && Objects.equals(password,usr.getPassword()))
                current_user=usr;
        }
    }


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
            System.out.println("2. Modify an appoiment");
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
                    role=persoana.log_in(users,current_user);
                    break;
                case 2:
                    role=2;
                    current_user=persoana.create_account();
                    users.add(current_user);
                    break;
            }
        }
        else if (role==2){
            switch (command){
                case 1:
                    client client_= (client) current_user;
                    appoiment app=appoiment.create_appoiment(client_);
                    if(app!=null)
                        hasmap.put(client_,app);
                    break;
                case 2:
                    appoiment app_=hasmap.get(current_user);
                    app_.modify((client) current_user);
                    break;
                case 3:
                    hasmap.remove(current_user);
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
                        ((client) current_user).setMembership(true);
                    }
                    break;
                case 6:
                    car temp=car.create_car();
                    ((client) current_user).add_car(temp);
                    break;
                case 7:
                    ((client) current_user).delete_car();
                    break;
                case 8:
                    ((client) current_user).show_cars();
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
