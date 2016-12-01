package com.springapp.model;

import java.util.Hashtable;

/**
 * Created by michaelgoode on 24/08/2016.
 */
public class EPC {
    private String contract;
    private String epc;
    private String upc;
    private String associationDate;
    private String transmissionId;
    private String labelForm;
    private String bureau;
    private boolean smlValid;

    private Hashtable<String,String> valuesMap = new Hashtable<String, String>();
    private Hashtable<String,String> messages = new Hashtable<String, String>();

    public EPC() {};

    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getAssociationDate() {
        return associationDate;
    }

    public void setAssociationDate(String associationDate) {
        this.associationDate = associationDate;
    }

    public String getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(String transmissionId) {
        this.transmissionId = transmissionId;
    }

    public String getLabelForm() {
        return labelForm;
    }

    public void setLabelForm(String labelForm) {
        this.labelForm = labelForm;
    }

    public String getBureau() {
        return bureau;
    }

    public void setBureau(String bureau) {
        this.bureau = bureau;
    }

    public Hashtable<String, String> getValuesMap() {
        return valuesMap;
    }

    public void setValuesMap(Hashtable<String, String> valuesMap) {
        this.valuesMap = valuesMap;
    }

    public Hashtable<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Hashtable<String, String> messages) {
        this.messages = messages;
    }

    public boolean isSmlValid() {
        return smlValid;
    }

    public void setSmlValid(boolean smlValid) {
        this.smlValid = smlValid;
    }
}
