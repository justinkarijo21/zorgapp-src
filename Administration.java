import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * class Administration represents the core of the application by showing
 * the main menu, from where all other functionality is accessible, either
 * directly or via sub-menus.
 * An Administration instance needs a User as input, which is passed via the
 * constructor to the data member 'currentUser'.
 * The patient data is available via the data member currentPatient.
 */
class Administration {
    static final int STOP = 0;
    static final int VIEW = 1;
    static final int QUICKSELECT = 2; 
    static final int EDIT_PATIENTDATA = 3;
    static final int PATIENTLIST = 4; 
    static final int ADD_MEDICATION = 5;
    static final int EDIT_MEDICATION = 6;
    static final int DELETE_MEDICATION = 7;
    
   
    List<Patient> allPatients = new ArrayList<>();
    
    Patient currentPatient;            // The currently selected patient
    User currentUser;               // the current user of the program.

    /**
     * Constructor
     */
    Administration(User user) {
        currentUser = user;
       
        allPatients.add (new Patient(9001, "Van Puffelen", "Pierre", LocalDate.of(2000, 2, 29), 75.7, 1.73));
        allPatients.add (new Patient(9002, "Ekkelon", "Jasmijn", LocalDate.of( 2001,  3,22 ),75.8, 1.78));
        allPatients.add (new Patient(9003, "Kali", "Bob", LocalDate.of(2001, 7, 7),75.9, 1.79));
        allPatients.add (new Patient(9004, "Van Dijk", "Virgil", LocalDate.of(1998, 5, 15), 87.9, 1.90));
        allPatients.add (new Patient(9005, "Tranada", "Kay", LocalDate.of(2003, 1, 22), 87.8, 1.95));
        allPatients.add (new Patient(9006, "Van Bussum", "Mark", LocalDate.of(1987, 1, 25), 89.2, 1.95));

        currentPatient = allPatients.get(0);
        System.out.format("Current user: [%d] %s\n", user.getUserID(), user.getUserName());
    }

    void menu() {
        var scanner = new Scanner(System.in);  // User input via this scanner.
        boolean nextCycle = true;
       
        while (nextCycle) {
            System.out.format("%s\n", "=".repeat(80));
            System.out.format("Current patient: %s\n", currentPatient.fullName());

            /*
             Print menu on screen
            */
            System.out.format("%d:  STOP\n", STOP);
            System.out.format("%d:  View patient data\n", VIEW);
            System.out.format("%d:  Quick select patient id\n", QUICKSELECT);
            System.out.format("%d:  Edit current patient data\n", EDIT_PATIENTDATA);
            System.out.format("%d:  Show patient list\n", PATIENTLIST);// gebruik dit om eigen patient id in tevoeren en van daaruit info. alleen naam en geboortedatum en van daaruit kiezen.
            System.out.format("%d:  Add medication to current patient\n", ADD_MEDICATION);
            System.out.format("%d:  EDIT medication to current patient\n", EDIT_MEDICATION);
            System.out.format("%d:  DELETE medication to current patient\n", DELETE_MEDICATION);
            System.out.print("Enter your choice: ");
            
            if (scanner.hasNextInt()){ //belangrijk voor letterinput
            int choice = scanner.nextInt();
            
            switch (choice) {
                case STOP: // interrupt the loop
                    nextCycle = false;
                        System.out.format("%s\n", " ".repeat(80));
                        System.out.format("%s\n", "=".repeat(80));
                        System.out.format("%s\n", " ".repeat(80));
                        System.out.println("Succesfully logged out, you may close this screen");
                    break;

                case QUICKSELECT:
                    QuickPatient(scanner);
                    break;

                case VIEW:
                    currentPatient.viewData();
                    break;

                case EDIT_PATIENTDATA:
                    editPatientData(scanner);
                    break;

                case PATIENTLIST:
                    showAndSelectPatient(scanner); 
                    break;
               
                case ADD_MEDICATION:
                    addMedicationToPatient(scanner);
                    break;
                
                case EDIT_MEDICATION:
                    EditMedication(scanner);
                    break;

                case DELETE_MEDICATION:
                    DeleteMed(scanner);
                    break;

                default:
                    System.out.println("Please enter a *valid* digit");
                    break;
                }
                } else {
                System.out.println("No valid input, Please enter a Digit");
                scanner.next(); //Fixt het probleem van letter input en zorgt ervoor dat ie niet crasht
            }
        }
   scanner.close();
 }
    
