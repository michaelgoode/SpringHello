package com.springapp.model;

import java.util.Date;

/**
 * Created by michaelgoode on 08/09/2016.
 */
public class AssociationBatch {

    private int batchID;
    private String filename;
    private boolean isSent;
    private Date dateSent;

    public int getBatchID() {
        return batchID;
    }

    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }
}
