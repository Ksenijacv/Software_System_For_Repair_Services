/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domenskeKlase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Luka
 */
public class PrijavaKvara extends ApstraktniObjekat implements Serializable {

    private int prijavaID;
    private String opisKvara;
    private Date datumVremePrijave;
    private int status;
    private Serviser serviser;
    private Klijent klijent;
    private IzvestajOServisu izvestaj;

    public PrijavaKvara() {
    }

    public PrijavaKvara(int prijavaID, String opisKvara, Date datumVremePrijave, int status, Serviser serviser, Klijent klijent, IzvestajOServisu izvestaj) {
        this.prijavaID = prijavaID;
        this.opisKvara = opisKvara;
        this.datumVremePrijave = datumVremePrijave;
        this.status = status;
        this.serviser = serviser;
        this.klijent = klijent;
        this.izvestaj = izvestaj;
    }
    
    

    @Override
    public String nazivTabele() {
        return "PrijavaKvara";
    }

    @Override
    public String alijas() {
        return "pk";
    }

    @Override
    public String spajanje() {
        return "JOIN klijent k on (k.klijentid=pk.klijentid)"
                + "JOIN serviser s on(s.serviserid=pk.serviserid)"
                + "JOIN mesto m on (m.mestoid=k.mestoid)";
    }

    @Override
    public ArrayList<ApstraktniObjekat> selectLista(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Mesto m = new Mesto(rs.getInt("mestoID"), rs.getString("NazivMesta"), rs.getString("PttBroj"));
            Serviser s = new Serviser(rs.getInt("ServiserID"), rs.getString("KorisnickoIme"), rs.getString("Lozinka"));
            Klijent k = new Klijent(rs.getInt("KlijentID"), rs.getString("ImePrezimeKlijenta"), rs.getString("Kontakt"), m, s);
            PrijavaKvara pk = new PrijavaKvara(rs.getInt("PrijavaID"), rs.getString("OpisKvara"), rs.getTimestamp("DatumVremePrijave"), rs.getInt("status"), s, k, null);
            lista.add(pk);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(OpisKvara, DatumVremePrijave, Status, KlijentID, ServiserID)";
    }

    @Override
    public String primarniKljuc() {
        return "";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + opisKvara + "','" + new Timestamp(datumVremePrijave.getTime()) + "'," + status + "," + klijent.getKlijentID() + "," + serviser.getServiserID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "Status=" + status;
    }

    @Override
    public String id() {
        return "prijavaid=" + prijavaID;
    }

    public int getPrijavaID() {
        return prijavaID;
    }

    public void setPrijavaID(int prijavaID) {
        this.prijavaID = prijavaID;
    }

    public String getOpisKvara() {
        return opisKvara;
    }

    public void setOpisKvara(String opisKvara) {
        this.opisKvara = opisKvara;
    }

    public Date getDatumVremePrijave() {
        return datumVremePrijave;
    }

    public void setDatumVremePrijave(Date datumVremePrijave) {
        this.datumVremePrijave = datumVremePrijave;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Serviser getServiser() {
        return serviser;
    }

    public void setServiser(Serviser serviser) {
        this.serviser = serviser;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public IzvestajOServisu getIzvestaj() {
        return izvestaj;
    }

    public void setIzvestaj(IzvestajOServisu izvestaj) {
        this.izvestaj = izvestaj;
    }

}
