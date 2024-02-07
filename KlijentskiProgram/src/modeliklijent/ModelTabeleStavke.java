/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeliklijent;

import domenskeKlase.PrijavaKvara;
import domenskeKlase.RezervniDeo;
import domenskeKlase.StavkaIzvestaja;
import formemain.PretragaPrijava;
import java.text.SimpleDateFormat;
import klijentkontroler.KlijentKontroler;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ksenija
 */
public class ModelTabeleStavke extends AbstractTableModel {

    private ArrayList<StavkaIzvestaja> lista;
    private String[] kolone = {"Rb", "Rez. deo", "Kolicina", "Cena", "Cena stavke"};
    int rb = 1;

    public ModelTabeleStavke() {
        try {
            lista = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleStavke.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ModelTabeleStavke(ArrayList<StavkaIzvestaja> stavke) {
        try {
            lista = stavke;
            rb = lista.size() + 1;
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleStavke.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        StavkaIzvestaja si = lista.get(row); //izmeniti
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        switch (column) {
            case 0:
                return si.getRb();
            case 1:
                return si.getDeo().getNazivRezervnogDela();
            case 2:
                return si.getKolicina();
            case 3:
                return si.getDeo().getCenaRezervnogDela();
            case 4:
                return si.getKolicina() * si.getDeo().getCenaRezervnogDela();
            default:
                return null;
        }
    }

    public boolean postoji(RezervniDeo rd) {
        for (StavkaIzvestaja stavkaIzvestaja : lista) {
            if (stavkaIzvestaja.getDeo().getRezervniDeoID() == rd.getRezervniDeoID()) {
                return true;
            }
        }
        return false;
    }

    public void dodaj(StavkaIzvestaja si) {
        si.setRb(rb++);
        lista.add(si);
        fireTableDataChanged();
    }

    public void obrisi(int row) {
        lista.remove(row);
        rb = 1;
        for (StavkaIzvestaja stavkaIzvestaja : lista) {
            stavkaIzvestaja.setRb(rb++);
        }
        fireTableDataChanged();
    }

    public int obracunajUkupnuCenu() {
        int cena = 0;
        for (StavkaIzvestaja stavkaIzvestaja : lista) {
            cena += (int) (stavkaIzvestaja.getKolicina() * stavkaIzvestaja.getDeo().getCenaRezervnogDela());
        }
        return (int) (cena * 1.15);
    }

    public ArrayList<StavkaIzvestaja> getLista() {
        return lista;
    }

}
