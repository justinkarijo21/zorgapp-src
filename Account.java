import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account {

    private final List<User> allUsers;
    private User currentUser;

    public Account() {
        allUsers = new ArrayList<>();
        allUsers.add(new Huisarts(1001, "Dr Supusepa"));
        allUsers.add(new Apotheker(2001, "Mr. Paul"));
        allUsers.add(new Fysio(3001, "Mr. Patta"));
        allUsers.add(new Tandarts(4001, "Mvr. Spa"));
    }

    /**
     * Prompts the user to enter their user id and returns the selected User.
     * Returns null when the selection is invalid.
     */
    public User login(Scanner scanner) {
        System.out.println("--- WELCOME IN THE ZORGAPP ---");
        System.out.println("Available users:");
        for (User user : allUsers) {
            System.out.printf("%d - %s (%s)%n", user.getUserID(), user.getUserName(), user.getClass().getSimpleName());
        }

        System.out.print("Enter your user ID: ");
        if (!scanner.hasNextInt()) {
            System.err.println("Invalid input, closing program.");
            return null;
        }

        int enteredId = scanner.nextInt();
        for (User user : allUsers) {
            if (user.getUserID() == enteredId) {
                currentUser = user;
                return currentUser;
            }
        }

        System.err.println("No user found for ID " + enteredId + ". Closing program.");
        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
