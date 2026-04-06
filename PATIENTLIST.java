import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PATIENTLIST{

  List<Patient> allPatients;
//deze class bevat een lijst van alle patiënten, en methodes om deze te tonen en te selecteren, en om de data van een patiënt te bewerken.

    public PATIENTLIST() {
    allPatients = new ArrayList<>();
        
        allPatients.add (new Patient(9001, "Van Puffelen", "Pierre", LocalDate.of(2000, 2, 29), 75.7, 1.73));
        allPatients.add (new Patient(9002, "Ekkelon", "Jasmijn", LocalDate.of( 2001,  3,22 ),75.8, 1.78));
        allPatients.add (new Patient(9003, "Kali", "Bob", LocalDate.of(2001, 7, 7),75.9, 1.79));
        allPatients.add (new Patient(9004, "Van Dijk", "Virgil", LocalDate.of(1998, 5, 15), 87.9, 1.90));
        allPatients.add (new Patient(9005, "Tranada", "Kay", LocalDate.of(2003, 1, 22), 87.8, 1.95));
        allPatients.add (new Patient(9006, "Van Bussum", "Mark", LocalDate.of(1987, 1, 25), 89.2, 1.95));

        // Add BMI history data to patients
        addBmiDataToPatients();
    }

    private void addBmiDataToPatients() {
        // No pre-loaded data - weight history will be populated dynamically during program session
    }
 
        //voids benadrukt op tonen van patientenlijst en selecteren van patient, en bewerken van patient data
        public void showAndSelectPatient (Scanner scanner, Administration admin) {
        System.out.println("\n AVAILABLE PATIENTS");
        System.out.format("%-4s %-20s %-3s\n", "ID:", "Name:", "Date of Birth:");
        System.out.println("-".repeat(45));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Patient p : allPatients){
            System.out.format("%-4d %-19s %-13s\n", p.id, p.firstName + " " + p.surname, p.dateOfBirth.format(dtf));

        }

        System.out.println("\n Enter Patient ID to select: ");
        
        if (scanner.hasNextInt()){
            int selectedId = scanner.nextInt();
            boolean found = false;
            
            for (Patient p : allPatients) {
            if (p.id == selectedId) {
                admin.currentPatient = p; // We overschrijven de actieve patiënt
                System.out.println("\nSelection successful: " + p.firstName + " is now the current patient.");
                found = true;
                break; // Stop de loop want we hebben hem gevonden
                 }
            }
            if (!found) {
            System.out.println("Error: No patient found with ID " + selectedId);
        }
     } else {
        System.out.println("Invalid input. Please enter a number.");
        scanner.next(); // Clear de foute input         
        }
        
    }

    //methodes en voids alles voor patient
    public void QuickPatient (Scanner scanpatient, Administration admin) { //in de toekomst evt zoeken op naam of geboortedatum
        System.out.print("Select patient ID: ");
                        
                        if (scanpatient.hasNextInt()){
                        int selectedId = scanpatient.nextInt();
                        boolean found = false;
            
                        for (Patient p : allPatients) {
                        if (p.id == selectedId) {
                            admin.currentPatient = p; 
                            System.out.println("\nSelection successful: " + p.firstName + " is now the current patient.");
                            found = true;
                            break; // Stop de loop want we hebben hem gevonden
                            }
                            }
                        
                        if (!found) {
                        System.out.println("Error: No patient found with ID " + selectedId);
                        }

                            } else {
                            System.out.println("Invalid input. Please enter a number.");
                            scanpatient.next(); // Clear de foute input         
                         } 
                            
    }


    public void editPatientData (Scanner edit, Administration admin) {
        System.out.println("\n--- Editing Patient ID: " + admin.currentPatient.id + " (ID can't be changed, PRESS ENTER TO KEEP THE SAME INFO) ---");
        edit.nextLine();

            System.out.print("Surname [" + admin.currentPatient.surname + "]: "); //achternaam bewerken
            String newSurname = edit.nextLine();
            if (!newSurname.isEmpty()) admin.currentPatient.surname = newSurname;

            System.out.print("Firstname [" + admin.currentPatient.firstName + "]: "); //voornaam bewerken
            String newFirstname = edit.nextLine();
            if (!newFirstname.isEmpty()) admin.currentPatient.firstName = newFirstname;

            System.out.print("Weight [" + admin.currentPatient.weight + "]: "); //gewicht bewerken
            String weightInput = edit.nextLine();
            double newWeight = admin.currentPatient.weight;
            boolean weightChanged = false;
            if (!weightInput.isEmpty()) {
                newWeight = Double.parseDouble(weightInput);
                weightChanged = true;
            }

            System.out.print("Length [" + admin.currentPatient.height + "]: "); //lengte bewerken
            String lengthInput = edit.nextLine();
            double newLength = admin.currentPatient.height;
            boolean lengthChanged = false;
            if (!lengthInput.isEmpty()) {
                newLength = Double.parseDouble(lengthInput);
                lengthChanged = true;
            }

            // Record weight entry if weight or length actually changed
            if (weightChanged || lengthChanged) {
                admin.currentPatient.recordWeightEntry(newWeight, newLength);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            System.out.print("Date of Birth [" + admin.currentPatient.dateOfBirth.format(formatter) +  "] (dd-MM-yyyy): ");
            String dateEdit = edit.nextLine();
            
            if (!dateEdit.isEmpty()) {
            admin.currentPatient.dateOfBirth = LocalDate.parse(dateEdit, formatter);
            }

            System.out.println("\n    Patient data succesfully updated!");
    }
}