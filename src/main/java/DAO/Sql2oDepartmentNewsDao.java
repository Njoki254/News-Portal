package DAO;

import models.DepartmentNews;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDepartmentNewsDao implements DepartmentNewsDao{
    private final Sql2o sql2o; //create property of the class to be instantiating, its what connects to the database/ this is the argument used in App.java

    public Sql2oDepartmentNewsDao (Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void add(DepartmentNews departmentNews) {
        String sql = "INSERT into departments_news (content, publisher, department_id) VALUES (:content, :publisher, :department_id);";
        try (Connection con = sql2o.open()){
            int id =(int) con.createQuery(sql, true)//id will be integer form it is being casted into an integer, true mean return generated key
                    .bind(departmentNews)//passing in object hero to avoid typing .addparameter for each property
                    .executeUpdate()
                    .getKey();//  this will get primary key and assign to the ID
            departmentNews.setId(id);// database assigning id

        }catch (Sql2oException ex) {
            System.out.println("there was a problem in adding the endangered animal");
        }
    }

    @Override
    public DepartmentNews findById(int id) {
        String sql = "SELECT * FROM departments_news WHERE id = :id;";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetchFirst(DepartmentNews.class);//takes the first object that matches with the id instead all, hero.class used because something is being returned
        }
    }
    @Override
    public List<DepartmentNews> getAllDepartmentsNews() {
        String sql = "SELECT * FROM department_news";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(DepartmentNews.class);//takes the object that matches with the id, the execute and fetch fetches everything that matches condition give
        }
    }

    @Override
    public void updateDepartmentNews(int id, String content, String publisher, int departmentId) {

        String sql = "UPDATE departments_news SET (content, publisher, departmentId) = (:content, :publisher, :departmentId) WHERE id = : id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("content", content)
                    .addParameter("publisher", publisher)
                    .addParameter("departmentId", departmentId)
                    .addParameter("id", id)
                    //.addParameter("id", id)// not updating id because its a condition
                    .executeUpdate();//takes the first object that matches with the id instead all
        }
    }

    @Override
    public void deleteDepartmentNewsById(int id) {
        String sql = "DELETE * FROM departments_news WHERE id= :id;";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();//takes the first object that matches with the id instead all, hero.class used because something is being returned
        }

    }


    @Override
    public void deleteAllDepartmentNews() {
        String sql = "DELETE * FROM departments_news";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();//takes the first object that matches with the id instead all, hero.class used because something is being returned
        }
    }

}
