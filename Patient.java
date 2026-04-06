import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Patient {
    int id;
    String surname;
    String firstName;
    LocalDate dateOfBirth;
    double weight;
    double height;
    double lungCapacity;

    List<String> allergies = new ArrayList<>();
    List<String> medications = new ArrayList<>();
    List<Consult> consultNotes = new ArrayList<>();
    List<WeightEntry> weightHistory = new ArrayList<>();

    static class WeightEntry {
        LocalDate date;
        double weight;
        double height;

        WeightEntry(LocalDate date, double weight, double height) {
            this.date = date;
            this.weight = weight;
            this.height = height;
        }

        double getBmi() {
            return weight / (height * height);
        }
    }

    Patient(int id, String surname, String firstName, LocalDate dateOfBirth, double weight, double height) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.height = height;
        recordWeightEntry(LocalDate.now(), weight, height);
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

    /**
     * Add a consultation note with sensitivity classification.
     * @param date The consultation date
     * @param note The consultation notes
     * @param isSensitive Whether this is a sensitive consultation
     */
    void addConsult(LocalDate date, String note, boolean isSensitive) {
        this.consultNotes.add(new Consult(date, note, isSensitive));
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

        addConsult(consultDate, note, false); // Default to non-sensitive for backward compatibility
        System.out.println("Consult note added.");
    }

    void addAllergieInfo(String allergieInfo){
        String InfoFormat = allergieInfo;
        this.allergies.add(InfoFormat);
    }

    void recordWeightEntry(LocalDate date, double weight, double length) {
        this.weight = weight;
        this.height = length;
        this.weightHistory.add(new WeightEntry(date, weight, length));
    }

    void recordWeightEntry(double weight, double length) {
        this.weight = weight;
        this.height = length;
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
            this.lungCapacity = Double.parseDouble(lungs);
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
        System.out.format("%-17s %.1f\n", "Weight:", weight);
        System.out.format("%-17s %.2f\n", "Length:", height);
        List<String> visibleMeds = getFilteredMedications(user);
        System.out.format("%-17s %.1f\n", "Current BMI:", weight / (height * height)); //%.1f\n is afronden op 1 decimaal
        System.out.format("%-17s %s\n", "Medication:", visibleMeds.isEmpty() ? "None" : String.join("; ", visibleMeds));
        
        // Get filtered consultations based on user's access level
        List<Consult> visibleConsults = ConsultService.getFilteredConsults(user, consultNotes);
        String consultDisplay = formatConsultNotes(visibleConsults);
        System.out.format("%-17s %s\n", "Consult Notes:", consultDisplay);
        
        System.out.format("%-17s %s\n", "Allergies:", allergies);
        System.out.format("%-17s %s\n", "Lung capacity", lungCapacity);
    }

    /**
     * Format consultation notes for display.
     * Shows sensitivity labels to Huisarts, hides them from others.
     * 
     * @param consults List of consultations to display
     * @return Formatted string for display
     */
    private String formatConsultNotes(List<Consult> consults) {
        if (consults.isEmpty()) {
            return "None";
        }
        
        // Build display string showing consult information
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < consults.size(); i++) {
            if (i > 0) {
                display.append("; ");
            }
            Consult c = consults.get(i);
            // Show sensitivity only if user is Huisarts
            if (ConsultService.canUserViewSensitive(getCurrentUserFromContext())) {
                display.append(c.toString()); // Includes [SENSITIVE] label
            } else {
                display.append(c.getFormattedNote()); // No sensitivity label
            }
        }
        return display.toString();
    }
    
    /**
     * Helper method to get the current user for display purposes.
     * Note: This is a workaround - in a real system, pass the user as a parameter to viewData.
     * This is stored during viewData() call.
     */
    private User currentUserContext = null;
    
    void viewDataWithUser(User user) {
        this.currentUserContext = user;
        viewData(user);
    }
    
    private User getCurrentUserFromContext() {
        return currentUserContext != null ? currentUserContext : new User(0, "unknown");
    }

    /**
     * Shorthand for a Patient's full name
     */
    String fullName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return String.format("%s %s [%s]", firstName, surname, dateOfBirth.format(formatter));
    }


}
