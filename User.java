public class User {
    protected String userName;
    protected int userID;

    public User(int id, String name) {
        this.userID = id;
        this.userName = name;
    }
// getters
    public String getUserName() {
        return userName;
    }

    public int getUserID() {
        return userID;
    }

// setters
    public void setUsername(String userName){
        this.userName = userName;
    }

    public void Dashboard (){
        System.out.println("Loading Dashboard");
    }

// Access and permissions
    public boolean canViewMedication() {
        return false;
    }

    public boolean canAddAllMedication() {
        return false;
    }
    public boolean canEditMedication() {
        return false;
    }

    public boolean canViewPainRelieversOnly() {
        return false;
    }

    public boolean canAddAllergie(){
        return false;
    }

    public boolean canAddLungInfo(){
        return false;
    }

    public boolean canViewConsultations() {
        return false;
    }

    public boolean canCreateConsultations() {
        return false;
    }

    public boolean canViewSensitiveConsultations() {
        return false;
    }
}

//classes rollen met verschillende permissies, deze worden gebruikt om te bepalen welke opties er in het menu worden getoond voor de verschillende users, en welke acties ze kunnen uitvoeren op de patient data.
class Huisarts extends User{
    public Huisarts (int id, String name){
        super(id, name);
    }
        @Override
         public void Dashboard() {
        System.out.println("DASHBOARD HUISARTS: " + getUserName());
        System.out.println("\n- Your Patient options");
    }

    @Override
    public boolean canViewMedication() {
        return true;
    }

    @Override
    public boolean canAddAllMedication() {
        return true;
    }

    @Override
    public boolean canEditMedication() {
        return true;
    }

    @Override
    public boolean canAddAllergie() {
        return true;
    }

    @Override
    public boolean canViewConsultations() {
        return true;
    }

    @Override
    public boolean canCreateConsultations() {
        return true;
    }

    @Override
    public boolean canViewSensitiveConsultations() {
        return true; // Huisarts can see sensitive consultations
    }
}

class Fysio extends User{
    public Fysio (int id, String name){
        super(id, name);
    }
        @Override
        public void Dashboard(){
            System.out.println("DASHBOARD FYSIO: " + getUserName());
            System.out.println("\n- Your Patient options");
        }
    
    @Override
    public boolean canViewPainRelieversOnly() {
        return true;
    }

    @Override
    public boolean canAddLungInfo(){
        return true;
    }

    @Override
    public boolean canViewConsultations() {
        return true;
    }

    @Override
    public boolean canCreateConsultations() {
        return true;
    }

    @Override
    public boolean canViewSensitiveConsultations() {
        return false; // Fysio cannot see sensitive consultations
    }
}

class Apotheker extends User{
    public Apotheker (int id, String name){
        super(id, name);
    }
        @Override
        public void Dashboard(){
            System.out.println("DASHBOARD APOTHEKER: " + getUserName());
            System.out.println("\n- Your Patient options");
        }

    @Override
    public boolean canViewMedication() {
        return true;
    }

    @Override
    public boolean canEditMedication() {
        return true;
    }

    @Override
    public boolean canViewConsultations() {
        return true;
    }

    @Override
    public boolean canCreateConsultations() {
        return true;
    }

    @Override
    public boolean canViewSensitiveConsultations() {
        return false; // Apotheker cannot see sensitive consultations
    }
}

class Tandarts extends User{
    public Tandarts (int id, String name){
        super(id, name);
    }
        @Override
        public void Dashboard(){
            System.out.println("DASHBOARD TANDARTS: " + getUserName());
            System.out.println("\n- Your Patient options");
        }
}





