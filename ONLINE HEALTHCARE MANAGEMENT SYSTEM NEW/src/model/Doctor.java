package model;

public class Doctor {
    private int id;
    private String name;
    private String specialization;

    private static int counter = 1;

    public Doctor() {
        this.id = counter++;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }

    public void setName(String name) { this.name = name; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
}
