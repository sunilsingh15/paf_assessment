package vttp2023.batch3.assessment.paf.bookings.models;

import java.sql.Date;

public class Reservation {

    private String id;
    private String name;
    private String email;
    private String accomodationID;
    private Date arrivalDate;
    private int durationOfStay;

    public Reservation() {

    }

    public Reservation(String id, String name, String email, String accomodationID, Date arrivalDate,
            int durationOfStay) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.accomodationID = accomodationID;
        this.arrivalDate = arrivalDate;
        this.durationOfStay = durationOfStay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccomodationID() {
        return accomodationID;
    }

    public void setAccomodationID(String accomodationID) {
        this.accomodationID = accomodationID;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getDurationOfStay() {
        return durationOfStay;
    }

    public void setDurationOfStay(int durationOfStay) {
        this.durationOfStay = durationOfStay;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", name=" + name + ", email=" + email + ", accomodationID=" + accomodationID
                + ", arrivalDate=" + arrivalDate + ", durationOfStay=" + durationOfStay + "]";
    }

}
