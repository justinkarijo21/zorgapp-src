import java.util.List;
import java.util.Scanner;

public class Medication{

    static final String[] STANDARD_MEDICATIONS = {
        "Paracetamol (Painkiller)",
        "Ibuprofen (Painkiller)",
        "Aspirin (Painkiller)",
        "Amoxicillin",
        "Lisinopril",
        "Metformin",
        "Omeprazole",
        "Losartan",
        "Atorvastatin",
        "Diclofenac (Painkiller)"
    };

    //methodes, voids van alle medicatie functies, add, edit, delete
    static void addMedicationToPatient(Scanner Meds, Patient currentPatient) { // voegt medication toe aan een patient
        System.out.println("\nAdding medication for: " + currentPatient.fullName());
        System.out.println("\nStandard medications:");
        for (int i = 0; i < STANDARD_MEDICATIONS.length; i++) {
            System.out.println((i + 1) + ". " + STANDARD_MEDICATIONS[i]);
        }
        System.out.println((STANDARD_MEDICATIONS.length + 1) + ". Custom medication");
        
        System.out.print("Select medication (number or custom): ");
        Meds.nextLine(); 
        String choice = Meds.nextLine().trim();

        String medName;
        if (choice.equalsIgnoreCase("custom") || choice.equals(String.valueOf(STANDARD_MEDICATIONS.length + 1))) {
            System.out.print("Enter custom medication name: ");
            medName = Meds.nextLine().trim();
        } else {
            try {
                int index = Integer.parseInt(choice) - 1;
                if (index >= 0 && index < STANDARD_MEDICATIONS.length) {
                    medName = STANDARD_MEDICATIONS[index];
                } else {
                    System.out.println("Invalid selection.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                return;
            }
        }

        if (!medName.trim().isEmpty()) {
            System.out.print("Enter dosage (example, 500mg, 2 tablets): ");
            String dosage = Meds.nextLine().trim();

            if (!dosage.isEmpty()) {
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

    static void EditMedication (Scanner Editmedication, Patient currentPatient){ //past medicatie van patient aan
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

    static void DeleteMed(Scanner scanner, Patient currentPatient) {
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