package de.novatec.bpm.model;

import java.util.ArrayList;
import java.util.List;

public class Reservation {

    private String reservationId;
    private List<String> seats = new ArrayList<>();
    private long price;
    private Ticket ticket;
    private UserAccount userAccount;
    private String userId;
    private boolean transactionSuccessful = false;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "Sitze " + String.join(", ", seats);
    }

    public boolean isTransactionSuccessful() {
        return transactionSuccessful;
    }

    public void setTransactionSuccessful(boolean transactionSuccessful) {
        this.transactionSuccessful = transactionSuccessful;
    }
}
