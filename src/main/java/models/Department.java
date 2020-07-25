package models;

import java.util.Objects;

public class Department {
    private int id;
    private String description;
    private int eNumber;

    public Department( String description, int eNumber) {

        this.description = description;
        this.eNumber = eNumber;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int geteNumber() {
        return eNumber;
    }
    public void setId(int id){
        this.id=id;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id &&
                eNumber == that.eNumber &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, eNumber);
    }
}
