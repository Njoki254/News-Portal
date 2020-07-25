package DAO;

import models.Department;
import models.DepartmentNews;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oNewsDao implements NewsDao {
    private final Sql2o sql2o; //create property of the class to be instantiating, its what connects to the database/ this is the argument used in App.java

    public Sql2oNewsDao (Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void add(News news) {
        String sql = "INSERT into news (content, publisher) VALUES ( :content, :publisher);";
        try (Connection con = sql2o.open()){
            int id =(int) con.createQuery(sql, true)//id will be integer form it is being casted into an integer, true mean return generated key
                    .bind(news)//passing in object hero to avoid typing .addparameter for each property
                    .executeUpdate()
                    .getKey();//  this will get primary key and assign to the ID
            news.setId( id);// database assigning id

        }catch (Sql2oException ex) {
            System.out.println("there was a problem in adding the endangered animal");
        }
    }

    @Override
    public News findById(int id) {
        String sql = "SELECT * FROM news WHERE id = :id;";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetchFirst(News.class);//takes the first object that matches with the id instead all, hero.class used because something is being returned
        }
    }

    @Override
    public List<News> getAllNews() {
        String sql = "SELECT * FROM department_news";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(News.class);//takes the object that matches with the id, the execute and fetch fetches everything that matches condition give
        }
    }

    @Override
    public void updateNews(int id, String content, String publisher) {
        String sql = "UPDATE news SET (content, publisher) = (:content, :publisher) WHERE id = : id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("content", content)
                    .addParameter("publisher", publisher)
                    .addParameter("id", id)// not updating id because its a condition
                    .executeUpdate();//takes the first object that matches with the id instead all
        }
    }
    @Override
    public void deleteNewsById(int id) {
        String sql = "DELETE * FROM news WHERE id= :id;";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();//takes the first object that matches with the id instead all, hero.class used because something is being returned
        }

    }

    @Override
    public void deleteAllNews() {
        String sql = "DELETE * FROM news";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();//takes the first object that matches with the id instead all, hero.class used because something is being returned
        }
    }
}
