import java.util.Scanner;

public class ZorgApp {
    public static void main(String[] args) {
        try (Scanner loginScanner = new Scanner(System.in)) { 
            Account account = new Account();
            User loggedinUser = account.login(loginScanner); // LOGIN VOOR DE USER, KIEZEN UIT DE 4 USERS DIE IN ACCOUNT STAAN.
            if (loggedinUser == null) {
                return;
            }

            Administration administration = new Administration(loggedinUser, account);
            administration.menu();
        }
    }
}


