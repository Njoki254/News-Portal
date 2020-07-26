import DAO.Sql2oDepartmentDao;
import DAO.Sql2oDepartmentNewsDao;
import DAO.Sql2oNewsDao;
import DAO.Sql2oUserDao;
import models.Department;
import models.DepartmentNews;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import com.google.gson.Gson;

import java.util.List;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        String connectionString = "jdbc:postgresql://localhost:5432/news-portal"; //connect to todolist, not todolist_test!
        Sql2oUserDao userDbStorage; //heroDao is just a variable storing, can call it anything
        Sql2oNewsDao newsDbStorage;
        Sql2oDepartmentNewsDao departmentNewsDbStorage;
        Sql2oDepartmentDao departmentDbStorage;
        Connection conn;
        Gson gson = new Gson();// instantiation, importing compiling
        Sql2o sql2o = new Sql2o(connectionString, "njoki254", "cookie254");// store data into this account, asking app to connect postgre to heroika datab, pass username and password
        userDbStorage = new Sql2oUserDao(sql2o);//sql20hero stored into heroDao
        newsDbStorage = new Sql2oNewsDao(sql2o);
        departmentDbStorage = new Sql2oDepartmentDao(sql2o);//sql20hero stored into heroDao
        departmentNewsDbStorage = new Sql2oDepartmentNewsDao(sql2o);
        conn = sql2o.open(); //now opening database, connecting
        // sqlsquad is a string, suadDao is the variable
        //trying to connect database to
        // Sql2o is inbuilt into system
        //

        //type “psvm + tab” to autocreate this
        staticFileLocation("/public");
        //create new department
        post("/department/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);// the body is what user has entered, stands for inputs, convert,
            // json is a javascript, what api can read, when going sent as json, but back must be any java gson was created by google to convert in between json to language using or vice versa
            departmentDbStorage.add(department); // in api constructor is not used at all to instantiate or collect data, save to database
            res.status(201);// status code represents that the code has been successful, 201 spcifically means successful
            res.type("application/json");//not needed because of filter response will be in json format response must be json so browser can read, application will manipulate it to a user friendly format, browser can only read html, javascript,css
            return gson.toJson(department);//converted back to json so it can be displayed in java
        });
        //get: display new departmnets
        get("/departments", "application/json", (req, res) -> {
            List<Department> departments = departmentDbStorage.getAllDepartments();
            res.status(200);
            return gson.toJson(departments);//gson used to convert either from user to java, when creating new instance convert back
        });
        post("/news/new", "application/json", (req, res) -> {
            News newses = gson.fromJson(req.body(), News.class);// the body is what user has entered, stands for inputs, convert,
            // json is a javascript, what api can read, when going sent as json, but back must be any java gson was created by google to convert in between json to language using or vice versa
            newsDbStorage.add(newses); // in api constructor is not used at all to instantiate or collect data, save to database
            res.status(201);// status code represents that the code has been successful, 201 spcifically means successful
            res.type("application/json");//not needed because of filter response will be in json format response must be json so browser can read, application will manipulate it to a user friendly format, browser can only read html, javascript,css
            return gson.toJson(newses);//converted back to json so it can be displayed in java
        });
        //get: display new departmnets
        get("/newses", "application/json", (req, res) -> {
            List<News> newses = newsDbStorage.getAllNews();
            res.status(200);
            return gson.toJson(newses);//gson used to convert either from user to java, when creating new instance convert back
        });
        //FILTERS something to be run after every root, res.type, runs every root, and before the next method after everypost, does same think as res.type, makes dry
        post("/DepartmentNews/new", "application/json", (req, res) -> {
            DepartmentNews departmentnews = gson.fromJson(req.body(), DepartmentNews.class);// the body is what user has entered, stands for inputs, convert,
            // json is a javascript, what api can read, when going sent as json, but back must be any java gson was created by google to convert in between json to language using or vice versa
            departmentNewsDbStorage.add(departmentnews); // in api constructor is not used at all to instantiate or collect data, save to database
            res.status(201);// status code represents that the code has been successful, 201 spcifically means successful
            res.type("application/json");//not needed because of filter response will be in json format response must be json so browser can read, application will manipulate it to a user friendly format, browser can only read html, javascript,css
            return gson.toJson(departmentnews);//converted back to json so it can be displayed in java
        });
        //get: display new departmnets
        get("/departmentnews", "application/json", (req, res) -> {
            List<DepartmentNews> departmentnews = departmentNewsDbStorage.getAllDepartmentsNews();
            res.status(200);
            return gson.toJson(departmentnews);//gson used to convert either from user to java, when creating new instance convert back
        });
        //FILTERS something to be run after every root, res.type, runs every root, and before the next method after everypost, does same think as res.type, makes dry
        post("/User/new", "application/json", (req, res) -> {
            User users = gson.fromJson(req.body(), User.class);// the body is what user has entered, stands for inputs, convert,
            // json is a javascript, what api can read, when going sent as json, but back must be any java gson was created by google to convert in between json to language using or vice versa
            userDbStorage.add(users); // in api constructor is not used at all to instantiate or collect data, save to database
            res.status(201);// status code represents that the code has been successful, 201 spcifically means successful
            res.type("application/json");//not needed because of filter response will be in json format response must be json so browser can read, application will manipulate it to a user friendly format, browser can only read html, javascript,css
            return gson.toJson(users);//converted back to json so it can be displayed in java
        });
        //get: display new departmnets
        get("/users", "application/json", (req, res) -> {
            List<User> users = userDbStorage.getAllUsers();
            res.status(200);
            return gson.toJson(users);//gson used to convert either from user to java, when creating new instance convert back
        });
        after((req, res) -> {
            res.type("application/json");
        });

    }

}