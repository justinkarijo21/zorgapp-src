import java.util.List;
import java.util.StringJoiner;

class ConsultFormatter {
    static String formatConsultList(User user, List<Consult> consults) {
        if (consults.isEmpty()) {
            return "None";
        }

        StringJoiner joiner = new StringJoiner("; ");
        for (Consult consult : consults) {
            joiner.add(formatConsultForUser(user, consult));
        }
        return joiner.toString();
    }

    private static String formatConsultForUser(User user, Consult consult) {
        if (user.canViewSensitiveConsultations()) {
            return consult.toString();
        }
        return consult.getFormattedNote();
    }
}
