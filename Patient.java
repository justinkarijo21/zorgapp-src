import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//opsplit 
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
   double Lunginhoud;

List<String> allergies = new ArrayList<>();
List<String> medications = new ArrayList<>();
List<String> consultNotes = new ArrayList<>();
List<WeightEntry> weightHistory = new ArrayList<>();

    /**
     * Weight Entry class to store weight/length measurements with dates
     */
    static class WeightEntry {
        LocalDate date;
        double weight;
        double length;
        
        WeightEntry(LocalDate date, double weight, double length) {
            this.date = date;
            this.weight = weight;
            this.length = length;
        }
        
        double calculateBmi() {
            return weight / (length * length);
        }
    }


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
        this.allergies = new ArrayList<>();
        this.weightHistory = new ArrayList<>();
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

   

    //voids voor add medicatie tot patient en add consult note tot patient
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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

    void addAllergieInfo(String allergieInfo){
        String InfoFormat = allergieInfo;
        this.allergies.add(InfoFormat);
    }

    void recordWeightEntry(LocalDate date, double weight, double length) {
        this.WEIGHT = weight;
        this.LENGTH = length;
        this.weightHistory.add(new WeightEntry(date, weight, length));
    }

    void recordWeightEntry(double weight, double length) {
        this.WEIGHT = weight;
        this.LENGTH = length;
        this.weightHistory.add(new WeightEntry(LocalDate.now(), weight, length));
    }

    void addAllergieInfoInput(Scanner scanner){
        if (scanner.hasNextLine()){
            scanner.nextLine();
        }

        System.out.println("Enter Patients allergie:");
        String allergieInfo = scanner.nextLine().trim();
        if(allergieInfo.isEmpty()){
            System.out.println("No allergie added, returning");
            return;
        }

        addAllergieInfo(allergieInfo);
        System.out.println("Allergie added");
    }

void lungInfoInput (Scanner scanner){
    if (scanner.hasNextLine()){
        scanner.nextLine();
    }

    System.out.println("Enter current lung capacity: ");
            String lungs = scanner.nextLine();
            if (!lungs.isEmpty()) {
            Lunginhoud = Double.parseDouble(lungs);
            }
 
            System.out.println("Lung capacity added!");
}

    private boolean isPainReliever(String medication) {
        String lowerMed = medication.toLowerCase();
        return lowerMed.contains("(painkiller)");
    }

    List<String> getFilteredMedications(User user) {
        List<String> filtered = new ArrayList<>();
        
        if (user.canViewPainRelieversOnly()) {
            // Fysio: ziet alleen pain relievers
            for (String med : medications) {
                if (isPainReliever(med)) {
                    filtered.add(med);
                }
            }
        } else if (!user.canViewMedication()) {
            // Tandarts die geen meds kunnen zien: return empty
            return filtered;
        } else {
            // Huisarts and Apotheker: zien alle medicatie
            filtered.addAll(medications);
        }
        return filtered;
    }

    //patient constructor
    void viewData(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.format("===== Patient id=%d ==============================\n", id);
        System.out.format("%-17s %s\n", "Surname:", surname);
        System.out.format("%-17s %s\n", "First Name:", firstName);
        System.out.format("%-17s %s\n", "Date of birth:", dateOfBirth.format(formatter));
        System.out.format("%-17s %s\n", "Age:", getAge()); 
        System.out.format("%-17s %s\n", "Weight:", WEIGHT);
        System.out.format("%-17s %s\n", "Length:", LENGTH);
        List<String> visibleMeds = getFilteredMedications(user);
        System.out.format("%-17s %.1f\n", "Current BMI:", (WEIGHT)/(LENGTH*LENGTH)); //%.1f\n is afronden op 1 decimaal
        System.out.format("%-17s %s\n", "Consult Notes:", consultNotes.isEmpty() ? "None" : String.join("; ", consultNotes));
        System.out.format("%-17s %s\n", "Allergies:", allergies);
        System.out.format("%-17s %s\n", "Lung capacity", Lunginhoud);
    }

    /**
     * Shorthand for a Patient's full name
     */
    String fullName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return String.format("%s %s [%s]", firstName, surname, dateOfBirth.format(formatter));
    }


}
