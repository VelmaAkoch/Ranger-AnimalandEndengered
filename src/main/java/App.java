import models.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        if (process.environment().get("PORT") != null) {
            port = parseInt(process.environment().get("PORT"));
        }else {
            port = 4567;
        }
        port(port);


        //home route
        get("/", (request, response) -> {
            Map<String, Object > model = new HashMap();
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/about", (request, response) -> {
            return new ModelAndView( new HashMap<>(), "about.hbs");
        }, new HandlebarsTemplateEngine());

        //thriving animals form input route
        get("/thriving", (request, response) -> {
            return new ModelAndView( new HashMap<>(), "thriving-form.hbs");
        }, new HandlebarsTemplateEngine());

        //thriving animals form input route for submitting post request
        post("/thriving", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String animalName = request.queryParams("animalName");
            try {
                ThrivingAnimal thrivingAnimal = new ThrivingAnimal(animalName);
                ThrivingAnimalDAO thrivingAnimalDAO = new ThrivingAnimalDAO();
                thrivingAnimalDAO.add(thrivingAnimal);
            } catch (IllegalArgumentException exception) {
                System.out.println("Please enter an animal name.");
            }
            EndangeredAnimalDAO endangeredAnimalDAO = new EndangeredAnimalDAO();
            model.put("eAnimals", endangeredAnimalDAO.getEndangeredAnimals());
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        //display list of animals route
        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ThrivingAnimalDAO thrivingAnimalDAO = new ThrivingAnimalDAO();
            model.put("animals", thrivingAnimalDAO.getThrivingAnimals());
            EndangeredAnimalDAO endangeredAnimalDAO = new EndangeredAnimalDAO();
            model.put("eAnimals", endangeredAnimalDAO.getEndangeredAnimals());
            return new ModelAndView( model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        //deleting an animal
        get("/animals/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ThrivingAnimalDAO thrivingAnimalDAO = new ThrivingAnimalDAO();
            EndangeredAnimalDAO endangeredAnimalDAO = new EndangeredAnimalDAO();
            thrivingAnimalDAO.deleteThrivingAnimal(Integer.parseInt(request.params(":id")));
            endangeredAnimalDAO.deleteEndangeredAnimal(Integer.parseInt(request.params(":id")));
            response.redirect("/animals");
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        //endangered animals form input route
        get("/endangered", (request, response) -> {
            return new ModelAndView( new HashMap<>(), "endangered-form.hbs");
        }, new HandlebarsTemplateEngine());

        //endangered animals form input route for submitting post request
        post("/endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            try {
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name, health, age);
                EndangeredAnimalDAO endangeredAnimalDAO = new EndangeredAnimalDAO();
                endangeredAnimalDAO.add(endangeredAnimal);
                System.out.println(endangeredAnimalDAO.getEndangeredAnimals());
            } catch (IllegalArgumentException exception) {
                System.out.println("Please enter all details");
            }
            EndangeredAnimalDAO endangeredAnimalDAO = new EndangeredAnimalDAO();
            model.put("eAnimals", endangeredAnimalDAO.getEndangeredAnimals());
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        //display list of sightings route
        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ThrivingAnimalDAO thrivingAnimalDAO = new ThrivingAnimalDAO();
            model.put("animals", thrivingAnimalDAO.getThrivingAnimals());
            EndangeredAnimalDAO endangeredAnimalDAO = new EndangeredAnimalDAO();
            model.put("eAnimals", endangeredAnimalDAO.getEndangeredAnimals());
            model.put("sightings", Sightings.all());
            return new ModelAndView( model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        //display animals data in sightings form input route for seen animals
        get("/sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ThrivingAnimalDAO thrivingAnimalDAO = new ThrivingAnimalDAO();
            model.put("animals", thrivingAnimalDAO.getThrivingAnimals());
            EndangeredAnimalDAO endangeredAnimalDAO = new EndangeredAnimalDAO();
            model.put("eAnimals", endangeredAnimalDAO.getEndangeredAnimals());
            return new ModelAndView( model, "sightings-form.hbs");
        }, new HandlebarsTemplateEngine());

        //deleting a sighting
        get("/sightings/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Sightings.find(Integer.parseInt(request.params(":id"))).delete();
            response.redirect("/sightings");
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int animalId = Integer.parseInt(name);
            String location = request.queryParams("location");
            String ranger = request.queryParams("ranger");
            try {
                Sightings sightings = new Sightings( animalId, location, ranger);
                sightings.save();
                System.out.println(Sightings.all());
            } catch (IllegalArgumentException exception) {
                System.out.println("Please enter all details");
            }
            response.redirect("/sightings");
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Sightings.find(Integer.parseInt(request.params(":id"))).update();
            ThrivingAnimalDAO thrivingAnimalDAO = new ThrivingAnimalDAO();
            model.put("animals", thrivingAnimalDAO.getThrivingAnimals());
            EndangeredAnimalDAO endangeredAnimalDAO = new EndangeredAnimalDAO();
            model.put("eAnimals", endangeredAnimalDAO.getEndangeredAnimals());
            return new ModelAndView(model, "sightings-form.hbs");
        }, new HandlebarsTemplateEngine());

        //updating a sighting instance
        post("/sightings/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int id = Integer.parseInt(request.params(":id"));
            String location = request.queryParams("location");
            String rangerName = request.queryParams("rangerName");
            Sightings sighting = Sightings.find(id);
            sighting.setLocation(location);
            sighting.setRanger(rangerName);
            sighting.update();
            response.redirect("/sightings");
            return new ModelAndView(model, "sightings-form.hbs");
        }, new HandlebarsTemplateEngine());

    }
}