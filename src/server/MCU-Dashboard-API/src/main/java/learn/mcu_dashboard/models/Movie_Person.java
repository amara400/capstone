package learn.mcu_dashboard.models;

public class Movie_Person {

    private int idMovie;
    private String role;
    private int idPerson;
    private Person person;

    // constructor
    public Movie_Person(int idMovie, String role, int idPerson, Person person) {
        this.idMovie = idMovie;
        this.role = role;
        this.idPerson = idPerson;
        this.person = person;
    }

    // empty constructor
    public Movie_Person() {}

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
