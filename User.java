enum Rol {
    HUISARTS, APOTHEKER, FYSIONTHERAPEUT, TANDARTS
}

class User {
    String userName;
    int userID;
    Rol rol;

    public User(int id, String name, Rol rol) {
        this.userID = id;
        this.userName = name;
        this.rol = rol;
    }


    String getUserName() {
        return userName;
    }

    int getUserID() {
        return userID;
    }

    Rol getRol(){
        return rol;
    }

    
}
