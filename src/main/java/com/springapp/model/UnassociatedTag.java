package com.springapp.model;

/**
 * Created by michaelgoode on 05/09/2016.
 */
public class UnassociatedTag {
    private int batch;
    private String EPC;
    private boolean valid;
    private String filename;
    private String labelForm;
    private String bureau;
    private String contract;
    private String sent;
    private boolean nowSent;
    private String association;
    private String upc;
    private String reportName;

    public String getEPC() {
        return EPC;
    }

    public void setEPC(String EPC) {
        this.EPC = EPC;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnassociatedTag that = (UnassociatedTag) o;

        return EPC != null ? EPC.equals(that.EPC) : that.EPC == null;

    }

    @Override
    public int hashCode() {
        return EPC != null ? EPC.hashCode() : 0;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public String getSent() {
        return sent;
    }

    public boolean isNowSent() {
        return nowSent;
    }

    public void setNowSent(boolean nowSent) {
        this.nowSent = nowSent;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public UnassociatedTag() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
