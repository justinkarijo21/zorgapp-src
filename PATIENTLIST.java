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
        // Pierre Van Puffelen (ID 9001) - Normal BMI progression
        Patient pierre = allPatients.get(0);
        pierre.addBmiEntry(LocalDate.of(2023, 1, 15), 22.5);
        pierre.addBmiEntry(LocalDate.of(2023, 4, 10), 23.1);
        pierre.addBmiEntry(LocalDate.of(2023, 7, 5), 22.8);
        pierre.addBmiEntry(LocalDate.of(2023, 10, 12), 23.5);
        pierre.addBmiEntry(LocalDate.of(2024, 1, 8), 24.2);

        // Jasmijn Ekkelon (ID 9002) - Slight increase
        Patient jasmijn = allPatients.get(1);
        jasmijn.addBmiEntry(LocalDate.of(2023, 2, 20), 24.0);
        jasmijn.addBmiEntry(LocalDate.of(2023, 5, 15), 24.3);
        jasmijn.addBmiEntry(LocalDate.of(2023, 8, 10), 24.8);
        jasmijn.addBmiEntry(LocalDate.of(2023, 11, 5), 25.1);
        jasmijn.addBmiEntry(LocalDate.of(2024, 2, 1), 25.4);

        // Bob Kali (ID 9003) - Overweight trend
        Patient bob = allPatients.get(2);
        bob.addBmiEntry(LocalDate.of(2023, 3, 1), 26.2);
        bob.addBmiEntry(LocalDate.of(2023, 6, 15), 26.8);
        bob.addBmiEntry(LocalDate.of(2023, 9, 20), 27.1);
        bob.addBmiEntry(LocalDate.of(2023, 12, 10), 27.5);
        bob.addBmiEntry(LocalDate.of(2024, 3, 5), 28.0);

        // Virgil Van Dijk (ID 9004) - Stable overweight
        Patient virgil = allPatients.get(3);
        virgil.addBmiEntry(LocalDate.of(2023, 1, 20), 25.8);
        virgil.addBmiEntry(LocalDate.of(2023, 4, 25), 25.9);
        virgil.addBmiEntry(LocalDate.of(2023, 7, 30), 26.1);
        virgil.addBmiEntry(LocalDate.of(2023, 10, 15), 25.7);
        virgil.addBmiEntry(LocalDate.of(2024, 1, 10), 26.0);

        // Kay Tranada (ID 9005) - Underweight to normal
        Patient kay = allPatients.get(4);
        kay.addBmiEntry(LocalDate.of(2023, 2, 5), 17.8);
        kay.addBmiEntry(LocalDate.of(2023, 5, 12), 18.2);
        kay.addBmiEntry(LocalDate.of(2023, 8, 18), 18.9);
        kay.addBmiEntry(LocalDate.of(2023, 11, 25), 19.5);
        kay.addBmiEntry(LocalDate.of(2024, 2, 15), 20.1);

        // Mark Van Bussum (ID 9006) - Obese
        Patient mark = allPatients.get(5);
        mark.addBmiEntry(LocalDate.of(2023, 1, 30), 31.2);
        mark.addBmiEntry(LocalDate.of(2023, 4, 20), 31.8);
        mark.addBmiEntry(LocalDate.of(2023, 7, 15), 32.1);
        mark.addBmiEntry(LocalDate.of(2023, 10, 8), 31.9);
        mark.addBmiEntry(LocalDate.of(2024, 1, 5), 32.5);
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

            System.out.print("Weight [" + admin.currentPatient.WEIGHT + "]: "); //gewicht bewerken
            String weightInput = edit.nextLine();
            if (!weightInput.isEmpty()) {
            admin.currentPatient.WEIGHT = Double.parseDouble(weightInput);
            }

            System.out.print("Length [" + admin.currentPatient.LENGTH + "]: "); //lengte bewerken
            String lengthInput = edit.nextLine();
            if (!lengthInput.isEmpty()) {
            admin.currentPatient.LENGTH = Double.parseDouble(lengthInput);
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