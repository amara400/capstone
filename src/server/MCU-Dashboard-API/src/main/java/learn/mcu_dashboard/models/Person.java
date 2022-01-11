package learn.mcu_dashboard.models;

public class Person {

    private int idPerson;
    private String name;

//    // constructor
//    public Person(int idPerson, String name) {
//        this.idPerson = idPerson;
//        this.name = name;
//    }
//
//    // empty constructor
//    public Person() {}

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
