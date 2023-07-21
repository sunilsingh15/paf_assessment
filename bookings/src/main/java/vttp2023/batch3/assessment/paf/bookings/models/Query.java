package vttp2023.batch3.assessment.paf.bookings.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Query {

    @NotNull(message = "Country cannot be null!")
    @NotEmpty(message = "Country cannot be empty!")
    private String country;

    @NotNull(message = "Number of persons cannot be null!")
    @Min(value = 1, message = "Number of persons cannot be less than 1!")
    @Max(value = 10, message = "Number of persons cannot be more than 10!")
    private Integer numberOfPersons;

    @NotNull(message = "Range minimum cannot be null!")
    @Min(value = 1, message = "Range minimum cannot be less than 1!")
    private Integer rangeMin;

    @NotNull(message = "Range maximum cannot be null!")
    @Max(value = 1000, message = "Range maximum cannot be more than 1000!")
    private Integer rangeMax;

    public Query() {
    }

    public Query(String country, Integer numberOfPersons, Integer rangeMin, Integer rangeMax) {
        this.country = country;
        this.numberOfPersons = numberOfPersons;
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(Integer numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public Integer getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(Integer rangeMin) {
        this.rangeMin = rangeMin;
    }

    public Integer getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(Integer rangeMax) {
        this.rangeMax = rangeMax;
    }

    @Override
    public String toString() {
        return "Query [country=" + country + ", numberOfPersons=" + numberOfPersons + ", rangeMin=" + rangeMin
                + ", rangeMax=" + rangeMax + "]";
    }

}
