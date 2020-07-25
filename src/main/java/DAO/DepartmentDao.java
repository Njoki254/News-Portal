package DAO;

import models.Department;

import java.util.List;

public interface DepartmentDao {
    void add(Department department);// putting add method mandatory for all hero objects
    Department findById(int id);
    List<Department> getAllDepartments();
    void updateDepartment(int id, String description, int eNumber);
    void deleteDepartmentById(int id);
    void deleteAllDepartment();
}