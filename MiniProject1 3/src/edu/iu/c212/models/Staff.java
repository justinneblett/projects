package edu.iu.c212.models;

public class Staff {
    private String fullName;

    private int age;






    private String role;
    private String availability;


    public Staff(String fullName, int age, String role) {
        this.fullName = fullName;
        this.age = age;
        this.role = role;

    }


    public Staff(String fullName, int age, String role, String availability) {
        this.fullName = fullName;
        this.age = age;
        this.role = role;
        this.availability = availability;
    }






    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }



    public String getRole() {
        return role;
    }



    public String getAvailability() {
        return availability;
    }



    public void setRole(String role){
        this.role = role;
    }



    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return getFullName() + " " + getAge() + " " + getRole() + " " + getAvailability();

    }
}
