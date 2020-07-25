package DAO;

import models.Department;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oUserDao implements UserDao{
    private final Sql2o sql2o; //create property of the class to be instantiating, its what connects to the database/ this is the argument used in App.java

    public Sql2oUserDao (Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void add(User user) {

    String sql = "INSERT into users (position, role, department) VALUES ( :position :role, :department);";
    try (Connection con = sql2o.open()){
        int id =(int) con.createQuery(sql, true)//id will be integer form it is being casted into an integer, true mean return generated key
                .bind(user)//passing in object hero to avoid typing .addparameter for each property
                .executeUpdate()
                .getKey();//  this will get primary key and assign to the ID
        user.setId( id);// database assigning id

    }catch (Sql2oException ex) {
        System.out.println("there was a problem in adding the endangered animal");
    }
    }

    @Override
    public User findById(int id) {
            String sql = "SELECT * FROM users WHERE id = :id;";
            try (Connection con = sql2o.open()){
                return con.createQuery(sql)
                        .executeAndFetchFirst(User.class);//takes the first object that matches with the id instead all, hero.class used because something is being returned
            }
        }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(User.class);//takes the object that matches with the id, the execute and fetch fetches everything that matches condition give
        }
    }

    @Override
    public void updateUser(int id, String position, String role, String department) {
        String sql = "UPDATE users SET (position, role, department) = (:position, :role, :department) WHERE id = : id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("position", position)
                    .addParameter("position", position)
                    .addParameter("department", department)// not updating id because its a condition
                    .executeUpdate();//takes the first object that matches with the id instead all
        }
    }

    @Override
    public void deleteUserById(int id) {
            String sql = "DELETE * FROM users departments WHERE id= :id;";
            try (Connection con = sql2o.open()){
                con.createQuery(sql)
                        .addParameter("id", id)
                        .executeUpdate();//takes the first object that matches with the id instead all, hero.class used because something is being returned
            }

    }

    @Override
    public void deleteAllUsers() {
        String sql = "DELETE * FROM users";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();//takes the first object that matches with the id instead all, hero.class used because something is being returned
        }
    }
}
