package sample.model;

import javafx.event.Event;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hematology)) return false;

        Hematology that = (Hematology) o;

        if (getRbc() != that.getRbc()) return false;
        if (getMcv() != that.getMcv()) return false;
        if (getHct() != that.getHct()) return false;
        if (getHgb() != that.getHgb()) return false;
        if (getMch() != that.getMch()) return false;
        if (getMchc() != that.getMchc()) return false;
        if (getPlt() != that.getPlt()) return false;
        if (getWbc() != that.getWbc()) return false;
        return getDate() != null ? getDate().equals(that.getDate()) : that.getDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getDate() != null ? getDate().hashCode() : 0;
        result = 31 * result + getRbc();
        result = 31 * result + getMcv();
        result = 31 * result + getHct();
        result = 31 * result + getHgb();
        result = 31 * result + getMch();
        result = 31 * result + getMchc();
        result = 31 * result + getPlt();
        result = 31 * result + getWbc();
        return result;
    }
}
