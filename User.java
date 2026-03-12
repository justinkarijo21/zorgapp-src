
class User {
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
    public void setUsername(String UserName){
        this.UserName = UserName;
    }

    public void Dashboard (){
        System.out.println("Loading Dashboard");
    }
}
    
//classes rollen
class Huisarts extends User{
    public Huisarts (int id, String name){
        super(id, name);
    }
        @Override
         public void Dashboard() {
        System.out.println("DASHBOARD HUISARTS: " + getUserName());
        System.out.println("\n- Your Patient options");
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




