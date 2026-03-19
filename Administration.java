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
    static final int OTHERUSER = 1;
    static final int VIEW = 2;
    static final int QUICKSELECT = 3; 
    static final int EDIT_PATIENTDATA = 4;
    static final int PATIENTLIST = 5; 
    static final int ADD_MEDICATION = 6;
    static final int EDIT_MEDICATION = 7;
    static final int DELETE_MEDICATION = 8;
    
    PATIENTLIST patientList = new PATIENTLIST();
    Patient currentPatient;            // The currently selected patient
    User currentUser;               // the current user of the program.


    Administration(User user) {
        this.currentUser = user;
        this.currentPatient = patientList.allPatients.get(0);

        MenuPrinter.Header(currentUser);
    }

    void menu() {
        currentUser.Dashboard();

        var scanner = new Scanner(System.in);  // User input via this scanner.
        boolean nextCycle = true;
       
        while (nextCycle) {
           MenuPrinter.printMainmenu(currentPatient, currentUser);
            
            if (scanner.hasNextInt()){ //belangrijk voor letterinput
            int choice = scanner.nextInt();
            
            switch (choice) {
                case STOP: // interrupt the loop
                    nextCycle = false;
                        MenuPrinter.logOutMessage();
                    break;

                case OTHERUSER:
                    currentUser.selectOtherUser(scanner, new Account(), this);
                    MenuPrinter.Header(currentUser);
                    break;

                case QUICKSELECT:
                    patientList.QuickPatient(scanner, this);
                    break;

                case VIEW:
                    if (currentUser.canViewMedication()) {
                        currentPatient.viewData();
                    } else {
                        System.out.println("Permission Denied");
                    }
                    break;

                case EDIT_PATIENTDATA:
                    patientList.editPatientData(scanner, this);
                    break;

                case PATIENTLIST:
                    patientList.showAndSelectPatient(scanner, this); 
                    break;
               
                case ADD_MEDICATION:
                    if (currentUser.canEditMedication()) {
                        Medication.addMedicationToPatient(scanner, currentPatient);
                    } else {
                        System.out.println("Permission Denied");
                    }
                    break;
                
                case EDIT_MEDICATION:
                    if (currentUser.canEditMedication()) {
                        Medication.EditMedication(scanner, currentPatient);
                    } else {
                        System.out.println("Permission Denied");
                    }
                    break;

                case DELETE_MEDICATION:
                    if (currentUser.canEditMedication()) {
                        Medication.DeleteMed(scanner, currentPatient);
                    } else {
                        System.out.println("Permission Denied");
                    }
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
