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
 * @author Luka
 */
public class RezervniDeo extends ApstraktniObjekat implements Serializable {

    private int rezervniDeoID;
    private String nazivRezervnogDela;
    private int cenaRezervnogDela;
    private String opisRezervnogDela;
    private Serviser serviser;

    public RezervniDeo() {
    }

    public RezervniDeo(int rezervniDeoID, String nazivRezervnogDela, int cenaRezervnogDela, String opisRezervnogDela, Serviser serviser) {
        this.rezervniDeoID = rezervniDeoID;
        this.nazivRezervnogDela = nazivRezervnogDela;
        this.cenaRezervnogDela = cenaRezervnogDela;
        this.opisRezervnogDela = opisRezervnogDela;
        this.serviser = serviser;
    }

    @Override
    public String nazivTabele() {
        return "RezervniDeo";
    }

    @Override
    public String alijas() {
        return "rd";
    }

    @Override
    public String spajanje() {
        return "JOIN SERVISER S USING(SERVISERID)";
    }

    @Override
    public ArrayList<ApstraktniObjekat> selectLista(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Serviser s = new Serviser(rs.getInt("ServiserID"), rs.getString("KorisnickoIme"), rs.getString("Lozinka"));
            RezervniDeo rd = new RezervniDeo(rs.getInt("RezervniDeoID"), rs.getString("NazivRezervnogDela"), rs.getInt("CenaRezervnogDela"), rs.getString("OpisRezervnogDela"), s);

            lista.add(rd);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(NazivRezervnogDela, CenaRezervnogDela, OpisRezervnogDela, ServiserID)";
    }

    @Override
    public String primarniKljuc() {
        return "";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivRezervnogDela + "'," + cenaRezervnogDela + ",'" + opisRezervnogDela + "'," + serviser.getServiserID();

    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String id() {
        return "";
    }

    public int getRezervniDeoID() {
        return rezervniDeoID;
    }

    public void setRezervniDeoID(int rezervniDeoID) {
        this.rezervniDeoID = rezervniDeoID;
    }

    public String getNazivRezervnogDela() {
        return nazivRezervnogDela;
    }

    public void setNazivRezervnogDela(String nazivRezervnogDela) {
        this.nazivRezervnogDela = nazivRezervnogDela;
    }

    public int getCenaRezervnogDela() {
        return cenaRezervnogDela;
    }

    public void setCenaRezervnogDela(int cenaRezervnogDela) {
        this.cenaRezervnogDela = cenaRezervnogDela;
    }

    public String getOpisRezervnogDela() {
        return opisRezervnogDela;
    }

    public void setOpisRezervnogDela(String opisRezervnogDela) {
        this.opisRezervnogDela = opisRezervnogDela;
    }

    public Serviser getServiser() {
        return serviser;
    }

    public void setServiser(Serviser serviser) {
        this.serviser = serviser;
    }

    @Override
    public String toString() {
        return nazivRezervnogDela;
    }

}
