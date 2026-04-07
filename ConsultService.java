import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ConsultService {
    static boolean canUserViewConsults(User user) {
        return user.canViewConsultations();
    }

    static boolean canUserViewSensitive(User user) {
        return user.canViewSensitiveConsultations();
    }

    static boolean canUserCreateConsults(User user) {
        return user.canCreateConsultations();
    }

    static List<Consult> filterConsultsForUser(User user, List<Consult> allConsults) {
        List<Consult> filtered = new ArrayList<>();

        if (!canUserViewConsults(user)) {
            return filtered;
        }

        if (canUserViewSensitive(user)) {
            filtered.addAll(allConsults);
            return filtered;
        }

        for (Consult consult : allConsults) {
            if (!consult.isSensitive()) {
                filtered.add(consult);
            }
        }
        return filtered;
    }

    static void createConsultWithInput(Scanner scanner, Patient patient, User user) {
        if (!canUserCreateConsults(user)) {
            System.out.println("[ACCESS DENIED] You don't have permission to create consultations.");
            return;
        }

        Consult consult = ConsultInputHandler.collectConsultFrom(scanner, user);
        if (consult == null) {
            return;
        }

        patient.addConsult(consult.getDate(), consult.getNote(), consult.isSensitive());
        System.out.println("Consult note added successfully!");
    }
}
