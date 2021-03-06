package com.pingvin.autoservice.model;

import com.pingvin.autoservice.form.ReserveForm;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

public class OrderInfo {
    private int id;
    private int customer;
    private int offer;
    private int masterId;
    private int kitId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFinish;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public int getKitId() {
        return kitId;
    }

    public void setKitId(int kitId) {
        this.kitId = kitId;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = timeCut(dateStart);
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = timeCut(dateFinish);
    }

    public OrderInfo() {
    }

    public OrderInfo(Date dateStart, Date dateFinish) {
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public OrderInfo(int id, int customer, int offer, int masterId, int kitId, Date dateStart, Date dateFinish) {
        this.id = id;
        this.customer = customer;
        this.offer = offer;
        this.masterId = masterId;
        this.kitId = kitId;
        this.dateStart = timeCut(dateStart);
        this.dateFinish = timeCut(dateFinish);
    }

    @Override
    public String toString() {
        return "OrderHistoryInfo{" +
                "id=" + id +
                ", customer=" + customer +
                ", offer='" + offer + '\'' +
                ", masterId=" + masterId +
                ", kitId='" + kitId + '\'' +
                ", dateStart=" + dateStart +
                ", dateFinish=" + dateFinish +
                '}';
    }

    public static Date timeCut(Date rawDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(rawDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
