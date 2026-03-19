import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



class Patient {
   static final int RETURN      = 0;
   static final int SURNAME     = 1;
   static final int FIRSTNAME   = 2;
   static final int DATEOFBIRTH = 3; 
   
   int       id;
   String    surname;
   String    firstName;
   LocalDate dateOfBirth;
   double WEIGHT;
   double LENGTH;

List<String> medications = new ArrayList<>();
List<String> consultNotes = new ArrayList<>();

    /**
     * Constructor
     */
    Patient(int id, String surname, String firstName, LocalDate dateOfBirth, double WEIGHT, double LENGTH) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.WEIGHT = WEIGHT;
        this.LENGTH = LENGTH;
        this.medications = new ArrayList<>();
        this.consultNotes = new ArrayList<>();
    }

    public int getAge(){
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    String getSurname() {
        return surname;
    }

    String getFirstName() {
        return firstName;
    }

    /**
     * Display patient data.
     */
    void addMedication(String medicationname){
        this.medications.add(medicationname);
    }

    void addConsultNote(LocalDate date, String note){
        String formatted = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ": " + note;
        this.consultNotes.add(formatted);
    }

    void addConsultNoteInput(Scanner scanner) {
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

        addConsultNote(consultDate, note);
        System.out.println("Consult note added.");
    }
    
    void viewData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.format("===== Patient id=%d ==============================\n", id);
        System.out.format("%-17s %s\n", "Surname:", surname);
        System.out.format("%-17s %s\n", "First Name:", firstName);
        System.out.format("%-17s %s\n", "Date of birth:", dateOfBirth.format(formatter));
        System.out.format("%-17s %s\n", "Age:", getAge()); 
        System.out.format("%-17s %s\n", "Weight:", WEIGHT);
        System.out.format("%-17s %s\n", "Length:", LENGTH);
        System.out.format("%-17s %s\n", "Medication", medications.isEmpty() ? "None" : String.join(", ", medications));
        System.out.format("%-17s %s\n", "Consult Notes:", consultNotes.isEmpty() ? "None" : String.join("; ", consultNotes));
        System.out.format("%-17s %.1f\n", "BMI:", (WEIGHT)/(LENGTH*LENGTH)); //%.1f\n is afronden op 1 decimaal
    }

    /**
     * Shorthand for a Patient's full name
     */
    String fullName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return String.format("%s %s [%s]", firstName, surname, dateOfBirth.format(formatter));
    }


}
