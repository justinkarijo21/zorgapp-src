import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class ConsultInputHandler {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static Consult collectConsultFrom(Scanner scanner, User user) {
        skipPendingInput(scanner);

        LocalDate consultDate = readConsultDate(scanner);
        String note = readConsultNote(scanner);
        if (note.isEmpty()) {
            System.out.println("No note entered, cancelled.");
            return null;
        }

        boolean isSensitive = readConsultSensitivity(scanner);
        if (isSensitive && !user.canViewSensitiveConsultations()) {
            System.out.println("[ACCESS DENIED] Your role can only create NON-SENSITIVE consultations.");
            System.out.println("This consultation would be marked as sensitive. Creation cancelled.");
            return null;
        }

        return new Consult(consultDate, note, isSensitive);
    }

    private static void skipPendingInput(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

    private static LocalDate readConsultDate(Scanner scanner) {
        System.out.println("Enter consult date (dd/MM/yyyy) or leave empty for today:");
        String dateInput = scanner.nextLine().trim();

        if (dateInput.isEmpty()) {
            return LocalDate.now();
        }

        try {
            return LocalDate.parse(dateInput, DATE_FORMATTER);
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid date format, using today.");
            return LocalDate.now();
        }
    }

    private static String readConsultNote(Scanner scanner) {
        System.out.println("Enter consult note:");
        return scanner.nextLine().trim();
    }

    private static boolean readConsultSensitivity(Scanner scanner) {
        System.out.println("Is this consultation SENSITIVE? (y/n)");
        System.out.println("(Sensitive = contains private/confidential medical info)");
        String sensitiveInput = scanner.nextLine().trim().toLowerCase();
        return sensitiveInput.equals("y") || sensitiveInput.equals("yes");
    }
}
