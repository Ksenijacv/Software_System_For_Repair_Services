/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domenskeKlase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ksenija
 */
public class Klijent extends ApstraktniObjekat implements Serializable {

    private int klijentID;
    private String imePrezime;
    private String kontakt;
    private Mesto mesto;
    private Serviser serviser;

    public Klijent() {
    }

    public Klijent(int klijentID, String imePrezime, String kontakt, Mesto mesto, Serviser serviser) {
        this.klijentID = klijentID;
        this.imePrezime = imePrezime;
        this.kontakt = kontakt;
        this.mesto = mesto;
        this.serviser = serviser;
    }

    public void setServiser(Serviser serviser) {
        this.serviser = serviser;
    }

    public Serviser getServiser() {
        return serviser;
    }

    @Override
    public String nazivTabele() {
        return "klijent";
    }

    @Override
    public String alijas() {
        return "k";
    }

    @Override
    public String spajanje() {
        return "JOIN mesto m using (mestoid)"
                + "JOIN serviser s using (serviserid)";
    }

    @Override
    public String toString() {
        return imePrezime;
    }
    

    @Override
    public ArrayList<ApstraktniObjekat> selectLista(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Mesto m = new Mesto(rs.getInt("mestoID"), rs.getString("NazivMesta"), rs.getString("PttBroj"));
            Serviser s = new Serviser(rs.getInt("ServiserID"), rs.getString("KorisnickoIme"), rs.getString("Lozinka"));
            Klijent k = new Klijent(rs.getInt("KlijentID"), rs.getString("ImePrezimeKlijenta"), rs.getString("Kontakt"), m, s);
            lista.add(k);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(ImePrezimeKlijenta, Kontakt, MestoID, ServiserID)";
    }

    @Override
    public String primarniKljuc() {
        return "mestoID=" +mesto.getMestoID();
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + imePrezime + "','" + kontakt + "'," + mesto.getMestoID() + "," + serviser.getServiserID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "ImePrezimeKlijenta='" + imePrezime + "',Kontakt='" + kontakt + "',MestoID=" + mesto.getMestoID() + ",ServiserID=" + serviser.getServiserID();
    }

    @Override
    public String id() {
        return "KlijentID=" + klijentID;
    }

    public int getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(int klijentID) {
        this.klijentID = klijentID;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

}
