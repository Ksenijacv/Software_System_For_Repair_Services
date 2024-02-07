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
 * @author Ksenija
 */
public class IzvestajOServisu extends ApstraktniObjekat implements Serializable {

    private int izvestajID;
    private int ukupnaCena;
    private Date datumVremeIzvestaja;
    private String napomena;
    private NacinPlacanja placanje;
    private PrijavaKvara prijava;
    private ArrayList<StavkaIzvestaja> stavke;

    public IzvestajOServisu() {
    }

    public IzvestajOServisu(int izvestajID, int ukupnaCena, Date datumVremeIzvestaja, String napomena, NacinPlacanja placanje, PrijavaKvara prijava, ArrayList<StavkaIzvestaja> stavke) {
        this.izvestajID = izvestajID;
        this.ukupnaCena = ukupnaCena;
        this.datumVremeIzvestaja = datumVremeIzvestaja;
        this.napomena = napomena;
        this.placanje = placanje;
        this.prijava = prijava;
        this.stavke = stavke;
    }

    @Override
    public String nazivTabele() {
        return "izvestajOServisu";
    }

    @Override
    public String alijas() {
        return "ios";
    }

    @Override
    public String spajanje() {
        return "JOIN prijavakvara pk on(pk.prijavaid=ios.prijavaid)"
                + "JOIN klijent k on (k.klijentid=pk.klijentid)"
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
            IzvestajOServisu ios = new IzvestajOServisu(rs.getInt("IzvestajID"), rs.getInt("UkupnaCena"), rs.getTime("DatumVremeIzvestaja"), rs.getString("Napomena"), NacinPlacanja.valueOf(rs.getString("Placanje")), pk, null);
            lista.add(ios);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(ukupnacena, datumvremeizvestaja, napomena, placanje, prijavaid)";
    }

    @Override
    public String primarniKljuc() {
        return "izvestajid=" + izvestajID;
    }

    @Override
    public String vrednostiZaInsert() {
        return ukupnaCena + ",'" + new Timestamp(datumVremeIzvestaja.getTime()) + "','" + napomena + "','" + placanje.toString() + "'," + prijava.getPrijavaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "UkupnaCena=" + ukupnaCena + ",DatumVremeIzvestaja='" + new Timestamp(datumVremeIzvestaja.getTime()) + "',Napomena='" + napomena + "', Placanje='" + placanje.toString() + "'";
    }

    @Override
    public String id() {
        return "izvestajid=" + izvestajID;
    }

    public int getIzvestajID() {
        return izvestajID;
    }

    public void setIzvestajID(int izvestajID) {
        this.izvestajID = izvestajID;
    }

    public int getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(int ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Date getDatumVremeIzvestaja() {
        return datumVremeIzvestaja;
    }

    public void setDatumVremeIzvestaja(Date datumVremeIzvestaja) {
        this.datumVremeIzvestaja = datumVremeIzvestaja;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public NacinPlacanja getPlacanje() {
        return placanje;
    }

    public void setPlacanje(NacinPlacanja placanje) {
        this.placanje = placanje;
    }

    public PrijavaKvara getPrijava() {
        return prijava;
    }

    public void setPrijava(PrijavaKvara prijava) {
        this.prijava = prijava;
    }

    public ArrayList<StavkaIzvestaja> getStavke() {
        return stavke;
    }

    public void setStavke(ArrayList<StavkaIzvestaja> stavke) {
        this.stavke = stavke;
    }

}
