package models;

import java.util.Objects;

public class DepartmentNews extends News {
    private int departmentId;
    private int id;

    public DepartmentNews(String content, String publisher, int departmentId) {
        super(content, publisher);
        this.departmentId=departmentId;
    }
    public int getDepartmentId(){
        return departmentId;
    }
    public void setId(int id){
        this.id=id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DepartmentNews that = (DepartmentNews) o;
        return departmentId == that.departmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), departmentId);
    }
}