 //voids below
    void showAndSelectPatient (Scanner scanner) {
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
                this.currentPatient = p; // We overschrijven de actieve patiënt
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

    void QuickPatient (Scanner scanpatient) { //in de toekomst evt zoeken op naam of geboortedatum
        System.out.print("Select patient ID: ");
                        
                        if (scanpatient.hasNextInt()){
                        int selectedId = scanpatient.nextInt();
                        boolean found = false;
            
                        for (Patient p : allPatients) {
                        if (p.id == selectedId) {
                            this.currentPatient = p; 
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

    void addMedicationToPatient (Scanner Meds) { // voegt medication toe aan een patient
            System.out.println("\nAdding medication for: " + currentPatient.fullName());
            
            System.out.print("Enter medication name: ");
            
                Meds.nextLine(); 
                String medName = Meds.nextLine();

                if (!medName.trim().isEmpty()) {
                    System.out.print("Enter dosage (example, 500mg, 2 tablets): "); //voegt dosage toe aan patient
                    String dosage = Meds.nextLine();

                if (!dosage.trim().isEmpty()) {
                    String fullMed = medName + " (" + dosage + ")";
                    currentPatient.addMedication(fullMed);
                    System.out.println("Successfully added: " + fullMed);
                } else {
                    System.out.println("Error: Dosage cannot be empty");
                    }   
                } else {
                    System.out.println("Error: Medication cannot be empty");
                }
    }

    void EditMedication (Scanner Editmedication){ //past medicatie van patient aan
        System.out.println("\nEditing medication for: " + currentPatient.fullName());

            List<String> meds = currentPatient.medications;
            if (meds.isEmpty()) {
                System.out.println("No medication to edit.");
                return;
            }

            for (int i = 0; i < meds.size(); i++) {
                System.out.println((i + 1) + ". " + meds.get(i));
            }

            System.out.print("\nWhich number do you want to edit? ");
            int index = Editmedication.nextInt() - 1;
            Editmedication.nextLine(); 

            if (index >= 0 && index < meds.size()) {
                String currentFullMed = meds.get(index);
                System.out.println("Current: " + currentFullMed);

                System.out.print("Enter new name (press Enter to keep current): ");
                String newName = Editmedication.nextLine();
                
                System.out.print("Enter new dosage (press Enter to keep current): ");
                String newDosage = Editmedication.nextLine();

                // Alleen aanpassen als er iets getypt is, anders behoud je de oude
                if (!newName.isEmpty() || !newDosage.isEmpty()) {
                    
                    String updatedEntry = (newName.isEmpty() ? "OldName" : newName) + " (" + (newDosage.isEmpty() ? "OldDosage" : newDosage) + ")";
                    currentPatient.medications.set(index, updatedEntry);
                    System.out.println("Updated!");
                }
            }
    }

    void editPatientData (Scanner edit) {
        System.out.println("\n--- Editing Patient ID: " + currentPatient.id + " (ID can't be changed, PRESS ENTER TO KEEP THE SAME INFO) ---");
        edit.nextLine();

            System.out.print("Surname [" + currentPatient.surname + "]: "); //achternaam bewerken
            String newSurname = edit.nextLine();
            if (!newSurname.isEmpty()) currentPatient.surname = newSurname;

            System.out.print("Firstname [" + currentPatient.firstName + "]: "); //voornaam bewerken
            String newFirstname = edit.nextLine();
            if (!newFirstname.isEmpty()) currentPatient.firstName = newFirstname;

            System.out.print("Weight [" + currentPatient.WEIGHT + "]: "); //gewicht bewerken
            String weightInput = edit.nextLine();
            if (!weightInput.isEmpty()) {
            currentPatient.WEIGHT = Double.parseDouble(weightInput);
            }

            System.out.print("Length [" + currentPatient.LENGTH + "]: "); //lengte bewerken
            String lengthInput = edit.nextLine();
            if (!lengthInput.isEmpty()) {
            currentPatient.LENGTH = Double.parseDouble(lengthInput);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            System.out.print("Date of Birth [" + currentPatient.dateOfBirth.format(formatter) +  "] (dd-MM-yyyy): ");
            String dateEdit = edit.nextLine();
            
            if (!dateEdit.isEmpty()) {
            currentPatient.dateOfBirth = LocalDate.parse(dateEdit, formatter);
            }

            System.out.println("\n    Patient data succesfully updated!");
    }

    void DeleteMed(Scanner scanner) {
        System.out.println("\nDeleting medication for: " + currentPatient.fullName());

        List<String> meds = currentPatient.medications;

        if (meds.isEmpty()) {
            System.out.println("No medication to delete.");
            return;
        }

        // toon lijst
        for (int i = 0; i < meds.size(); i++) {
            System.out.println((i + 1) + ". " + meds.get(i));
        }

        System.out.print("\nWhich number do you want to delete? ");

        if (scanner.hasNextInt()) {
            int index = scanner.nextInt() - 1;

            if (index >= 0 && index < meds.size()) {
                String removed = meds.remove(index);
                System.out.println("Removed medication: " + removed);
            } else {
                System.out.println("Invalid number.");
            }
        } else {
            System.out.println("Invalid input.");
            scanner.next();
                }
                    }

}
