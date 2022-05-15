package model;

public class ThrivingAnimalDAO implements ThrivingAnimalManagement {
    public void getDrivers() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void add(ThrivingAnimal thrivingAnimal) {
        //add by inserting to database
        getDrivers();
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (animalName, animalType) VALUES (:animalName, :animalType)";

            thrivingAnimal.id = (int) con.createQuery(sql, true)
                    .addParameter("animalName", thrivingAnimal.animalName)
                    .addParameter("animalType", thrivingAnimal.animalType)
                    .executeUpdate()
                    .getKey();
        } catch (Sql2oException e){
            System.out.println(e);
        }

    }







}
