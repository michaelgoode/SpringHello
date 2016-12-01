package com.springapp.model;

/**
 * Created by michaelgoode on 08/09/2016.
 */
public class Contract {

    private String contract;
    private String epc;
    private String upc;
    private String associationDate;
    private String transmissionId;
    private String labelForm;
    private String bureau;

    public Contract() {};

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
}
