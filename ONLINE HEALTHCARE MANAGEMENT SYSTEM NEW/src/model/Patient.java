package model;

public class Patient {
    private int id;
    private String name;
    private int age;

    private static int counter = 1;

    public Patient() {
        this.id = counter++;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
}
