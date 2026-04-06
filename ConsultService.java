import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class ConsultService {
    
   
    static boolean canUserViewConsults(User user) {
        // Huisarts can view all consultations
        if (user instanceof Huisarts) {
            return true;
        }
        // Fysio and Apotheker can view non-sensitive
        if (user instanceof Fysio || user instanceof Apotheker) {
            return true;
        }
        // Tandarts and others cannot view consultations
        return false;
    }
    
   
    static boolean canUserViewSensitive(User user) {
        return user instanceof Huisarts;
    }
    
   
    static boolean canUserCreateConsults(User user) {
        return canUserViewConsults(user);
    }
    
   
    static List<Consult> getFilteredConsults(User user, List<Consult> allConsults) {
        List<Consult> filtered = new ArrayList<>();
        
        // If user cannot view any consultations, return empty list
        if (!canUserViewConsults(user)) {
            System.out.println("[ACCESS DENIED] You don't have permission to view consultations.");
            return filtered;
        }
        
        // If user is Huisarts, return all consultations (sensitive + non-sensitive)
        if (canUserViewSensitive(user)) {
            filtered.addAll(allConsults);
        } else {
            // For Fysio and Apotheker: only add non-sensitive consultations
            for (Consult consult : allConsults) {
                if (!consult.isSensitive()) {
                    filtered.add(consult);
                }
            }
        }
        
        return filtered;
    }
    
    static void createConsultWithInput(Scanner scanner, Patient patient, User user) {
        // Permission check: can this user create consultations?
        if (!canUserCreateConsults(user)) {
            System.out.println("[ACCESS DENIED] You don't have permission to create consultations.");
            return;
        }
        
        
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        System.out.println("Enter consult date (dd/MM/yyyy) or leave empty for today:");
        String dateInput = scanner.nextLine().trim();
        LocalDate consultDate = LocalDate.now();
        
        if (!dateInput.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                consultDate = LocalDate.parse(dateInput, formatter);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Invalid date format, using today.");
            }
        }

        System.out.println("Enter consult note:");
        String note = scanner.nextLine().trim();
        
        if (note.isEmpty()) {
            System.out.println("No note entered, cancelled.");
            return;
        }

        // Ask for sensitivity classification
        System.out.println("Is this consultation SENSITIVE? (y/n)");
        System.out.println("(Sensitive = contains private/confidential medical info)");
        String sensitiveInput = scanner.nextLine().trim().toLowerCase();
        boolean isSensitive = sensitiveInput.equals("y") || sensitiveInput.equals("yes");
        
        // Additional check: some roles cannot create sensitive consultations
        if (isSensitive && !canUserViewSensitive(user)) {
            System.out.println("[ACCESS DENIED] Your role can only create NON-SENSITIVE consultations.");
            System.out.println("This consultation would be marked as sensitive. Creation cancelled.");
            return;
        }

        patient.addConsult(consultDate, note, isSensitive);
        System.out.println("Consult note added successfully!");
    }
}
