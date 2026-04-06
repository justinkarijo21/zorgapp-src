import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


class Consult {
    LocalDate date;
    String note;
    boolean isSensitive;
    
    // Types of consultations
    static final boolean SENSITIVE = true;
    static final boolean NON_SENSITIVE = false;

    
    Consult(LocalDate date, String note, boolean isSensitive) {
        this.date = date;
        this.note = note;
        this.isSensitive = isSensitive;
    }

    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String sensitivityLabel = isSensitive ? "[SENSITIVE]" : "[NON-SENSITIVE]";
        return date.format(formatter) + ": " + sensitivityLabel + " " + note;
    }
    

    String getFormattedNote() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter) + ": " + note;
    }
    
    public boolean isSensitive() {
        return isSensitive;
    }
}
