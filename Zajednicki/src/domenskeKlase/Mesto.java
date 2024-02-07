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
public class Mesto extends ApstraktniObjekat implements Serializable{
    int mestoID;
    String nazivMesta;
    String pttBroj;

    public Mesto() {
    }

    @Override
    public String toString() {
        return nazivMesta;
    }
    
    public Mesto(int mestoID, String nazivMesta, String pttBroj) {
        this.mestoID = mestoID;
        this.nazivMesta = nazivMesta;
        this.pttBroj = pttBroj;
    }

    public int getMestoID() {
        return mestoID;
    }

    public void setMestoID(int mestoID) {
        this.mestoID = mestoID;
    }

    public String getNazivMesta() {
        return nazivMesta;
    }

    public void setNazivMesta(String nazivMesta) {
        this.nazivMesta = nazivMesta;
    }

    public String getPttBroj() {
        return pttBroj;
    }

    public void setPttBroj(String pttBroj) {
        this.pttBroj = pttBroj;
    }
    
    
    @Override
    public String nazivTabele() {
        return "mesto";
    }

    @Override
    public String alijas() {
        return "m";
    }

    @Override
    public String spajanje() {
        return "";
    }

    @Override
    public ArrayList<ApstraktniObjekat> selectLista(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniObjekat> lista=new ArrayList<>();
        while(rs.next()){
            Mesto m=new Mesto(rs.getInt("mestoID"), rs.getString("NazivMesta"), rs.getString("PttBroj"));
            lista.add(m);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String primarniKljuc() {
        return "mestoid=" +mestoID;
    }

    @Override
    public String vrednostiZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vrednostiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String id() {
        return "mestoid=" +mestoID;
    }
    
}
