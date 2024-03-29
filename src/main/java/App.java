import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        get("/posts/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newpost-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/posts/new", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String name =request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String power= request.queryParams("power");
            String weakness = request.queryParams("weakness");
            Post newPost = new Post(name,age,power,weakness);
            model.put("post", newPost);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

            get("/", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                ArrayList<Post> posts = Post.getAll();
                model.put("posts", posts);

                return new ModelAndView(model, "index.hbs");
            }, new HandlebarsTemplateEngine());
        get("/posts/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToFind = Integer.parseInt(req.params(":id")); //pull id - must match route segment
            Post foundPost = Post.findById(idOfPostToFind); //use it to find post
            model.put("post", foundPost); //add it to model for template to display
            return new ModelAndView(model, "post-detail.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());
        get("/posts/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToEdit = Integer.parseInt(req.params("id"));
            Post editPost = Post.findById(idOfPostToEdit);
            model.put("editPost", editPost);
            return new ModelAndView(model, "newpost-form.hbs");
        }, new HandlebarsTemplateEngine());
        post("/posts/:id/update", (req, res) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            int newAge =Integer.parseInt(req.queryParams("age"));
            String newPower = req.queryParams("power");
            String newWeakness = req.queryParams("weakness");
            String newContent = req.queryParams("content");
            int idOfPostToEdit = Integer.parseInt(req.params("id"));
            Post editPost = Post.findById(idOfPostToEdit);
            editPost.update(newName,newAge,newPower,newWeakness);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/posts/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Post.clearAllPosts();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/posts/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToDelete = Integer.parseInt(req.params("id"));
            Post deletePost = Post.findById(idOfPostToDelete);
            deletePost.deletePost();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squads/form", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());
        post("squads/form", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String squadName =request.queryParams("squadName");
            int maxSize = Integer.parseInt(request.queryParams("maxSize"));
            String cause= request.queryParams("cause");
            Squad newSquad = new Squad(squadName,maxSize,cause);
            model.put("post", newSquad);
            return new ModelAndView(model, "success2.hbs");
        }, new HandlebarsTemplateEngine());
        get("/list", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Squad> squads = Squad.getAll();
            model.put("squads", squads);

            return new ModelAndView(model, "squad-list.hbs");
        }, new HandlebarsTemplateEngine());

        }
    }



