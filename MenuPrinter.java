public class MenuPrinter {
    public static void Header(User currentUser) {
              System.out.println("=".repeat(30));
        System.out.format("Logged in as: %s\n", currentUser.getUserName());
        System.out.format("Function:      %s\n", currentUser.getClass().getSimpleName()); // Laat zien welk type kind-klasse het is
        System.out.println("=".repeat(30));
        System.out.format("Current user: [%d] %s\n", currentUser.getUserID(), currentUser.getUserName());
    }

    static void printMainmenu(Patient currentPatient, User currentUser){
 System.out.format("%s\n", "=".repeat(80));
            System.out.format("Current patient: %s\n", currentPatient.fullName());

           
            System.out.println("0:  STOP\n");
            System.out.println("1:  Select other user\n");
            System.out.println("2:  View patient data\n");
            System.out.println("3:  Quick select patient id\n");
            System.out.println("4:  Edit current patient data\n");
            System.out.println("5:  Show patient list\n");// gebruik dit om eigen patient id in tevoeren en van daaruit info. alleen naam en geboortedatum en van daaruit kiezen.
            if (currentUser.canAddMedication()) {
                System.out.println("6:  Add medication to current patient\n");
            }
            if (currentUser.canEditMedication()) {
                 System.out.println("7:  EDIT medication to current patient\n" );
                 System.out.println("8:  DELETE medication to current patient\n"); 
            }
            System.out.println("9:  Add consult note\n");

            System.out.print("Enter your choice: ");
    }

    public static void logOutMessage(){
        System.out.format("%s\n", " ".repeat(80));
                        System.out.format("%s\n", "=".repeat(80));
                        System.out.format("%s\n", " ".repeat(80));
                        System.out.println("Succesfully logged out, you may close this screen");
    }
}
