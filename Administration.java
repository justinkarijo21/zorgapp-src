import java.util.Scanner;

/**
 * class Administration represents the core of the application by showing
 * the main menu, from where all other functionality is accessible, either
 * directly or via sub-menus.
 * An Administration instance needs a User as input, which is passed via the
 * constructor to the data member 'currentUser'.
 * The patient data is available via the data member currentPatient.
 */
public class Administration {
    static final int STOP = 0;
    static final int VIEW = 1;
    static final int QUICKSELECT = 2; 
    static final int EDIT_PATIENTDATA = 3;
    static final int PATIENTLIST = 4; 
    static final int ADD_MEDICATION = 5;
    static final int EDIT_MEDICATION = 6;
    static final int DELETE_MEDICATION = 7;
    
    PATIENTLIST patientList = new PATIENTLIST();
    Patient currentPatient;            // The currently selected patient
    User currentUser;               // the current user of the program.

    /**
     * Constructor
     */

    Administration(User user) {
        this.currentUser = user;
        this.currentPatient = patientList.allPatients.get(0);

        System.out.println("=".repeat(30));
        System.out.format("Logged in as: %s\n", currentUser.getUserName());
        System.out.format("Function:      %s\n", currentUser.getClass().getSimpleName()); // Laat zien welk type kind-klasse het is
        System.out.println("=".repeat(30));
        System.out.format("Current user: [%d] %s\n", user.getUserID(), user.getUserName());
    }

    void menu() {
        currentUser.Dashboard();

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
                    patientList.QuickPatient(scanner, this);
                    break;

                case VIEW:
                    currentPatient.viewData();
                    break;

                case EDIT_PATIENTDATA:
                    patientList.editPatientData(scanner, this);
                    break;

                case PATIENTLIST:
                    patientList.showAndSelectPatient(scanner, this); 
                    break;
               
                case ADD_MEDICATION:
                    Medication.addMedicationToPatient(scanner, currentPatient);
                    break;
                
                case EDIT_MEDICATION:
                    Medication.EditMedication(scanner, currentPatient);
                    break;

                case DELETE_MEDICATION:
                    Medication.DeleteMed(scanner, currentPatient);
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
    

}
