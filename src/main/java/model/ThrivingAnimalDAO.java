package model;

public class ThrivingAnimalDAO implements ThrivingAnimalManagement {
    public void getDrivers() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
