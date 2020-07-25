package DAO;

import models.Department;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {
    private final Sql2o sql2o; //create property of the class to be instantiating, its what connects to the database/ this is the argument used in App.java

    public Sql2oDepartmentDao (Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void add(Department department) {
        String sql = "INSERT into departments (description, eNumber) VALUES ( :description, :eNumber);";
        try (Connection con = sql2o.open()){
            int id =(int) con.createQuery(sql, true)//id will be integer form it is being casted into an integer, true mean return generated key
                    .bind(department)//passing in object hero to avoid typing .addparameter for each property
                    .executeUpdate()
                    .getKey();//  this will get primary key and assign to the ID
            department.setId( id);// database assigning id

        }catch (Sql2oException ex) {
            System.out.println("there was a problem in adding the endangered animal");
        }
    }

    @Override
    public Department findById(int id) {
        String sql = "SELECT * FROM departments WHERE id = :id;";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetchFirst(Department.class);//takes the first object that matches with the id instead all, hero.class used because something is being returned
        }
    }

    @Override
    public List<Department> getAllDepartments() {
            String sql = "SELECT * FROM heroes";
            try (Connection con = sql2o.open()){
                return con.createQuery(sql)
                        .executeAndFetch(Department.class);//takes the object that matches with the id, the execute and fetch fetches everything that matches condition give
            }
    }

    @Override
    public void updateDepartment(int id, String description, int eNumber) {
            String sql = "UPDATE departments SET (description, eNumber) = (:description, :eNumber) WHERE id = : id";
            try (Connection con = sql2o.open()){
                con.createQuery(sql)
                        .addParameter("description", description)
                        .addParameter("eNumber", eNumber)
                        .addParameter("id", id)// not updating id because its a condition
                        .executeUpdate();//takes the first object that matches with the id instead all
            }
    }

    @Override
    public void deleteDepartmentById(int id) {
        String sql = "DELETE * FROM departments WHERE id= :id;";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();//takes the first object that matches with the id instead all, hero.class used because something is being returned
        }

    }

    @Override
    public void deleteAllDepartment() {

        String sql = "DELETE * FROM departments";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .executeUpdate();//takes the first object that matches with the id instead all, hero.class used because something is being returned
        }



    }
}
