package learn.mcu_dashboard.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Movie {

    private int idMovie;
    private String title;
    private LocalDate UsReleaseDate;
    private int runtime;
    private float ImdbRating;
    private int metascore;
    private BigDecimal budget;
    private BigDecimal domesticGross;
    private BigDecimal totalGross;
    private BigDecimal openingGross;
    private int oscarNominations;
    private int oscarsWon;
    private String franchise;
    // Do we need a list for persons or genres??

    // constructor
    public Movie(int idMovie, String title, LocalDate usReleaseDate,
                 int runtime, float imdbRating, int metascore,
                 BigDecimal budget, BigDecimal domesticGross,
                 BigDecimal totalGross, BigDecimal openingGross,
                 int oscarNominations, int oscarsWon, String franchise) {
        this.idMovie = idMovie;
        this.title = title;
        UsReleaseDate = usReleaseDate;
        this.runtime = runtime;
        ImdbRating = imdbRating;
        this.metascore = metascore;
        this.budget = budget;
        this.domesticGross = domesticGross;
        this.totalGross = totalGross;
        this.openingGross = openingGross;
        this.oscarNominations = oscarNominations;
        this.oscarsWon = oscarsWon;
        this.franchise = franchise;
    }

    //empty constructor
    public Movie(){}

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getUsReleaseDate() {
        return UsReleaseDate;
    }

    public void setUsReleaseDate(LocalDate usReleaseDate) {
        UsReleaseDate = usReleaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public float getImdbRating() {
        return ImdbRating;
    }

    public void setImdbRating(float imdbRating) {
        ImdbRating = imdbRating;
    }

    public int getMetascore() {
        return metascore;
    }

    public void setMetascore(int metascore) {
        this.metascore = metascore;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getDomesticGross() {
        return domesticGross;
    }

    public void setDomesticGross(BigDecimal domesticGross) {
        this.domesticGross = domesticGross;
    }

    public BigDecimal getTotalGross() {
        return totalGross;
    }

    public void setTotalGross(BigDecimal totalGross) {
        this.totalGross = totalGross;
    }

    public BigDecimal getOpeningGross() {
        return openingGross;
    }

    public void setOpeningGross(BigDecimal openingGross) {
        this.openingGross = openingGross;
    }

    public int getOscarNominations() {
        return oscarNominations;
    }

    public void setOscarNominations(int oscarNominations) {
        this.oscarNominations = oscarNominations;
    }

    public int getOscarsWon() {
        return oscarsWon;
    }

    public void setOscarsWon(int oscarsWon) {
        this.oscarsWon = oscarsWon;
    }

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }
}
