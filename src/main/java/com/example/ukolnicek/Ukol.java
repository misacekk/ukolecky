package com.example.ukolnicek;

public class Ukol {
    private String nazev;
    private String predmet;
    private boolean splneno;

    public Ukol(String nazev, String predmet, boolean splneno) {
        this.nazev = nazev;
        this.predmet = predmet;
        this.splneno = splneno;
    }

    public String getNazev() { return nazev; }
    public void setNazev(String nazev) { this.nazev = nazev; }
    public String getPredmet() { return predmet; }
    public void setPredmet(String predmet) { this.predmet = predmet; }
    public boolean isSplneno() { return splneno; }
    public void setSplneno(boolean splneno) { this.splneno = splneno; }

    @Override
    public String toString() {
        return nazev + " (" + predmet + ") " + (splneno ? "✔" : "✘");
    }

}