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
public class StavkaIzvestaja extends ApstraktniObjekat implements Serializable {

    private int rb;
    private IzvestajOServisu izvestaj;
    private PrijavaKvara prijavaKvara;
    private int kolicina;
    private RezervniDeo deo;

    public StavkaIzvestaja() {
    }

    public StavkaIzvestaja(int rb, IzvestajOServisu izvestaj, PrijavaKvara prijavaKvara, int kolicina, RezervniDeo deo) {
        this.rb = rb;
        this.izvestaj = izvestaj;
        this.prijavaKvara = prijavaKvara;
        this.kolicina = kolicina;
        this.deo = deo;
    }

    @Override
    public String nazivTabele() {
        return "stavkaizvestaja";
    }

    @Override
    public String alijas() {
        return "si";
    }

    @Override
    public String spajanje() {
        return "JOIN izvestajoservisu ios using(izvestajid)"
                + "JOIN rezervnideo rd using(rezervnideoid)"
                + "JOIN prijavakvara pk on(pk.prijavaid=ios.prijavaid)"
                + "JOIN klijent k on (k.klijentid=pk.klijentid)"
                + "JOIN serviser s on(s.serviserid=pk.serviserid)"
                + "JOIN mesto m on (m.mestoid=k.mestoid)";
    }

    @Override
    public ArrayList<ApstraktniObjekat> selectLista(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            PrijavaKvara pk = new PrijavaKvara(rs.getInt("PrijavaID"), rs.getString("OpisKvara"), rs.getTimestamp("DatumVremePrijave"), rs.getInt("status"), null, null, null);
            RezervniDeo rd = new RezervniDeo(rs.getInt("RezervniDeoID"), rs.getString("NazivRezervnogDela"), rs.getInt("CenaRezervnogDela"), rs.getString("OpisRezervnogDela"), null);
            IzvestajOServisu ios = new IzvestajOServisu(rs.getInt("IzvestajID"), rs.getInt("UkupnaCena"), rs.getTime("DatumVremeIzvestaja"), rs.getString("Napomena"), NacinPlacanja.valueOf(rs.getString("Placanje")), pk, null);
            StavkaIzvestaja si = new StavkaIzvestaja(rs.getInt("Rb"), ios, pk, rs.getInt("Kolicina"), rd);
            lista.add(si);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(Rb,IzvestajID,PrijavaID, Kolicina, RezervniDeoID)";
    }

    @Override
    public String primarniKljuc() {
        return "izvestajID=" + izvestaj.getIzvestajID();
    }

    @Override
    public String vrednostiZaInsert() {
        return rb + "," + izvestaj.getIzvestajID() + "," + prijavaKvara.getPrijavaID() + "," + kolicina + "," + deo.getRezervniDeoID();
    }

    @Override
    public String vrednostiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String id() {
        return "izvestajid=" + izvestaj.getIzvestajID();
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public IzvestajOServisu getIzvestaj() {
        return izvestaj;
    }

    public void setIzvestaj(IzvestajOServisu izvestaj) {
        this.izvestaj = izvestaj;
    }

    public PrijavaKvara getPrijavaKvara() {
        return prijavaKvara;
    }

    public void setPrijavaKvara(PrijavaKvara prijavaKvara) {
        this.prijavaKvara = prijavaKvara;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public RezervniDeo getDeo() {
        return deo;
    }

    public void setDeo(RezervniDeo deo) {
        this.deo = deo;
    }

}
