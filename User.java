import java.util.Scanner;
public class User {
    protected String userName;
    protected int userID;

    public User(int id, String name) {
        this.userID = id;
        this.userName = name;
    }

// getters
    public String getUserName() {
        return userName;
    }

    public int getUserID() {
        return userID;
    }

// setters
    public void setUsername(String userName){
        this.userName = userName;
    }

    public void Dashboard (){
        System.out.println("Loading Dashboard");
    }

    public boolean canViewMedication() {
        return false;
    }

    public boolean canEditMedication() {
        return false;
    }

    public boolean canAddMedication() {
        return false;
    }

    public boolean canViewPainRelieversOnly() {
        return false;
    }

    public boolean canViewPatientData() {
        return false;
    }

    //methode voor het selecteren van een andere user, deze wordt opgeroepen in het menu van de administratie class, en geeft de mogelijkheid om te switchen tussen de verschillende users die in de account class staan.
    public void selectOtherUser(Scanner scanner, Account account, Administration admin) {
        System.out.println("\n--- Select another user ---");
        System.out.println("Available users:");
        for (User u : account.allUsers) {
            System.out.format("[%d] %s (%s)\n", u.getUserID(), u.getUserName(), u.getClass().getSimpleName());
        }
        System.out.println("Enter the user ID to switch to (or 0 to cancel):");
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            if (id == 0) {
                System.out.println("Cancelled.");
                return;
            }
            for (User u : account.allUsers) {
                if (u.getUserID() == id) {
                    admin.currentUser = u;
                    System.out.println("Switched to user: " + u.getUserName() + " (" + u.getClass().getSimpleName() + ")");
                    return;
                }
            }
            System.out.println("Invalid user ID.");
        } else {
            System.out.println("Invalid input.");
            scanner.next(); // consume invalid input
        }
    }
        }
    
//classes rollen met verschillende permissies, deze worden gebruikt om te bepalen welke opties er in het menu worden getoond voor de verschillende users, en welke acties ze kunnen uitvoeren op de patient data.
class Huisarts extends User{
    public Huisarts (int id, String name){
        super(id, name);
    }
        @Override
         public void Dashboard() {
        System.out.println("DASHBOARD HUISARTS: " + getUserName());
        System.out.println("\n- Your Patient options");
    }

    @Override
    public boolean canViewMedication() {
        return true;
    }

    @Override
    public boolean canEditMedication() {
        return false;
    }

    @Override
    public boolean canAddMedication() {
        return false;
    }

    @Override
    public boolean canViewPatientData() {
        return true;
    }
}

class Fysio extends User{
    public Fysio (int id, String name){
        super(id, name);
    }
        @Override
        public void Dashboard(){
            System.out.println("DASHBOARD FYSIO: " + getUserName());
            System.out.println("\n- Your Patient options");
        }
    
    @Override
    public boolean canViewMedication() {
        return true;
    }

    @Override
    public boolean canEditMedication() {
        return false;
    }

    @Override
    public boolean canAddMedication() {
        return false;
    }

    @Override
    public boolean canViewPainRelieversOnly() {
        return true;
    }

    @Override
    public boolean canViewPatientData() {
        return true;
    }
}

class Apotheker extends User{
    public Apotheker (int id, String name){
        super(id, name);
    }
        @Override
        public void Dashboard(){
            System.out.println("DASHBOARD APOTHEKER: " + getUserName());
            System.out.println("\n- Your Patient options");
        }

    @Override
    public boolean canViewMedication() {
        return true;
    }

    @Override
    public boolean canEditMedication() {
        return true;
    }

    @Override
    public boolean canAddMedication() {
        return true;   
    }

    @Override
    public boolean canViewPatientData() {
        return true;
    }
}

class Tandarts extends User{
    public Tandarts (int id, String name){
        super(id, name);
    }
        @Override
        public void Dashboard(){
            System.out.println("DASHBOARD TANDARTS: " + getUserName());
            System.out.println("\n- Your Patient options");
        }

        @Override
        public boolean canViewPatientData() {
            return true;
        }
}





