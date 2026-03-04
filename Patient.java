import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

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
    void viewData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.format("===== Patient id=%d ==============================\n", id);
        System.out.format("%-17s %s\n", "Surname:", surname);
        System.out.format("%-17s %s\n", "First Name:", firstName);
        System.out.format("%-17s %s\n", "Date of birth:", dateOfBirth.format(formatter));
        System.out.format("%-17s %s\n", "Age:", getAge()); 
        System.out.format("%-17s %s\n", "Weight:", WEIGHT);
        System.out.format("%-17s %s\n", "Length:", LENGTH);
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
