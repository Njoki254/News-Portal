package DAO;


import models.DepartmentNews;

import java.util.List;

public interface DepartmentNewsDao {
    void add(DepartmentNews departmentnews);// putting add method mandatory for all hero objects
    DepartmentNews findById(int id);
    List<DepartmentNews> getAllDepartmentsNews();
    void updateDepartmentNews(int id, String content, String publisher, int departmentId);
    void deleteDepartmentNewsById(int id);
    void deleteAllDepartmentNews();
}