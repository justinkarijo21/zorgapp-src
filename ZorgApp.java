import java.util.Scanner;

public class ZorgApp {
    public static void main(String[] args) {
        Scanner loginScanner = new Scanner(System.in);

        System.out.println("--- WELCOME IN THE ZORGAPP ---");
        System.out.println("Select your function:");
        System.out.println("1. Huisarts\n2. Apotheker\n3. Fysio\n4. Tandarts");
        System.out.print("Make a choice: ");

        int choice = loginScanner.nextInt();
        User loggedinUser = null;

        switch (choice){

            case 1:
                loggedinUser = new Huisarts(1001, "Dr Supusepa");
                break;

            case 2:
                loggedinUser = new Apotheker(2001, "Mr. Paul");
                break;
            
            case 3: 
                loggedinUser = new Fysio(3001, "Mr. Patta");
                break;

            case 4:
                loggedinUser = new Tandarts(4001, "Mvr. Spa");
                break;

            default:
                System.err.println("Invalid choice, Closing program.");
                return;
        }

        Administration administration = new Administration(loggedinUser);
        administration.menu();
    
    }
}


