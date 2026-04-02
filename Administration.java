import java.util.Scanner;


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
    static final int ADD_CONSULT = 9;
    static final int ADD_ALLERGIE = 10;
    static final int ADD_LUNGCAPACITY = 11;
    static final int VIEW_BMI_GRAPH = 12;
    
    PATIENTLIST patientList = new PATIENTLIST();
    loginManager userAcces = new loginManager();
    Account account;                   // The account with all users
    Patient currentPatient;            // The currently selected patient
    User currentUser;               // the current user of the program.


    Administration(User user, Account account) {
        this.currentUser = user;
        this.account = account;
        this.currentPatient = patientList.allPatients.get(0);

        MenuPrinter.Header(currentUser);
    }

    void menu() {
        currentUser.Dashboard();

        var scanner = new Scanner(System.in);  // User input via this scanner.
        boolean nextCycle = true;
       
        while (nextCycle) {
           MenuPrinter.printMainmenu(currentPatient, currentUser);
            
// opties in menu, deze worden getoond afhankelijk van de permissies van de user, en worden uitgevoerd via switch case, waarbij elke case een andere functie aanroept die de gewenste actie uitvoert op de patient data.

            if (scanner.hasNextInt()){ //belangrijk voor letterinput
            int choice = scanner.nextInt();
            
            switch (choice) {
                case STOP: // interrupt the loop
                    nextCycle = false;
                        MenuPrinter.logOutMessage();
                    break;

                case OTHERUSER:
                    userAcces.selectOtherUser( scanner, account, this)  ;
                    MenuPrinter.Header(currentUser);
                    break;

                case QUICKSELECT:
                    patientList.QuickPatient(scanner, this);
                    break;

                case VIEW:
                        currentPatient.viewData(currentUser);
                    break;

                case EDIT_PATIENTDATA:
                    patientList.editPatientData(scanner, this);
                    break;

                case PATIENTLIST:
                    patientList.showAndSelectPatient(scanner, this); 
                    break;
               
                case ADD_MEDICATION:
                    if (currentUser.canAllMedication()) {
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
                    if (currentUser.canAllMedication()) {
                        Medication.DeleteMed(scanner, currentPatient);
                    } else {
                        System.out.println("Permission Denied");
                    }
                    break;

                case ADD_CONSULT:
                    currentPatient.addConsultNoteInput(scanner);
                    break;
                
                case ADD_ALLERGIE:
                    currentPatient.addAllergieInfoInput(scanner);
                    break;

                case ADD_LUNGCAPACITY:
                    currentPatient.lungInfoInput(scanner);
                    break;

                case VIEW_BMI_GRAPH:
                    BmiGrafiek.printBmiGraph(currentPatient);
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
