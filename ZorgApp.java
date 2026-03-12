class ZorgApp {
    public static void main(String[] args) {
        User user = new User(1001, "Mart ElCamera", Rol.TANDARTS);
        Administration administration = new Administration(user);

        administration.menu();
    }
}
