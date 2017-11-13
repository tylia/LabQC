package sample.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Hematology {
    private LocalDate date;
    private int rbc;
    private int mcv;
    private int hct;
    private int hgb;
    private int mch;
    private int mchc;
    private int plt;
    private int wbc;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getRbc() {
        return rbc;
    }

    public void setRbc(int rbc) {
        this.rbc = rbc;
    }

    public int getMcv() {
        return mcv;
    }

    public int getHgb() {
        return hgb;
    }

    public void setHgb(int hgb) {
        this.hgb = hgb;
    }

    public void setMcv(int mcv) {
        this.mcv = mcv;
    }

    public int getHct() {
        return hct;
    }

    public void setHct(int hct) {
        this.hct = hct;
    }

    public int getMch() {
        return mch;
    }

    public void setMch(int mch) {
        this.mch = mch;
    }

    public int getMchc() {
        return mchc;
    }

    public void setMchc(int mchc) {
        this.mchc = mchc;
    }

    public int getPlt() {
        return plt;
    }

    public void setPlt(int plt) {
        this.plt = plt;
    }

    public int getWbc() {
        return wbc;
    }

    public void setWbc(int wbc) {
        this.wbc = wbc;
    }
}
