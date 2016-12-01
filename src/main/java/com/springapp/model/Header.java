package com.springapp.model;

/**
 * Created by michaelgoode on 06/08/2016.
 */
public class Header {

    String contract;
    String upc;
    String assocDate;
    String transmissionId;
    String labelForm;
    String bureau;

    public Header(String contract, String upc, String assocDate, String transmissionId, String labelForm, String bureau) {
        this.contract = contract;
        this.upc = upc;
        this.assocDate = assocDate;
        this.transmissionId = transmissionId;
        this.labelForm = labelForm;
        this.bureau = bureau;
    }

    public Header() {

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

    public String getAssocDate() {
        return assocDate;
    }

    public void setAssocDate(String assocDate) {
        this.assocDate = assocDate;
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
