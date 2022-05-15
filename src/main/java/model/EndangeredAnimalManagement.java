package model;

import java.util.List;

public interface EndangeredAnimalManagement {
    void add(EndangeredAnimal endangeredAnimal);
    void update(EndangeredAnimal endangeredAnimal);
    List<EndangeredAnimal> getEndangeredAnimalId(int id);
    List<EndangeredAnimal> getEndangeredAnimals();
    void deleteEndangeredAnimal(int id);
    void  deleteAllEndangeredAnimals();
}
