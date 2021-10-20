package com.amaticorp.test.model;

public class ModelQuery {
    String id;
    String link;
    String codice;
    String descr;
    double qt;
    double price;

    public ModelQuery(String id, String link, String codice, String descr, double qt, double price) {
        this.id = id;
        this.link = link;
        this.codice = codice;
        this.descr = descr;
        this.qt = qt;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescr() {
        return descr;
    }

    @Override
    public String toString() {
        return this.id + " --> Descr: " + this.descr + " - Qt: " + this.qt + " - Prezzo:" + this.price;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public double getQt() {
        return qt;
    }

    public void setQt(double qt) {
        this.qt = qt;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
