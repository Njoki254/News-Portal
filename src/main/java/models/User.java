package models;

import java.util.Objects;

public class User {

    private int id;
    private String position;
    private String role;
    private String department;

    public User(String position, String role, String department) {
        this.position = position;
        this.role = role;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }
    public void setId(int id){
        this.id=id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
        return id == users.id &&
                Objects.equals(position, users.position) &&
                Objects.equals(role, users.role) &&
                Objects.equals(department, users.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position, role, department);
    }
}
