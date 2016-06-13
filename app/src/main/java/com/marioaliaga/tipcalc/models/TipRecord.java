package com.marioaliaga.tipcalc.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maliaga on 13-06-16.
 */
public class TipRecord {
    private double bill;
    private int tipPorcentage;
    private Date timestamp;

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public int getTipPorcentage() {
        return tipPorcentage;
    }

    public void setTipPorcentage(int tipPorcentage) {
        this.tipPorcentage = tipPorcentage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getTip(){
        return bill*(tipPorcentage/100d);
    }

    public String getDateFormatted(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM dd.yyyy HH:mm");
        return simpleDateFormat.format(timestamp);
    }
}
