package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class Scheduling {



    @Id
    private String address;
    private String day;
    private String month;
    private String year;
    private String hour;
    private String agentSchedule;
    private String special_req;
    private String acceptScheduling;
    private String rejection_message;
    private String user;

    public Scheduling(String address, String day, String month, String year, String hour, String agentSchedule, String special_req, String user) {
        this.user=user;
        this.address = address;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.agentSchedule = agentSchedule;
        this.special_req = special_req;
        this.acceptScheduling ="not responded";
        this.rejection_message=" ";
    }
    public Scheduling()
    {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setAgentSchedule(String agentSchedule) {
        this.agentSchedule = agentSchedule;
    }

    public void setSpecial_req(String special_req) {
        this.special_req = special_req;
    }

    public String getHour() {
        return hour;
    }

    public String getAgentSchedule() {
        return agentSchedule;
    }

    public String getSpecial_req() {
        return special_req;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public String getAcceptScheduling() {
        return acceptScheduling;
    }

    public void setAcceptScheduling(String acceptScheduling) {
        this.acceptScheduling = acceptScheduling;
    }

    public String getRejection_message() {
        return rejection_message;
    }

    public void setRejection_message(String rejection_message) {
        this.rejection_message = rejection_message;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Scheduling scheduling = (Scheduling) object;
        return address.equals(scheduling.address) && day.equals(scheduling.day) && month.equals(scheduling.month) && year.equals(scheduling.year) && hour.equals(scheduling.hour) && agentSchedule.equals(scheduling.agentSchedule) && special_req.equals(scheduling.special_req) && acceptScheduling.equals(scheduling.acceptScheduling) && rejection_message.equals(scheduling.rejection_message) && user.equals(scheduling.user);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), address, day, month, year, hour, agentSchedule, special_req, acceptScheduling, rejection_message, user);
    }

    @Override
    public String toString() {
        return "Scheduling{" +
                "address= " + address +"\n" +
                ", day=" + day  +
                ", month=" + month  +
                ", year= " + year  +
                ", hour= " + hour  + "\n" +
                ", agent_book= " + agentSchedule +
                ", special_req= " + special_req   + "\n" +
                ", accept_booking= " + acceptScheduling +
                ", rejection_message= " + rejection_message +
                ", Name='" + user  +
                '}';
    }
}