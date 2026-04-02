import java.util.Scanner;

public class loginManager {
    
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
