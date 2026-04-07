import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Consult {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LocalDate date;
    private final String note;
    private final boolean sensitive;

    Consult(LocalDate date, String note, boolean sensitive) {
        this.date = date;
        this.note = note;
        this.sensitive = sensitive;
    }

    LocalDate getDate() {
        return date;
    }

    String getNote() {
        return note;
    }

    boolean isSensitive() {
        return sensitive;
    }

    @Override
    public String toString() {
        String sensitivityLabel = sensitive ? "[SENSITIVE]" : "[NON-SENSITIVE]";
        return formatDate() + ": " + sensitivityLabel + " " + note;
    }

    String getFormattedNote() {
        return formatDate() + ": " + note;
    }

    private String formatDate() {
        return date.format(DATE_FORMATTER);
    }
}
