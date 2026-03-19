import java.util.Scanner;

public class ZorgApp {
    public static void main(String[] args) {
        try (Scanner loginScanner = new Scanner(System.in)) {
            Account account = new Account();
            User loggedinUser = account.login(loginScanner);
            if (loggedinUser == null) {
                return;
            }

            Administration administration = new Administration(loggedinUser);
            administration.menu();
        }
    }
}


